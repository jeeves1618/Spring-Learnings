package net.myphenotype.SpringBootDemo.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ApplicationConfiguration implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public ApplicationConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> beans = Arrays.asList(this.applicationContext.getBeanDefinitionNames());
        beans.forEach(bean -> System.out.println("Beans: " + bean));
    }
}
