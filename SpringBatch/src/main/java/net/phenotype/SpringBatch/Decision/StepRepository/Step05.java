package net.phenotype.SpringBatch.Decision.StepRepository;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step05 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello I am Homo Sapien. I have been here in a pale blue planet for the past 200,000 years. In all of the universe, I am only one who is capable of writing this sentance.");
        return RepeatStatus.FINISHED;
    }
}
