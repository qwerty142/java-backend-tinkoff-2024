package edu.java.bot.configuration;

import edu.java.bot.service.validation.GithubLinkValidator;
import edu.java.bot.service.validation.StackoverflowLinkValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

@Configuration
public class LinkValidatingConfiguration {
    @Bean(name = "stackOverflowLinkValidator")
    StackoverflowLinkValidator stackOverflowLinkValidator() {
        return new StackoverflowLinkValidator();
    }

    @Bean
    @Primary
    @DependsOn("stackOverflowLinkValidator")
    GithubLinkValidator gitHubLinkValidator(StackoverflowLinkValidator next) {
        var validator = new GithubLinkValidator();
        validator.next(next);
        return validator;
    }
}
