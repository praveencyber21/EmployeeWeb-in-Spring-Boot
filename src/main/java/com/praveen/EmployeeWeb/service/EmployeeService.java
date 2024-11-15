package com.praveen.EmployeeWeb.service;

import com.praveen.EmployeeWeb.model.Address;
import com.praveen.EmployeeWeb.model.Employee;
import com.praveen.EmployeeWeb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public String updateEmployeeById(int id, Employee updatedEmployee) {

        Optional<Employee> employee = repo.findById(id);

        if (employee.isPresent()) {
            Employee existingEmployee = employee.get();

            if(updatedEmployee.getEmpName() != null)
                existingEmployee.setEmpName(updatedEmployee.getEmpName());

            if(updatedEmployee.getSalary() != null)
                existingEmployee.setSalary(updatedEmployee.getSalary());

            if(updatedEmployee.getAddress() != null){
               Address existedAddress = existingEmployee.getAddress();
               Address updatedAddress = updatedEmployee.getAddress();

               if(updatedAddress.getCity() != null && !updatedAddress.getCity().equals(existedAddress.getCity())) {
                   existedAddress.setCity(updatedAddress.getCity());
               }

                System.out.println(existedAddress.getId());
                System.out.println(existedAddress.getCity());
                System.out.println(updatedAddress.getCity());
            }

            repo.save(existingEmployee);
            return "Employee details updated";
        }

            return "Employee not found";

    }

}
