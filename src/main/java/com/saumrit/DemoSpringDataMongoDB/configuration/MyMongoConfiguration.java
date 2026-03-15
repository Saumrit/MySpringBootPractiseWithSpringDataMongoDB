package com.saumrit.DemoSpringDataMongoDB.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClientFactory;
import com.mongodb.client.MongoClients;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@ConfigurationProperties(prefix = "myapp.mydatabase")
public class MyMongoConfiguration {

    public String uri;
    public String host;
    public int port;
    public String database;

    /**
     * This method is created to return a mongoClient Bean using the connection URI directly from YML
     * @return MongoClient
     */
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(uri);
    }

    /**
     * This method is created to return a mongoClient Bean using the MongoClientFactoryBean
     * Here we are using the host, port, UserName, PassWord for connecting the DB
     * These Credentials we can have in different environment YMLs
     * @return MongoClient
     * @throws Exception
     */
    @Bean
    public MongoClient getMongoClientByFactory() throws Exception {
        MongoCredential mongoCredential= MongoCredential.createCredential("",database,"".toCharArray());
        MongoClientFactoryBean mongoClientFactoryBean= new MongoClientFactoryBean();
        mongoClientFactoryBean.setCredential(new MongoCredential[]{mongoCredential});
        mongoClientFactoryBean.setPort(port);
        mongoClientFactoryBean.setHost(host);
        return mongoClientFactoryBean.getObject();
    }


    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(){
        return new SimpleMongoClientDatabaseFactory(uri);
    }


    @Bean
    public MongoTemplate mongoTemplate(){
        MongoTemplate mongoTemplate_From_MongoDatabaseFactory= new MongoTemplate(mongoDatabaseFactory());
        MongoTemplate mongoTemplate_From_MongoClient= new MongoTemplate(mongoClient(),database);
        return mongoTemplate_From_MongoDatabaseFactory;
    }


}
