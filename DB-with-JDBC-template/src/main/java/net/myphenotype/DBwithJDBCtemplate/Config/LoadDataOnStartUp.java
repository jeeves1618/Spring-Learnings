package net.myphenotype.DBwithJDBCtemplate.Config;

import com.github.javafaker.Faker;
import lombok.Data;
import net.myphenotype.DBwithJDBCtemplate.Entity.ItProjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Configuration
@Data
public class LoadDataOnStartUp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    ItProjects itProjects;

    Faker faker = new Faker();

    static List<ItProjects> itProjectsList = new ArrayList<>();
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        IntStream.range(0,10).forEach((index) -> {
            Date pastDate = faker.date().past(100, TimeUnit.DAYS);
            Date futureDate = faker.date().future(100, TimeUnit.DAYS);
            Date current = faker.date().future(1, TimeUnit.DAYS);
            itProjects.setProjectName(faker.name().title());
            itProjects.setStartDate(new java.sql.Date(pastDate.getTime()));
            itProjects.setTargetEndDate(new java.sql.Date(futureDate.getTime()));
            itProjects.setActualEndDate(new java.sql.Date(futureDate.getTime()));
            itProjects.setCreatedOn(new java.sql.Date(current.getTime()));
            itProjects.setCreatedBy(faker.friends().character());
            itProjects.setModifiedOn(new java.sql.Date(current.getTime()));
            itProjects.setModifiedBy(faker.hitchhikersGuideToTheGalaxy().character());

            itProjectsList.add(itProjects);
            itProjects = new ItProjects();
        });
    }

    public List<ItProjects> getProjects(){
        return itProjectsList;
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
