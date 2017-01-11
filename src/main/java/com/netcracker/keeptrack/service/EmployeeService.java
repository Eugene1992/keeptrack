package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Employee;

/**
 * Employee interface that describes methods for Employee entity business logic.
 *
 * @see Employee
 */
public interface EmployeeService {

    void addEmployee(Employee employee);

    void deleteEmployee(Integer id);

    Employee getEmployeeById(Integer id);

    void editEmployee(Employee employee);
}
