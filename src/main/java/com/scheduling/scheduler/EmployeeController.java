package com.scheduling.scheduler;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody CreateEmployeeRequest request) {
        // 1. make a new empty Employee entity
        // 2. use the setters to copy name, email, role from request into it
        // 3. save it via employeeRepository.save(...) which returns the saved entity (now with an id)
        // 4. build and return an EmployeeResponse from the saved entity's getters

        /**
         * Here we are taking the values from the request and
         * inserting those values into the Employee Entity or table
         * Then
         * we are saving and storing in newEmployee through employeeRepository which is a JPA interface
         * that connects to the database.
         * to send the response that comes from the database,
         * we use EmployeeResponse, in which we have the request saved along with the auto-generated id.
         */

        Employee employee = new Employee();

        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setRole(request.role());

        Employee newEmployee = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = new EmployeeResponse(
                newEmployee.getId(), newEmployee.getName(), newEmployee.getEmail(), newEmployee.getRole()
        );

        return employeeResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> readEmployee(@PathVariable Long id){

        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            Employee emp = employee.get();
            EmployeeResponse employeeResponse = new EmployeeResponse(
                    emp.getId(), emp.getName(), emp.getEmail(), emp.getRole()
            );

            return ResponseEntity.ok(employeeResponse);
        }
    }

    @GetMapping
    public List<EmployeeResponse> readAll(){
        List<Employee> employees = employeeRepository.findAll();

        /*
        * We are getting all the records of employees from employeeRepository
        * this list is converted to employeeResponse list by adding each employee to this list.
        **/

        return employees.stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(), employee.getName(), employee.getEmail(), employee.getRole()))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody CreateEmployeeRequest request) {
        Optional<Employee> foundEmployee = employeeRepository.findById(id);

        if (foundEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = foundEmployee.get();
        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setRole(request.role());
        employeeRepository.save(employee);

        EmployeeResponse response = new EmployeeResponse(
                employee.getId(), employee.getName(), employee.getEmail(), employee.getRole());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id){
        Optional<Employee> foundEmployee = employeeRepository.findById(id);

        if (!foundEmployee.isPresent()){
            return ResponseEntity.notFound().build();
        }

        employeeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
