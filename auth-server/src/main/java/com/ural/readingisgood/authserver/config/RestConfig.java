package com.ural.readingisgood.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {


    @Bean
    public RestTemplate getInstance(){

        return new RestTemplate();

    }
}
