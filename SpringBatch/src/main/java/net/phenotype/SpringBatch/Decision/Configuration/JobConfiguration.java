package net.phenotype.SpringBatch.Decision.Configuration;

import net.phenotype.SpringBatch.Decision.StepRepository.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
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

    public static class DecisionMaker implements JobExecutionDecider {

        private int count = 0;
        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
            count++;

            if(count%2 == 0) {
                return new FlowExecutionStatus("EVEN");
            } else
            {
                return new FlowExecutionStatus("ODD");
            }
        }
    }

    @Bean
    public JobExecutionDecider decider(){
        return new DecisionMaker();
    }

    @Bean
    public Job helloWorldJob(){
        System.out.println("Executing the job");
        return jobBuilderFactory.get("deciderjob!")
                .start(Step01())
                .next(decider())
                .from(decider()).on("EVEN").to(Step01())
                .from(decider()).on("ODD").to(Step04())
                .end()
                .build();
    }
}
