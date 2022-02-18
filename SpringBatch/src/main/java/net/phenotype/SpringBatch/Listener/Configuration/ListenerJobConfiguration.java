package net.phenotype.SpringBatch.Listener.Configuration;

import net.phenotype.SpringBatch.Listener.ListenerRepo.ChunkListener;
import net.phenotype.SpringBatch.Listener.ListenerRepo.JobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ListenerJobConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<String> reader(){
        return new ListItemReader<>(Arrays.asList("Hello I am Ardipithicus Ramidus, I lived 4.3 million years ago. I am probably the common ancestor of all hominids.",
                "Hello I am Australopiticus Afarensis, I lived 3.2 million years ago. Not to boast or anything, but I am first one to walk upright.",
                "Hello I am Homo Habilis. I lived 2.3 million years ago. I am the first of your ancestors who was brave enough to fight other animals. " +
                        "Because, I know how to use sticks and stones as tools",
                "Hello I am Homo Erectus. I lived 1.6 million years ago. I am the first one to use fire and took the apes to the top of food chain.",
                "Hello I am Homo Sapien. I have been here in a pale blue planet for the past 200,000 years. In all of the universe, I am only one who is capable of writing this sentance."
                ));
    }

    @Bean
    public ItemWriter<String> writer(){
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                for(String item: list)
                System.out.println("Writing item : " + item);
            }
        };
    }

    @Bean
    public Step Step01(){
        SimpleStepBuilder<String, String> taskletStepBuilder = stepBuilderFactory.get("Step1").<String, String>chunk(2)
                .faultTolerant()
                .listener(new ChunkListener())
                .reader(reader())
                .writer(writer());
        Step step = taskletStepBuilder.build();
        return step;
    }

    @Bean
    public Job listenAndMail(JavaMailSender mailSender){
        System.out.println("Executing the job");
        return jobBuilderFactory.get("listenAndMail1")
                .start(Step01())
                .listener(new JobListener(mailSender))
                .build();
    }
}
