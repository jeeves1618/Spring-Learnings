package com.classpath.springbootdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        List<String> beans = Arrays.asList(this.applicationContext.getBeanDefinitionNames());
        beans.stream()
                .filter(bean -> bean.startsWith("user"))
                .forEach(bean -> System.out.println("Bean Name:: "+ bean));
    }
}
