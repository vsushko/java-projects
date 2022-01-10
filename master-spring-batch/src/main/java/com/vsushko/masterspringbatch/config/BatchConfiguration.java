package com.vsushko.masterspringbatch.config;

import com.vsushko.masterspringbatch.listener.HwJobExecutionListener;
import com.vsushko.masterspringbatch.listener.HwStepExecutionListener;
import com.vsushko.masterspringbatch.model.Product;
import com.vsushko.masterspringbatch.processor.InMemeItemProcessor;
import com.vsushko.masterspringbatch.reader.ItemReader;
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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

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
                .reader(flatFileItemReader(null))
//                .processor(inMemeItemProcessor)
                .writer(new ConsoleItemWriter())
                .build();
    }

    @Bean
    public ItemReader reader() {
        return new ItemReader();
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
