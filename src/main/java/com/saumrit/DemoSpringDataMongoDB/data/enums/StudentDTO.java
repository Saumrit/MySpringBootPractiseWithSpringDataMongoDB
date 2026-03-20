package com.saumrit.DemoSpringDataMongoDB.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saumrit.DemoSpringDataMongoDB.model.Address;
import com.saumrit.DemoSpringDataMongoDB.model.Semester;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class StudentDTO {
    public String id;
    public String rollId;
    public String name;
    public String standard;
    public Integer age;
    public Address address;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    public Date dateOfBirth;
    public Boolean nriStatus;
    public Float cgpa;
    public String college;
    public List<Semester> semesters;
    public Branch branch;
    public Integer passOutYear;
}
