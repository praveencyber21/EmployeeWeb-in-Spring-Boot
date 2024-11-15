package com.praveen.EmployeeWeb.service;

import com.praveen.EmployeeWeb.model.Employee;
import com.praveen.EmployeeWeb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    @Autowired
    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployee() {
        return repo.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return repo.save(employee);
    }

    public Employee getEmployeeByName(String empName) {
        return repo.findByEmpName(empName);
    }

    public String deleteEmployeeById(int id) {
        Employee employee = repo.findById(id).orElse(null);

        if(employee != null){
            repo.deleteById(id);
            return "Employee deleted successfully";
        }
        else
            return "Employee not found";

    }

    public String updateEmployeeById(int id) {
        Employee employee = repo.findById(id).orElse(null);

        if(employee != null){
            repo.save(employee);
            return "Employee details updated";
        }
        else
            return "Employee not found";

    }

}
