package com.vsushko.masterspringbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author vsushko
 */
@Component
public class HwStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("this is from beforee step execution" + stepExecution.getJobExecution().getExecutionContext());
        System.out.println("In side Step - print job parameters: " + stepExecution.getJobExecution().getJobParameters());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("this is from after step execution" + stepExecution.getJobExecution().getExecutionContext());
        return null;
    }
}
