package com.saumrit.DemoSpringDataMongoDB.service;

import com.saumrit.DemoSpringDataMongoDB.data.enums.StudentDTO;
import com.saumrit.DemoSpringDataMongoDB.model.Student;
import com.saumrit.DemoSpringDataMongoDB.repository.V1StudentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class V1StudentService {

    public final V1StudentRepository v1StudentRepository;
    public final ObjectMapper objectMapper;

    public V1StudentService(V1StudentRepository v1StudentRepository, ObjectMapper objectMapper) {
        this.v1StudentRepository = v1StudentRepository;
        this.objectMapper = objectMapper;
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
        v1StudentRepository.insert(objectMapper.convertValue(studentDTO,Student.class));
    }

    public StudentDTO updateSingleStudent(StudentDTO studentDTO){
        Student student= v1StudentRepository.save( objectMapper.convertValue(studentDTO,Student.class));
        return objectMapper.convertValue(student,StudentDTO.class);
    }


}
