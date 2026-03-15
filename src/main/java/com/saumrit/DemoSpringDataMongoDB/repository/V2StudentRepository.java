package com.saumrit.DemoSpringDataMongoDB.repository;

import com.saumrit.DemoSpringDataMongoDB.data.enums.Branch;
import com.saumrit.DemoSpringDataMongoDB.model.Address;
import com.saumrit.DemoSpringDataMongoDB.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Repository
public class V2StudentRepository {

    @Autowired
    public  final MongoTemplate mongoTemplate ;

    public V2StudentRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Student> getAllStudents(){
        return mongoTemplate.findAll(Student.class);
    }

    public List<Student> getAllStudentsSortedByDOB(){
        Query query= new Query();
        Sort sort= Sort.by("dateOfBirth").descending();
        return mongoTemplate.find(query.with(sort),Student.class);
    }

    public void addSingleStudent(Student student){
        mongoTemplate.insert(student);
        //mongoTemplate.save(student) //TODO check the difference between save and insert
    }

    public long deleteSingleStudent(String id){
        Query query= new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, Student.class).getDeletedCount();
    }

    public Student updateSingleStudent(Student student){
        return mongoTemplate.save(student);
    }

    public Student patchUpdateSingleStudent(Student student){

        Criteria criteria= Criteria.where("name").is(student.getName())
                .andOperator(Criteria.where("id").is(student.getId()))
                .andOperator(Criteria.where("college")).is(student.getCollege());
        Query query= new Query().addCriteria(criteria);
        return mongoTemplate.findAndReplace(query,student);
    }

    public List<Student> getAllGenZs() throws ParseException {
        Query query= new Query();
        Criteria criteria= new Criteria();
        criteria.lte(new SimpleDateFormat("yyyy-MM-dd").parse("1997-01-01"));
        Sort sort= Sort.by("dateOfBirth").descending();
        return mongoTemplate.find(query.addCriteria(criteria)
                .with(sort),Student.class);
    }

    public List<Student> getNRIs(){
        Query query= new Query();
        Criteria criteria= Criteria.where("isNRI").is(true);
        return mongoTemplate.find(query.addCriteria(criteria),Student.class);
    }

    public long updateAddress(Address address,String name,String branch) throws Exception {
        Query query= new Query();
        Criteria criteria= Criteria.where("name").is(name)
                .andOperator(Criteria.where("branch").is(Branch.CSE));
        List<Student> students= mongoTemplate.find(query.addCriteria(criteria),Student.class);
        if(students.size()>1)
            throw new Exception("Duplicate DOcuments Found with name and branch ");
        Update update= new Update();
        update.set("address",address);
        return mongoTemplate.update(Student.class)
                .matching(query)
                .apply(update)
                .upsert()
                .getModifiedCount();
    }



}
