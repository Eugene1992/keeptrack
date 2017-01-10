package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository interface for Employee entity.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
