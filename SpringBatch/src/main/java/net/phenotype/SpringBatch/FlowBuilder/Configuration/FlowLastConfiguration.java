package net.phenotype.SpringBatch.FlowBuilder.Configuration;

import net.phenotype.SpringBatch.FlowBuilder.StepRepository.DemoStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowLastConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    DemoStep demoStep;

    @Bean
    public Step DemoFirst(){
        TaskletStepBuilder taskletStepBuilder = stepBuilderFactory.get("DemoFirst").tasklet(demoStep);
        Step step = taskletStepBuilder.build();
        return step;
    }

    @Bean
    public Job demoFirstFlowNext(Flow flow){
        return jobBuilderFactory.get("demoFirstJob")
                .start(DemoFirst())
                .on("COMPLETED").to(flow)
                .end().build();
    }
}
