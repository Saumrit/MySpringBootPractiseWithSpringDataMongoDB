package com.saumrit.DemoSpringDataMongoDB.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saumrit.DemoSpringDataMongoDB.data.enums.StudentDTO;
import com.saumrit.DemoSpringDataMongoDB.model.Address;
import com.saumrit.DemoSpringDataMongoDB.model.Student;
import com.saumrit.DemoSpringDataMongoDB.repository.V2StudentRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class V2StudentService {
    public final V2StudentRepository v2StudentRepository;
    public final ObjectMapper objectMapper;

    public V2StudentService(V2StudentRepository v2StudentRepository, ObjectMapper objectMapper) {
        this.v2StudentRepository = v2StudentRepository;
        this.objectMapper = objectMapper;
    }

    public List<StudentDTO> fetchAllStudent(){
        List<Student> students= v2StudentRepository.getAllStudents();
        return students.stream().map(x -> objectMapper.convertValue(x, StudentDTO.class)).toList();
    }

    public List<StudentDTO> fetchAllStudentSortedByDOB(){
        List<Student> students=  v2StudentRepository.getAllStudentsSortedByDOB();
        return students.stream().map(x -> objectMapper.convertValue(x, StudentDTO.class)).toList();
    }

    public long deleteStudent(String id){
        return v2StudentRepository.deleteSingleStudent(id);
    }

    public void addSingleStudent(StudentDTO studentDTO){
        Student student=objectMapper.convertValue(studentDTO, Student.class);
        v2StudentRepository.addSingleStudent(student);
    }

    public StudentDTO updateSingleStudent(StudentDTO studentDTO){
        Student student=objectMapper.convertValue(studentDTO, Student.class);
        Student updatedValue= v2StudentRepository.updateSingleStudent(student);
        return objectMapper.convertValue(updatedValue, StudentDTO.class);
    }

    public List<StudentDTO> fetchAllGenZStudent() throws ParseException {
        List<Student> students=null;
        try {
           students = v2StudentRepository.getAllGenZs();
        }
        catch (ParseException e){
            System.out.println("caught Exception while parsing result");
            throw new ParseException(e.getMessage(),e.getErrorOffset());
        }
        return students.stream().map(x -> objectMapper.convertValue(x, StudentDTO.class)).toList();
    }

    public List<StudentDTO> fetchAllNRIStudent() throws Exception {
        List<Student> students=null;
        try {
            students = v2StudentRepository.getNRIs();
        }
        catch (Exception e){
            System.out.println("caught Exception while parsing NRI result");
            throw new Exception("caught Exception while parsing NRI result");
        }
        return students.stream().map(x -> objectMapper.convertValue(x, StudentDTO.class)).toList();
    }

    public long updateAddressForSingleStudentWithRollAndBranch(Address address,String roll,String branch) throws Exception {
        long updatedValue= v2StudentRepository.updateAddress(address,roll,branch);
        if(updatedValue != 1)
            throw new Exception("Either No or Multiple Documents are updated !!");
        return updatedValue;
    }

}
