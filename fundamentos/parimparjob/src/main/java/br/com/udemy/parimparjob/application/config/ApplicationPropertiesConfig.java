package br.com.udemy.parimparjob.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ApplicationPropertiesConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer execute() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new FileSystemResource("/etc/config/parimparjob/application.properties"));
        return configurer;
    }
}
