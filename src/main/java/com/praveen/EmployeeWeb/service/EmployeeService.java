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

    public Employee getEmployeeById(int id) {
        return repo.findById(id).orElse(null);
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

    public String updateEmployeeById(int id, Employee newEmployee) {
        Employee employee = repo.findById(id).orElse(null);

        if (employee != null) {
            newEmployee.setId(employee.getId());
            repo.save(newEmployee);
            return "Employee details updated";
        }
        else
            return "Employee not found";

    }

}
