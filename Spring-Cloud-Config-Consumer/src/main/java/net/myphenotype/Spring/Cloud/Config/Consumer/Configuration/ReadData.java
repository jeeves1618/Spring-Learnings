package net.myphenotype.Spring.Cloud.Config.Consumer.Configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "myreads")
@Getter
@Setter
@Component
public class ReadData {

    private String url;
    private String description;
    private String sorting;
    private String whatdoimean;
    private String whomtocontact;
}

