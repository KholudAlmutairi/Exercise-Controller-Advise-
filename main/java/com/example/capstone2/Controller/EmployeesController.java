package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Employees;
import com.example.capstone2.Repository.EmployeesReopsitory;
import com.example.capstone2.Service.EmployeesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;
    Logger logger= LoggerFactory.getLogger(EmployeesController.class);

    @GetMapping("/get")
    public ResponseEntity getAllEmployees(){
        logger.info("get all employees");
        return ResponseEntity.status(200).body(employeesService.getAllEmployees());}


    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employees employees){
        logger.info("inside add employees");
        employeesService.addEmployees(employees);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable Integer id, @RequestBody @Valid Employees employees ){
        logger.info("inside update employees");
        employeesService.updateEmployees(id,employees);
        return ResponseEntity.status(200).body(new ApiResponse( "Employee updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id){
        logger.info("inside delete employees");
        employeesService.deleteEmployees(id);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted!"));
    }























}
