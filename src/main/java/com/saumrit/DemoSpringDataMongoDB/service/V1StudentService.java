package com.saumrit.DemoSpringDataMongoDB.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saumrit.DemoSpringDataMongoDB.data.StudentDTO;
import com.saumrit.DemoSpringDataMongoDB.model.Student;
import com.saumrit.DemoSpringDataMongoDB.repository.V1StudentRepository;
import com.saumrit.DemoSpringDataMongoDB.util.StudentIDGeneratorUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class V1StudentService {

    public final V1StudentRepository v1StudentRepository;
    public final ObjectMapper objectMapper;
    public final StudentIDGeneratorUtil studentIDGeneratorUtil;
    public V1StudentService(V1StudentRepository v1StudentRepository, ObjectMapper objectMapper, StudentIDGeneratorUtil studentIDGeneratorUtil) {
        this.v1StudentRepository = v1StudentRepository;
        this.objectMapper = objectMapper;
        this.studentIDGeneratorUtil = studentIDGeneratorUtil;
    }

    public List<StudentDTO> fetchAllStudent(){
        List<Student> students= v1StudentRepository.findAll();
        return students.stream().map(x -> objectMapper.convertValue(x,StudentDTO.class)).toList();
    }

    public List<StudentDTO> fetchAllStudentSortedBy(String sort_property_name){
        Sort sort= Sort.by(sort_property_name);
        List<Student> students= v1StudentRepository.findAll(sort);
        return students.stream().map(x -> objectMapper.convertValue(x,StudentDTO.class)).toList();
    }

    public void deleteStudent(String id){
         v1StudentRepository.deleteById(id);
    }

    public void addSingleStudent(StudentDTO studentDTO){
        studentDTO.setRollId(studentIDGeneratorUtil.generateByApacheText(10));
        v1StudentRepository.insert(objectMapper.convertValue(studentDTO,Student.class));
    }

    public StudentDTO updateSingleStudent(StudentDTO studentDTO){
        Student student= v1StudentRepository.save( objectMapper.convertValue(studentDTO,Student.class));
        return objectMapper.convertValue(student,StudentDTO.class);
    }

    public Long updateSingleStudentAge(String name,Integer age){
        return v1StudentRepository.findIncreaseAgeByName(name,age);

    }


}
