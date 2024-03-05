package net.myphenotype.Spring.Cloud.Config.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SpringCloudConfigConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigConsumerApplication.class, args);
	}

}
