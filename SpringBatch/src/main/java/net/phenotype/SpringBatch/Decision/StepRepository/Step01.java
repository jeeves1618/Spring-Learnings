package net.phenotype.SpringBatch.Decision.StepRepository;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step01 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello I am Ardipithicus Ramidus, I lived 4.3 million years ago. I am probably the common ancestor of all hominids.");
        return RepeatStatus.FINISHED;
    }
}
