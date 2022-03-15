package com.classpath.springbootdemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name="user")
    public User userBasedOnMisingUser(){
        return new User();
    }
    @Bean
    @ConditionalOnProperty(name="loadUser", havingValue = "false", matchIfMissing = true)
    public User user(){
        return new User();
    }
    @Bean
    @ConditionalOnBean(name="user")
    public User userBasedOnUser(){
        return new User();
    }

    @Bean
    @ConditionalOnMissingClass(value="com.xyz.Test")
    public User userBasedOnMisingClass(){
        return new User();
    }

}


class User{
}
