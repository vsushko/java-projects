package com.swt.batchwriters;

import com.swt.batchwriters.model.Product;
import com.swt.batchwriters.processor.ProductProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
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
import org.springframework.oxm.xstream.XStreamMarshaller;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
        FlatFileItemWriter writer = new FlatFileItemWriter();
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
                .reader(reader(null))
                .processor(new ProductProcessor())
                .writer(flatFileItemWriter(null))
//                .writer(xmlWriter(null))
//                .writer(dbWriter())
//                .writer(dbWriter2())
                .build();
    }

    @Bean
    public Job job1() {
        return jobs.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }
}
