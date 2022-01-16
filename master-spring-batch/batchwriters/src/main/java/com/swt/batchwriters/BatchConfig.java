package com.swt.batchwriters;

import com.swt.batchwriters.model.Product;
import com.swt.batchwriters.processor.ProductProcessor;
import com.swt.batchwriters.reader.ColumnRangePartitioner;
import com.swt.batchwriters.tasklet.BizTasklet3;
import com.swt.batchwriters.tasklet.BizTasklet4;
import com.swt.batchwriters.tasklet.CleanupTasklet;
import com.swt.batchwriters.tasklet.ConsoleTasklet;
import com.swt.batchwriters.tasklet.DownloadTasklet;
import com.swt.batchwriters.tasklet.FileProcessTasklet;
import com.swt.batchwriters.tasklet.PagerDutyTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vsushko
 */
@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flow fileFlow;

//    @Autowired
//    private ProductServiceAdapter adapter;
//
//    @Bean
//    public ItemReaderAdapter serviceAdapter() {
//        ItemReaderAdapter readerAdapter = new ItemReaderAdapter();
//        readerAdapter.setTargetObject(adapter);
//        readerAdapter.setTargetMethod("nextProduct");
//        return readerAdapter;
//    }

    @StepScope
    @Bean
    public FlatFileItemReader reader(@Value("#{jobParameters['fileInput']}") FileSystemResource inputFile) {
        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(inputFile);
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper() {
            {
                setFieldSetMapper(new BeanWrapperFieldSetMapper() {
                    {
                        setTargetType(Product.class);
                    }
                });
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames("productId", "productName", "productDesc", "price", "unit");
                        setDelimiter(",");
                    }
                });
            }
        });
        return reader;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter flatFileItemWriter(@Value("#{jobParameters['fileOutput']}") FileSystemResource outputFile) {
        FlatFileItemWriter writer = new FlatFileItemWriter<Product>();

//        FlatFileItemWriter writer = new FlatFileItemWriter<Product>() {
//            @Override
//            public String doWrite(List<? extends Product> items) {
//                for (Product p : items) {
//                    if (p.getProductId() == 9) {
//                        throw new RuntimeException("Beacause ID is 9");
//                    }
//                }
//                return super.doWrite(items);
//            }
//        };

        writer.setResource(outputFile);
        writer.setLineAggregator(new DelimitedLineAggregator() {
            {
                setDelimiter("|");
                setFieldExtractor(new BeanWrapperFieldExtractor() {
                    {
                        setNames(new String[]{"productId", "productName", "productDesc", "price", "unit"});
                    }
                });
            }
        });
        // how to write the header
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("productId,productName,productDesc,price,unit");
            }
        });
        writer.setAppendAllowed(true);
//        writer.setFooterCallback(new FlatFileFooterCallback() {
//            @Override
//            public void writeFooter(Writer writer) throws IOException {
//                writer.write("Thee file is create at " + new SimpleDateFormat().format(new Date()));
//            }
//        });
        return writer;
    }

    @StepScope
    @Bean
    public StaxEventItemWriter xmlWriter(@Value("#{jobParameters['fileOutput']}") FileSystemResource outputFile) {
        XStreamMarshaller marshaller = new XStreamMarshaller();
        HashMap<String, Class> aliases = new HashMap<>();
        aliases.put("product", Product.class);
        marshaller.setAliases(aliases);
        marshaller.setAutodetectAnnotations(true);

        StaxEventItemWriter staxEventItemWriter = new StaxEventItemWriter();
        staxEventItemWriter.setResource(outputFile);
        staxEventItemWriter.setMarshaller(marshaller);
        staxEventItemWriter.setRootTagName("Products");
        return staxEventItemWriter;
    }

    @Bean
    public JdbcBatchItemWriter dbWriter() {
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into products (product_id, prod_name, prod_desc, unit, price) " +
                "values (?, ?, ?, ?, ?)");
        writer.setItemPreparedStatementSetter(new ItemPreparedStatementSetter<Product>() {
            @Override
            public void setValues(Product item, PreparedStatement ps) throws SQLException {
                ps.setInt(1, item.getProductId());
                ps.setString(2, item.getProductName());
                ps.setString(3, item.getProductDesc());
                ps.setInt(4, item.getUnit());
                ps.setBigDecimal(5, item.getPrice());
            }
        });
        return writer;
    }

    @Bean
    public JdbcBatchItemWriter dbWriter2() {
        return new JdbcBatchItemWriterBuilder<Product>()
                .dataSource(dataSource)
                .sql("insert into products (product_id, prod_name, prod_desc, unit, price) " +
                        "values (:productId, :productName, :productDesc, :unit, :price)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step step1() {
        return steps.get("step1")
                .<Product, Product>chunk(3)
//                .reader(serviceAdapter())
                .reader(reader(null))
                .processor(new ProductProcessor())
                .writer(flatFileItemWriter(null))
                .faultTolerant()
                .skip(FlatFileParseException.class)
                .skipLimit(3)
                // skip  policy
//                .retry(ResourceAccessException.class)
//                .retryLimit(5)
//                .skipPolicy(new AlwaysSkipItemSkipPolicy())
//                .listener(new ProductSkipListener())
//                .writer(xmlWriter(null))
//                .writer(dbWriter())
//                .writer(dbWriter2())
                .build();
    }

    @Bean
    public Step multiThreadStep() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.afterPropertiesSet();

        return steps.get("step1")
                .<Product, Product>chunk(3)
//                .reader(serviceAdapter())
                .reader(reader(null))
                .processor(new ProductProcessor())
                .writer(flatFileItemWriter(null))
                .taskExecutor(taskExecutor)
//                .faultTolerant()
//                .skip(FlatFileParseException.class)
//                .skipLimit(3)
                // skip  policy
//                .retry(ResourceAccessException.class)
//                .retryLimit(5)
//                .skipPolicy(new AlwaysSkipItemSkipPolicy())
//                .listener(new ProductSkipListener())
//                .writer(xmlWriter(null))
//                .writer(dbWriter())
//                .writer(dbWriter2())
                .build();
    }

    @Bean
    public AsyncItemProcessor asyncItemProcessor() {
        AsyncItemProcessor processor = new AsyncItemProcessor();
        processor.setDelegate(new ProductProcessor());
        processor.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return processor;
    }

    @Bean
    public AsyncItemWriter asyncItemWriter() {
        AsyncItemWriter writer = new AsyncItemWriter();
        writer.setDelegate(flatFileItemWriter(null));
        return writer;
    }

    @Bean
    public Step async_step() {

        return steps.get("async_step")
                .<Product, Product>chunk(5)
                .reader(reader(null))
                //.reader(serviceAdapter())
                .processor(asyncItemProcessor())
                //.writer(flatFileItemWriter(null))
                .writer(asyncItemWriter())
                //  .writer(xmlWriter(null))
                //.writer(dbWriter())
                //   .writer(dbWriter2())
                //.faultTolerant()
                // .retry(FlatFileParseException.class)
                // .retryLimit(5)
                //.skip(FlatFileParseException.class)
                //.skipLimit(3)
                //Skippolicy
                //.skipPolicy(new AlwaysSkipItemSkipPolicy())
                // .listener(new ProductSkipListener())
                .build();
    }

    @Bean
    public Step step0() {
        return steps.get("step0")
                .tasklet(new ConsoleTasklet())
                .build();
    }

//
//    @Bean
//    public Job job1() {
//        return jobs.get("job1")
//                .incrementer(new RunIdIncrementer())
//                .start(step0())
////                .next(step1())
//                .next(multiThreadStep())
//                .build();
//    }



    @Bean
    public Step downloadStep() {
        return steps.get("downloadStep")
                .tasklet(new DownloadTasklet())
                .build();
    }

    @Bean
    public Step fileProcessStep() {
        return steps.get("fileProcessStep")
                .tasklet(new FileProcessTasklet())
                .build();
    }

    @Bean
    public Step bizTasklet3Step() {
        return steps.get("bizTasklet3Step")
                .tasklet(new BizTasklet3())
                .build();
    }

    @Bean
    public Step bizTasklet4Step() {
        return steps.get("bizTasklet4Step")
                .tasklet(new BizTasklet4())
                .build();
    }

    @Bean
    public Step cleanupStep() {
        return steps.get("cleanupStep")
                .tasklet(new CleanupTasklet())
                .build();
    }

    public Step pagedDutyStep() {
        return steps.get("pagerrdutyStep")
                .tasklet(new PagerDutyTasklet())
                .build();
    }

    @Bean
    public Flow fileFlow() {
        return new FlowBuilder<SimpleFlow>("fileFlow")
                .start(downloadStep())
                .next(fileProcessStep())
                .build();
    }

    @Bean
    public Flow bizFlow1() {
        return new FlowBuilder<SimpleFlow>("bizFlow1")
                .start(bizTasklet3Step())
                .next(fileProcessStep())
                .build();
    }

    @Bean
    public Flow bizFlow2() {
        return new FlowBuilder<SimpleFlow>("bizFlow2")
                .start(bizTasklet4Step())
//                .next(fileProcessStep())
                .from(bizTasklet4Step()).on("*").end()
                .on("FAILED")
                .to(pagedDutyStep())
                .build();
    }
    public Flow splitFlow() {
        return new FlowBuilder<SimpleFlow>("splitFlow")
                .split(new SimpleAsyncTaskExecutor())
                .add(fileFlow(), bizFlow1(), bizFlow2())
                .build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader pagingItemReader(
            @Value("#{stepExecutionContext['minValue']}") Long minValue,
            @Value("#{stepExecutionContext['maxValue']}") Long maxValue
    ) {
        System.out.println("From " + minValue + "to " + maxValue);
        Map<String, Order> sortKey = new HashMap<>();
        sortKey.put("product_id", Order.ASCENDING);

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("product_id, prod_name, prod_desc, unit, price");
        queryProvider.setFromClause("from products");
        queryProvider.setWhereClause("where product_id >=" + minValue + " and product_id <" + maxValue);
        queryProvider.setSortKeys(sortKey);

        JdbcPagingItemReader reader = new JdbcPagingItemReader();
        reader.setDataSource(this.dataSource);
        reader.setQueryProvider(queryProvider);
        reader.setFetchSize(1000);

        reader.setRowMapper(new BeanPropertyRowMapper() {
            {
                setMappedClass(Product.class);
            }
        });

        return reader;
    }

    public ColumnRangePartitioner columnRangePartitioner() {
        ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
        columnRangePartitioner.setColumn("product_id");
        columnRangePartitioner.setDataSource(dataSource);
        columnRangePartitioner.setTable("products");
        return columnRangePartitioner;
    }

    public Step slaveStep() {
        return steps.get("slaveStep")
                .<Product, Product>chunk(5)
                .reader(pagingItemReader(null, null))
                .writer(new ConsoleItemWriter())
                .build();
    }

    public Step partitionStep() {

        return steps.get("partitionStep")
                .partitioner(slaveStep().getName(), columnRangePartitioner())
                .step(slaveStep())
                .gridSize(3)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }
//
//    @Bean
//    public Job job1() {
//        return jobs.get("job1")
//                .incrementer(new RunIdIncrementer())
//                .start(splitFlow())
//                .next(cleanupStep())
//                .end()
//                .build();
//    }

    @Bean
    public Job job1() {
        return jobs.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(partitionStep())
                .build();
    }
}
