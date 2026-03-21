package com.saumrit.DemoSpringDataMongoDB.controller;

import com.saumrit.DemoSpringDataMongoDB.data.StudentDTO;
import com.saumrit.DemoSpringDataMongoDB.service.V1StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/students")
public class V1StudentController {
    public V1StudentService v1StudentService;

    public V1StudentController(V1StudentService v1StudentService) {
        this.v1StudentService = v1StudentService;
    }

    @Operation(summary = "Api to get All Students",
    description = "Api to get All Students")
    @GetMapping("/getAllStudents")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<StudentDTO> getAllStudents(){
        return v1StudentService.fetchAllStudent();
    }

    @Operation(summary = "Api to get All Students with Sorting applied",
            description = "Api to get All Students in a sorted Order")
    @GetMapping("/getAllStudentsSortedBy")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<StudentDTO> getAllStudentsSortedBy(@RequestParam String sortPropertyName){
        return v1StudentService.fetchAllStudentSortedBy(sortPropertyName);
    }

    @Operation(summary = "Api to add a StudentDTO",
            description = "Api to add a StudentDTO")
    @PostMapping("/addSingleStudent")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addSingleStudent(@RequestBody StudentDTO studentDTO){
        v1StudentService.addSingleStudent(studentDTO);
    }

    @Operation(summary = "Api to remove a StudentDTO",
            description = "Api to remove a StudentDTO")
    @DeleteMapping("/{id}/removeStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void deleteSingleStudent(@PathVariable String id){
        v1StudentService.deleteStudent(id);
    }

    @Operation(summary = "Api to Update a StudentDTO",
            description = "Api to Update a StudentDTO")
    @PutMapping("/updateStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public StudentDTO updateSingleStudent(@RequestBody StudentDTO studentDTO){
        return v1StudentService.updateSingleStudent(studentDTO);
    }

    @Operation(summary = "Api to patch update a StudentDTO",
            description = "Api to patch update a StudentDTO")
    @PatchMapping("/patchStudentInformation")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public StudentDTO patchUpdateSingleStudent(@RequestBody StudentDTO studentDTO){
        return v1StudentService.updateSingleStudent(studentDTO);
    }

    @Operation(summary = "Api to patch update a StudentDTO",
            description = "Api to patch update a StudentDTO")
    @PatchMapping("/customWork/{fname}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Long workWithRepositoryMethod(@PathVariable String fname){
        return v1StudentService.updateSingleStudentAge(fname,1);
    }





}
