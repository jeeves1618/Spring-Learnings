package net.phenotype.SpringBatch.linearFlow.Configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello Ardipithicus Ramidus!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello Australopithicus Afarensis!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello Homo Habilis!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello Homo Sapiens!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello Homo Erectus!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
    @Bean
    public Job helloWorldJob(){
        return jobBuilderFactory.get("howdyHominid")
                .start(step1())
                .on("COMPLETED").to(step2())
                .from(step2()).on("COMPLETED").to(step3())
                .from(step3()).on("COMPLETED").to(step5())
                .from(step5()).on("COMPLETED").to(step4())
                /*
                .from(step4()).on("COMPLETED").to(step1())
                .from(step1()).end()
                Above flow will never end. after Step 1 the flow is going to the start line and executes all the steps again.
                 */
                .from(step4()).end()
                .build();
    }
}
