package net.myphenotype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy
@PropertySource("classpath:BookProperties.properties")
public class SpringMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMysqlApplication.class, args);
	}
}
/*
To Do
Add Exception page for Duplicate Books
 */