package net.myphenotype.Spring.Cloud.Config.Consumer.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {
    @Value("${message}")
    String urlToBeAccessed;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Application started!" + urlToBeAccessed);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
