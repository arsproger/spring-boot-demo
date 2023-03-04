package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Configurator {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
