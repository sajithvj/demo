package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.mongodb.autoconfigure.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }
    @Bean
    public MongoClientSettingsBuilderCustomizer mongoClientCustomizer() {
        return builder -> builder
                .applyToConnectionPoolSettings(pool -> pool
                        .maxSize(50)
                        .minSize(5))
                .applyToSocketSettings(socket -> socket
                        .connectTimeout(10, TimeUnit.SECONDS));
    }
}
