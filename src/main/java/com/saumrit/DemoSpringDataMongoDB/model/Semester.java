package com.saumrit.DemoSpringDataMongoDB.model;

import lombok.Data;

@Data
public class Semester {

    public String semesterId;
    public Long sgpa;
    public Boolean isPass;
}
