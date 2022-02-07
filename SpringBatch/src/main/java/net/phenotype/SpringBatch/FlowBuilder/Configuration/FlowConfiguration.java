package net.phenotype.SpringBatch.FlowBuilder.Configuration;

import net.phenotype.SpringBatch.FlowBuilder.StepRepository.Step01;
import net.phenotype.SpringBatch.FlowBuilder.StepRepository.Step02;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class FlowConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    Step01 step01;

    @Autowired
    Step02 step02;

    @Bean
    public Step Step01(){
        TaskletStepBuilder taskletStepBuilder = stepBuilderFactory.get("Step1").tasklet(step01);
        Step step = taskletStepBuilder.build();
        return step;
    }

    @Bean
    public Step Step02(){
        TaskletStepBuilder taskletStepBuilder = stepBuilderFactory.get("Step2").tasklet(step02);
        Step step = taskletStepBuilder.build();
        return step;
    }

   @Bean
    public Flow humanEvolution(){
       FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("humanEvolution");

       flowBuilder.start(Step01())
               .next(Step02())
               .end();

       return flowBuilder.build();
   }

}
