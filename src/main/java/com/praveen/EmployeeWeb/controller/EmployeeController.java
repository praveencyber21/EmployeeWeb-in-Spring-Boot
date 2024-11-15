package com.praveen.EmployeeWeb.controller;

import com.praveen.EmployeeWeb.model.ApiResponse;
import com.praveen.EmployeeWeb.model.Employee;
import com.praveen.EmployeeWeb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employee")
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployee() {
        return ResponseEntity.ok(new ApiResponse<>(service.getAllEmployee(), 0, "Details fetched successfully", true));
    }

    @PostMapping("/employee")
    public ResponseEntity<ApiResponse<Object>> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = service.addEmployee(employee);

        if(savedEmployee != null)
                return ResponseEntity.ok(new ApiResponse<>(employee, 0, "Employee added successfully", true));
        else
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>(new HashMap<String, Object>(), 400, "Failed", false));

    }

//    @GetMapping("/employee/{empName}")
//    public ResponseEntity<ApiResponse<Object>> getEmployeeByName(@PathVariable String empName) {
//
//        Employee employee = service.getEmployeeByName(empName);
//
//        if (employee != null) {
//            return ResponseEntity.ok(new ApiResponse<>(employee, 200, "Employee details fetched successfully", true));
//        } else {
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ApiResponse<>(new HashMap<String, Object>(), 200, "Employee not found", false));
//        }
//    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<Object>> getEmployeeById(@PathVariable int id) {
        Employee employee = service.getEmployeeById(id);

        if(employee != null) {
            return ResponseEntity.ok(new ApiResponse<>(employee, 200, "Employee details fetches successfully", true));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(new HashMap<String, Object>(), 200, "Employee not found", false));
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteEmployeeById(@PathVariable int id) {
        String result = service.deleteEmployeeById(id);

        if("Employee deleted successfully".equals(result))
            return ResponseEntity.ok(new ApiResponse<>(new HashMap<String, Object>(), 200, "Employee deleted successfully", true));

        return ResponseEntity.internalServerError()
                .body(new ApiResponse<>(new HashMap<String, Object>(), 200, "Employee not found", false));
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<Object>> updateEmployeeById(@PathVariable int id, @RequestBody Employee employee) {
        String result = service.updateEmployeeById(id, employee);

        if("Employee details updated".equals(result))
            return ResponseEntity.ok(new ApiResponse<>(new HashMap<String, Object>(), 0, "Employee details updated", true));

        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(new HashMap<String, Object>(), 0, "Employee not found", false));

    }

}
