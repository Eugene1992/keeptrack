package com.netcracker.keeptrack.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;

/**
 * A person who is hired to work for a project.
 *
 * @see Gender
 */
@Entity
public class Employee extends BaseEntity {

    /**
     * Employee first name.
     */
    private String firstName;

    /**
     * Employee last name.
     */
    private String lastName;

    /**
     * Employee salary.
     */
    private int salary;

    /**
     * Employee email.
     */
    private String email;

    /**
     * Employee gender.
     */
    private Gender gender;

    /**
     * Employee birthday.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    /**
     * Employee hire day.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate hireDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Project project;

    @OneToMany(mappedBy = "assigner")
    private Set<Task> tasks;

    public Employee(String firstName, String lastName, int salary, String email, Gender gender, LocalDate birthday, LocalDate hireDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.hireDay = hireDay;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (salary != employee.salary) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        if (gender != employee.gender) return false;
        if (birthday != null ? !birthday.equals(employee.birthday) : employee.birthday != null) return false;
        return hireDay != null ? hireDay.equals(employee.hireDay) : employee.hireDay == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (hireDay != null ? hireDay.hashCode() : 0);
        return result;
    }
}
