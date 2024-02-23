package edu.java.configuration;

import edu.java.shedule.LinkUpdaterScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class LinkUpdaterSchedulerConfiguration {
    @Bean
    public LinkUpdaterScheduler updaterScheduler() {
        return new LinkUpdaterScheduler();
    }
}
