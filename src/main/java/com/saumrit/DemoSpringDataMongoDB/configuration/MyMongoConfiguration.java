package com.saumrit.DemoSpringDataMongoDB.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MyMongoConfiguration {

    public final MyDBConfigurationProperties myDBConfigurationProperties;

    public MyMongoConfiguration(MyDBConfigurationProperties myDBConfigurationProperties) {
        this.myDBConfigurationProperties = myDBConfigurationProperties;
    }

    /**
     * This method is created to return a mongoClient Bean using the connection URI directly from YML
     * @return MongoClient
     */
    @Bean(name = "FirstMongoClient")
    public MongoClient mongoClient(){
        return MongoClients.create(myDBConfigurationProperties.uri);
    }

    /**
     * This method is created to return a mongoClient Bean using the MongoClientFactoryBean
     * Here we are using the host, port, UserName, PassWord for connecting the DB
     * These Credentials we can have in different environment YMLs
     * @return MongoClient
     * @throws Exception
     */
    @Bean(name = "SecondMongoClient")
    public MongoClient getMongoClientByFactory() throws Exception {
        MongoCredential mongoCredential= MongoCredential.createCredential("",myDBConfigurationProperties.database,"".toCharArray());
        MongoClientFactoryBean mongoClientFactoryBean= new MongoClientFactoryBean();
        mongoClientFactoryBean.setCredential(new MongoCredential[]{mongoCredential});
        mongoClientFactoryBean.setPort(myDBConfigurationProperties.port);
        mongoClientFactoryBean.setHost(myDBConfigurationProperties.host);
        return mongoClientFactoryBean.getObject();
    }


    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(){
        return new SimpleMongoClientDatabaseFactory(myDBConfigurationProperties.uri);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean(name="mongoTemplateFromMongoDatabaseFactory")
    @Primary
    public MongoTemplate mongoTemplate(){
        MongoTemplate mongoTemplate_From_MongoDatabaseFactory= new MongoTemplate(mongoDatabaseFactory());
        return mongoTemplate_From_MongoDatabaseFactory;
    }

    @Bean(name="mongoTemplate")
    public MongoTemplate mongoTemplateS(){
        MongoTemplate mongoTemplate_From_MongoClient= new MongoTemplate(mongoClient(),myDBConfigurationProperties.database);
        return mongoTemplate_From_MongoClient;
    }


}
