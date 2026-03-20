package com.saumrit.DemoSpringDataMongoDB.repository;

import com.saumrit.DemoSpringDataMongoDB.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * This is to show use of Repository interface with  Different query methods.
 * Here we will be communicating with DataBase using the query-methods instead of mongoTemplate.
 * Queries will be extracted from the method name.
 * Useful links here
 * <a href="https://docs.spring.io/spring-data/mongodb/reference/repositories/query-methods-details.html">Defining Query Methods</a>
 * <a href="https://docs.spring.io/spring-data/mongodb/reference/mongodb/repositories/query-methods.html">Repository query keywords</a>
 * <a href="https://docs.spring.io/spring-data/mongodb/reference/repositories/query-keywords-reference.html#appendix.query.method.subject">MongoDB-specific Query Methods</a>
 * <a href="https://docs.spring.io/spring-data/mongodb/reference/mongodb/repositories/query-methods.html#mongodb.repositories.queries.json-based">JSON-based Query Methods and Field Restriction</a>
 */
@Repository
public interface V1StudentRepository extends MongoRepository<Student, String> {

    List<Student> findByName(String theName);
    List<Student> findByDateOfBirthAfter(Date date);
    List<Student> findByAgeGreaterThan(Integer age);
    List<Student> findByAgeGreaterThanEqual(Integer theAge);

    //Nested property
    List<Student> findByAddressCity(String city);
    List<Student> findByAddress_State(String city);

    //Here Result limiting keywords used like First,Limit,Top etc between "Find" and "By"
    Student findFirstByName(String theLastName);
    List<Student> findFirst10ByCollege(String theCollegeName);
    List<Student> findTop3ByBranchOrderByCgpaDesc(String branch);

    Long removeByName(String lastName);
    List<Student> deleteByCollege(String collegeName);

    Boolean existsByNriStatus(Boolean theNriStatus);
    Boolean existsByAgeLessThan(Integer age);

    //Use of @Query
    @Query("{'age':{$gte:?0}}")
    List<Student> getAdultStudents(Integer theAge);

    //Update
    @Update("{$inc:{'age':?1}}")
    Long findIncreaseAgeByName(String name,Integer age);//here find..By... clause is used

    @Query("{'firstName':?0}}")
    @Update("{$set:{'age':?1}}")
    void updateAgeOFSingleStudent(String  name, Integer age); //here method name does not matter


}
