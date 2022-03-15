package net.myphenotype.SpringBootDemo.Config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {

    @Bean
    @ConditionalOnProperty(name="loadUser", havingValue = "false", matchIfMissing = true)
    public User user(){
        return new User();
    }

    @Bean
    public User userBasedOnUser(){
        return new User();
    }
}
