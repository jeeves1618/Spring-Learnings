package net.phenotype.SpringBatch.Decision.StepRepository;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step04 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello I am Homo Erectus. I lived 1.6 million years ago. I am the first one to use fire and took the apes to the top of food chain.");
        return RepeatStatus.FINISHED;
    }
}
