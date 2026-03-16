package com.saumrit.DemoSpringDataMongoDB;

import com.saumrit.DemoSpringDataMongoDB.configuration.MyMongoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@ConfigurationPropertiesScan("com.saumrit.DemoSpringDataMongoDB.configuration")
//@EnableConfigurationProperties(MyMongoConfiguration.class)
//@ConfigurationProperties(prefix = "mydatabase")
public class DemoSpringDataMongoDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataMongoDBApplication.class, args);
	}

}
