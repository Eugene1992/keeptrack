package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Employee;
import com.netcracker.keeptrack.repository.EmployeeRepository;
import com.netcracker.keeptrack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link EmployeeService} interface that provides methods for Employee
 * entity business logic.
 *
 * @see EmployeeService
 * @see Employee
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public void addEmployee(Employee employee) {
        repository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        repository.delete(id);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void editEmployee(Employee employee) {
        repository.saveAndFlush(employee);
    }
}
