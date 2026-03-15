package com.saumrit.DemoSpringDataMongoDB.repository;

import com.saumrit.DemoSpringDataMongoDB.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface V1StudentRepository extends MongoRepository<Student, String> {

    List<Student> findByName(String name);
}
