package net.phenotype.SpringBatch.BasicFlow.Configuration;

import net.phenotype.SpringBatch.BasicFlow.StepRepository.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    Step01 step01;

    @Autowired
    Step02 step02;

    @Autowired
    Step03 step03;

    @Autowired
    Step04 step04;

    @Autowired
    Step05 step05;

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
    public Step Step03(){
        TaskletStepBuilder taskletStepBuilder = stepBuilderFactory.get("Step3").tasklet(step03);
        Step step = taskletStepBuilder.build();
        return step;
    }

    @Bean
    public Step Step04(){
        TaskletStepBuilder taskletStepBuilder = stepBuilderFactory.get("Step4").tasklet(step04);
        Step step = taskletStepBuilder.build();
        return step;
    }

    @Bean
    public Step Step05(){
        TaskletStepBuilder taskletStepBuilder = stepBuilderFactory.get("Step5").tasklet(step05);
        Step step = taskletStepBuilder.build();
        return step;
    }

    @Bean
    public Job helloWorldJob(){
        System.out.println("Executing the job");
        return jobBuilderFactory.get("HowdyHomis!")
                .start(Step01())
                .on("COMPLETED").to(Step02())
                .from(Step02()).on("COMPLETED").to(Step03())
                .from(Step03()).on("COMPLETED").to(Step05())
                .from(Step05()).on("COMPLETED").to(Step04())
                /*
                .from(step4()).on("COMPLETED").to(step1())
                .from(step1()).end()
                Above flow will never end. after Step 1 the flow is going to the start line and executes all the steps again.
                 */
                .from(Step04()).end()
                .build();
    }
}
