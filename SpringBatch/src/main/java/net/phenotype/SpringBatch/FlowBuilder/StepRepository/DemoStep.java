package net.phenotype.SpringBatch.FlowBuilder.StepRepository;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class DemoStep implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello I am Home Neanderthalensis, I lived in Europe 40,000 years ago. Homo Sapiens from Africa and Asia came and killed us all");
        return RepeatStatus.FINISHED;
    }
}
