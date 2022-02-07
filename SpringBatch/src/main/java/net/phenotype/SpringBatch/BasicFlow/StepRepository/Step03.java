package net.phenotype.SpringBatch.BasicFlow.StepRepository;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Step03 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Hello I am Homo Habilis. I lived 2.3 million years ago. I am the first of your ancestors who was brave enough to fight other animals. " +
                "Because, I know how to use sticks and stones as tools");
        return RepeatStatus.FINISHED;
    }
}
