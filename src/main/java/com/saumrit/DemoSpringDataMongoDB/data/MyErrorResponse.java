package com.saumrit.DemoSpringDataMongoDB.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyErrorResponse {

    public Integer code;
    public String message;
}
