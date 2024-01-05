//package ru.home.configuration;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import lombok.NonNull;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//    @Override
//    protected String getDatabaseName() {
//        return "home_app";
//    }
//
//    @Override
//    @Bean
//    public MongoClient mongoClient() {
//        ConnectionString connectionString = new ConnectionString("mongodb://dev:dev@localhost:27017/admin");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    @Override
//    public Collection getMappingBasePackages() {
//        return Collections.singleton("ru.home");
//    }
//}
