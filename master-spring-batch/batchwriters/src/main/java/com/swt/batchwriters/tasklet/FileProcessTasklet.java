package com.swt.batchwriters.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author vsushko
 */
public class FileProcessTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("File Process tasks");
        Thread.sleep(1000);
        System.out.println("File Process tasks completed");
        return RepeatStatus.FINISHED;
    }
}
