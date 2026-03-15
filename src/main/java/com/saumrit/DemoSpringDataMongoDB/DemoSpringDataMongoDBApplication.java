package com.saumrit.DemoSpringDataMongoDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@ConfigurationPropertiesScan
public class DemoSpringDataMongoDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataMongoDBApplication.class, args);
	}

}
