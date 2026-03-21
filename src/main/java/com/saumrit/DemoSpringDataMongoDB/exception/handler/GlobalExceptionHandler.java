package com.saumrit.DemoSpringDataMongoDB.exception.handler;

import com.mongodb.MongoClientException;
import com.saumrit.DemoSpringDataMongoDB.data.MyErrorResponse;
import org.bson.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({MongoClientException.class})
    public MyErrorResponse noSuchElement(){
        return new MyErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NullPointerException.class, JsonParseException.class})
    public MyErrorResponse nullCheck(){
        return new MyErrorResponse(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchElementException.class})
    public MyErrorResponse noElementFound(){
        return new MyErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name());
    }
}
