package net.myphenotype.Spring.Cloud.Config.Consumer;

import net.myphenotype.Spring.Cloud.Config.Consumer.Configuration.ReadData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableConfigurationProperties(value = {ReadData.class})
public class SpringCloudConfigConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigConsumerApplication.class, args);
	}

}
