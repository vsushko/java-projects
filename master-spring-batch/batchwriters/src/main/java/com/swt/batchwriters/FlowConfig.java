package com.swt.batchwriters;

import com.swt.batchwriters.tasklet.BizTasklet3;
import com.swt.batchwriters.tasklet.BizTasklet4;
import com.swt.batchwriters.tasklet.CleanupTasklet;
import com.swt.batchwriters.tasklet.DownloadTasklet;
import com.swt.batchwriters.tasklet.FileProcessTasklet;
import com.swt.batchwriters.tasklet.PagerDutyTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author vsushko
 */
@Configuration
public class FlowConfig {

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Step step1;

    @Autowired
    private Step async_step;

    @Autowired
    private Step step0;

    @Autowired

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
}
