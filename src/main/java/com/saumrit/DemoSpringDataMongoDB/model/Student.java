package com.saumrit.DemoSpringDataMongoDB.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saumrit.DemoSpringDataMongoDB.data.enums.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "student")
public class Student {

    @Id
    public String id;

    public String name;
    public String standard;
    public Integer age;
    public Address address;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    public Date dateOfBirth;
    public Boolean isNRI;
    public Float cgpa;
    public String college;
    public List<Semester> semesters;
    public Branch branch;


}
