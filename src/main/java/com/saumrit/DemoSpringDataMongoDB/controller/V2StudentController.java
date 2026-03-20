package com.saumrit.DemoSpringDataMongoDB.controller;


import com.saumrit.DemoSpringDataMongoDB.data.enums.StudentDTO;
import com.saumrit.DemoSpringDataMongoDB.model.Address;
import com.saumrit.DemoSpringDataMongoDB.service.V2StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/v2/students")
public class V2StudentController {
    public V2StudentService v2StudentService;

    public V2StudentController(V2StudentService v2StudentService) {
        this.v2StudentService = v2StudentService;
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
        return v2StudentService.fetchAllStudent();
    }

    @Operation(summary = "Api to get All Students with Sorting applied on DOBs",
            description = "Api to get All Students in a sorted Order of their Date Of Birth")
    @GetMapping("/getAllStudentsSortedByDOB")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<StudentDTO> getAllStudentsSortedByDOB(){
        return v2StudentService.fetchAllStudentSortedByDOB();
    }

    @Operation(summary = "Api to add a StudentDTO",
            description = "Api to add a StudentDTO")
    @PostMapping("/addSingleStudent")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addSingleStudent(@RequestBody  StudentDTO studentDTO){
        v2StudentService.addSingleStudent(studentDTO);
    }

    @Operation(summary = "Api to remove a StudentDTO",
            description = "Api to remove a StudentDTO")
    @DeleteMapping("/{id}/removeStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void deleteSingleStudent(@PathVariable @NotNull String id){
        v2StudentService.deleteStudent(id);
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
        Assert.notNull(studentDTO,"requestBody cannot be null");
        Assert.notNull(studentDTO.getId(),"Id cannot be null while Updating");
        return v2StudentService.updateSingleStudent(studentDTO);
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
        Assert.notNull(studentDTO,"requestBody cannot be null");
        Assert.notNull(studentDTO.getId(),"Id cannot be null while Updating");
        Assert.notNull(studentDTO.getName(),"Name cannot be null while Updating");
        Assert.notNull(studentDTO.getCollege(),"College cannot be null while Updating");
        return v2StudentService.updateSingleStudent(studentDTO);
    }

    @Operation(summary = "Get get All GenZ Students",
            description = "Get All GenZ Students")
    @GetMapping("/getAllGenZs")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<StudentDTO> getAllGenZsStudents() throws ParseException {
        return v2StudentService.fetchAllGenZStudent();
    }

    @Operation(summary = "Get get All NRI Students",
            description = "Get All NRI Students")
    @GetMapping("/getAllNRIs")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<StudentDTO> getAllNRIStudents() throws Exception {
        return v2StudentService.fetchAllNRIStudent();
    }

    @Operation(summary = "Api to Update Address for a Student",
            description = "Api to Update Address for a Student, here specific Address like city, state etc can be updated individually" +
                    " or as a whole address also")
    @PatchMapping("/modifyAddress/{rollNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public long updateAddress(@RequestBody Address address,
                                    @PathVariable(name = "rollNumber",required = true,value = "rollNumber") String rollNumber,
                                    @RequestParam(required = false,name = "branch") String branch) throws Exception {
        Assert.notNull(address,"address cannot be null to Update");
        Assert.notNull(address,"address cannot be null to Update");
        return v2StudentService.updateAddressForSingleStudentWithRollAndBranch(address,rollNumber,branch);
    }





}
