package edu.educate.configurations;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@PropertySource("classpath:ValidationMessages.properties")
@PropertySource("classpath:messages.properties")
public class EducateConfigs {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("ValidationMessages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasenames("ValidationMessages", "messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
