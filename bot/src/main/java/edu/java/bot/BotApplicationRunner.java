package edu.java.bot;

import edu.java.bot.bot.BotApplication;
import edu.java.bot.configuration.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
@Slf4j
public class BotApplicationRunner {

    public static void main(String[] args) {
        var contexts = SpringApplication.run(BotApplicationRunner.class, args);
        ApplicationConfig config = contexts.getBean(ApplicationConfig.class);
        var bot = contexts.getBean(BotApplication.class);
        bot.start();
    }
}
