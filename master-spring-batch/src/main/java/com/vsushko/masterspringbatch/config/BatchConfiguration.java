package com.vsushko.masterspringbatch.config;

import com.vsushko.masterspringbatch.listener.HwJobExecutionListener;
import com.vsushko.masterspringbatch.listener.HwStepExecutionListener;
import com.vsushko.masterspringbatch.model.Product;
import com.vsushko.masterspringbatch.processor.InMemeItemProcessor;
import com.vsushko.masterspringbatch.reader.ItemReader;
import com.vsushko.masterspringbatch.reader.ProductServiceAdapter;
import com.vsushko.masterspringbatch.service.ProductService;
import com.vsushko.masterspringbatch.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.sql.DataSource;

/**
 * @author vsushko
 */
@EnableBatchProcessing
@Configuration
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private HwJobExecutionListener jobExecutionListener;

    @Autowired
    private HwStepExecutionListener hwStepExecutionListener;

    @Autowired
    private InMemeItemProcessor inMemeItemProcessor;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductServiceAdapter productServiceAdapter;

    @Bean
    public Step step1() {
        return steps.get("step1")
                .listener(hwStepExecutionListener)
                .tasklet(helloworldTasklet())
                .build();
    }

    @Bean
    public Step step2() {
        return steps.get("step2")
                .<Integer, Integer>chunk(3)
//                .reader(reader())
//                .reader(flatFileItemReader(null))
//                .processor(inMemeItemProcessor)
//                .reader(xmlItemReader(null))
//                .reader(flatfixFileItemReader(null))
//                .reader(jdbcCursorItemReader())
//                .reader(jsonItemReader(null))
                .reader(serviceItemReader())
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public ItemReader reader() {
        return new ItemReader();
    }

    @StepScope
    @Bean
    public StaxEventItemReader xmlItemReader(@Value("#{jobParameters['fileInput']}") FileSystemResource inputFile) {
        // where to read the xml file
        StaxEventItemReader reader = new StaxEventItemReader();
        reader.setResource(inputFile);

        // need to let reader know which tags describe the domain object
        reader.setFragmentRootElementName("product");

        // tell reader how to parse XML and which domain object to be mapped
        reader.setUnmarshaller(new Jaxb2Marshaller() {
            {
                setClassesToBeBound(Product.class);
            }
        });
        return reader;
    }

    @StepScope
    @Bean
    public FlatFileItemReader flatFileItemReader(@Value("#{jobParameters['fileInput']}") FileSystemResource inputFile) {
        FlatFileItemReader reader = new FlatFileItemReader();
        // step 1 let reader kno where is the file
        // reader.setResource(new FileSystemResource("input/product.csv"));
        reader.setResource(inputFile);

        // create the line Mapper
        reader.setLineMapper(
                new DefaultLineMapper<Product>() {
                    {
                        setLineTokenizer(new DelimitedLineTokenizer() {
                            {
                                setNames("productID", "productName", "productDesc", "price", "unit");
                                setDelimiter("|");
                            }
                        });
                        setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {
                            {
                                setTargetType(Product.class);
                            }
                        });
                    }
                }
        );

        // step 3 tell reader to skip the header
        reader.setLinesToSkip(1);
        return reader;
    }

    @StepScope
    @Bean
    public FlatFileItemReader flatfixFileItemReader(@Value("#{jobParameters['fileInput']}") FileSystemResource inputFile) {
        FlatFileItemReader reader = new FlatFileItemReader();
        // step 1 let reader kno where is the file
        // reader.setResource(new FileSystemResource("input/product.csv"));
        reader.setResource(inputFile);

        // create the line Mapper
        reader.setLineMapper(
                new DefaultLineMapper<Product>() {
                    {
                        setLineTokenizer(new FixedLengthTokenizer() {
                            {
                                setNames("productID", "productName", "productDesc", "price", "unit");
                                setColumns(
                                        new Range(1, 16),
                                        new Range(17, 41),
                                        new Range(42, 65),
                                        new Range(66, 73),
                                        new Range(74, 80)
                                );
                            }
                        });
                        setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {
                            {
                                setTargetType(Product.class);
                            }
                        });
                    }
                }
        );

        // step 3 tell reader to skip the header
        reader.setLinesToSkip(1);
        return reader;
    }

    @Bean
    public JdbcCursorItemReader jdbcCursorItemReader() {
        JdbcCursorItemReader reader = new JdbcCursorItemReader();
        reader.setDataSource(this.dataSource);
        reader.setSql("select product_id, prod_name as productName, prod_desc as productDesc, unit, price from products");
        reader.setRowMapper(new BeanPropertyRowMapper() {
            {
                setMappedClass(Product.class);
            }
        });
        return reader;
    }

    @StepScope
    @Bean
    public JsonItemReader jsonItemReader(@Value("#{jobParameters['fileInput']}") FileSystemResource inputFile) {
        JsonItemReader reader = new JsonItemReader(inputFile, new JacksonJsonObjectReader(Product.class));
        return reader;
    }

    @Bean
    public ItemReaderAdapter serviceItemReader() {
        ItemReaderAdapter reader = new ItemReaderAdapter();
        reader.setTargetObject(productServiceAdapter);
        reader.setTargetMethod("nextProduct");
        return reader;
    }

    private Tasklet helloworldTasklet() {
        return (new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
                System.out.println("Hello world");
                return RepeatStatus.FINISHED;
            }
        });
    }

    @Bean
    public Job helloworldJob() {
        return jobs.get("helloworldJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .start(step1())
                .start(step2())
                .build();
    }
}
