package com.netcracker.keeptrack.model;

import java.time.LocalDate;

/**
 * A person who is hired to work for a project.
 *
 * @see Gender
 */
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
    private LocalDate birthday;

    /**
     * Employee hire day.
     */
    private LocalDate hireDay;

    public Employee(int id, String firstName, String lastName, int salary, String email, Gender gender, LocalDate birthday, LocalDate hireDay) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.hireDay = hireDay;
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
}
