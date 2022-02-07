package net.phenotype.SpringBatch.BasicFlow.StepRepository;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step02 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello I am Australopiticus Afarensis, I lived 3.2 million years ago. Not to boast or anything, but I am first one to walk upright.");
        return RepeatStatus.FINISHED;
    }
}
