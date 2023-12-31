package br.com.primeirojobspringbatch.primeirojobspringbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer config() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        //O configurer não está funcionando com yml
        configurer.setLocation(new FileSystemResource("/etc/config/primeirojobspringbatch/application.properties"));
        System.out.println(configurer);
        return configurer;
    }
}
