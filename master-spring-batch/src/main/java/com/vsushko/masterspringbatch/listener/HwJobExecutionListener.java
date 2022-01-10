package com.vsushko.masterspringbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author vsushko
 */
@Component
public class HwJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before starting the Job" + jobExecution.getJobInstance().getJobName());
        System.out.println("Before starting the Job" + jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("My name", "michael");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after compliting the Job - Job Execution Context" + jobExecution.getExecutionContext());
    }
}
