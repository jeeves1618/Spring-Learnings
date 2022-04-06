package net.phenotype.SpringBatch.ListReader.Configuration;

import net.phenotype.SpringBatch.ListReader.ListReader.ListItemReader;
import net.phenotype.SpringBatch.Listener.ListenerRepo.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ListReaderJobConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public ListItemReader listItemReader(){
        List<String> dataList = new ArrayList<>(5);

        dataList.add("Hello I am Ardipithicus Ramidus, I lived 4.3 million years ago. I am probably the common ancestor of all hominids.");
        dataList.add("Hello I am Australopiticus Afarensis, I lived 3.2 million years ago. Not to boast or anything, but I am first one to walk upright.");
        dataList.add("Hello I am Homo Habilis. I lived 2.3 million years ago. I am the first of your ancestors who was brave enough to fight other animals. " +
                        "Because, I know how to use sticks and stones as tools");
        dataList.add("Hello I am Homo Erectus. I lived 1.6 million years ago. I am the first one to use fire and took the apes to the top of food chain.");
        dataList.add("Hello I am Homo Sapien. I have been here in a pale blue planet for the past 200,000 years. In all of the universe, I am only one who is capable of writing this sentance.");

        return new ListItemReader(dataList);
    }

    @Bean
    public Step Step01(){
        SimpleStepBuilder<String, String> taskletStepBuilder = stepBuilderFactory.get("Step1")
                .<String, String>chunk(2)
                .reader(listItemReader())
                .listener(new ChunkListener())
                .writer(list -> {
                    for(Object dataItem: list){
                        System.out.println("Current Item :" + dataItem);
                    }
                });
        Step step = taskletStepBuilder.build();
        return step;
    }
    @Bean
    public Job executeChunkBasedStep(){
        System.out.println("Executing the job");
        return jobBuilderFactory.get("listreaderjob")
                .start(Step01())
                .build();
    }
}
