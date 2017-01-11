package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Employee;
import com.netcracker.keeptrack.model.Gender;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.service.EmployeeService;
import com.netcracker.keeptrack.service.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
})
@ActiveProfiles("hsql")
public class EmployeeServiceImplTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private EmployeeService employeeService;

    private EntityManager em;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        employee = new Employee("Evgeniy", "Deyneka", 10000, "deyneko55@gmail.com", Gender.MALE,
                LocalDate.of(1992, 12, 26), LocalDate.of(2016, 2, 10));
        employeeService.addEmployee(employee);
    }

    @Test
    public void addEmployee() throws Exception {
        final Employee RESULT = employeeService.getEmployeeById(employee.getId());
        Assert.assertEquals(employee, RESULT);
    }

    @Test
    public void deleteEmployee() throws Exception {
        employeeService.deleteEmployee(employee.getId());
        final Employee RESULT = employeeService.getEmployeeById(employee.getId());
        Assert.assertNull(RESULT);
    }

    @Test
    public void getEmployeeById() throws Exception {
        final Employee RESULT = employeeService.getEmployeeById(employee.getId());
        Assert.assertEquals(employee, RESULT);
    }

    @Test
    public void editEmployee() throws Exception {
        Employee updatedEmployee = employeeService.getEmployeeById(employee.getId());
        final String NEW_FIRST_NAME = "Denis";
        updatedEmployee.setFirstName(NEW_FIRST_NAME);
        employeeService.editEmployee(updatedEmployee);
        final String UPD_FIRST_NAME = employeeService.getEmployeeById(employee.getId()).getFirstName();
        Assert.assertEquals(NEW_FIRST_NAME, UPD_FIRST_NAME);
    }

}