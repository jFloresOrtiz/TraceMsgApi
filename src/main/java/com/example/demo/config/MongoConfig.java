package com.example.demo.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoConfig {
    
    private final MongoConfigProperties properties;

    public MongoConfig(MongoConfigProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(properties.uri);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), properties.database);
    }
}