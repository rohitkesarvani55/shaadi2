package com.bond.shaadi;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;


import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
@EnableSwagger2
public class AppConfig {

    @Value("${mongodb.uri}")
    private String uri;

    @Value("${mongodb.uri}")
    private String mongoUrl;

    public String getUri() {
        return uri;
    }

    @Bean
    MongoClient mongoClient() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoUrl))
                .codecRegistry(pojoCodecRegistry)
                .build();

        return MongoClients.create(settings);    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bond.shaadi.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
