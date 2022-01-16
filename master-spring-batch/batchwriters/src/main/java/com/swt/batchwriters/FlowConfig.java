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

}
