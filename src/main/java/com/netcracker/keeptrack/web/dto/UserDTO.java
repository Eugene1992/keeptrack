package com.netcracker.keeptrack.web.dto;

import com.netcracker.keeptrack.model.User;

/**
 * User entity Data Transfer Object.
 */
public class UserDTO {

    /**
     * User id.
     */
    private String id;

    /**
     * Username for system access.
     */
    private String username;

    /**
     * User password.
     */
    private String password;

    /**
     * The project in which the user works.
     */
    private String projectId;

    /**
     * User role for system functions separation.
     */
    private String role;

    /**
     * User first name.
     */
    private String firstName;

    /**
     * User last name.
     */
    private String lastName;

    /**
     * User salary.
     */
    private String salary;

    /**
     * User email.
     */
    private String email;

    /**
     * User gender.
     */
    private String gender;

    /**
     * User birthday.
     */
    private String birthday;

    /**
     * User hire day.
     */
    private String hireDay;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = String.valueOf(user.getId());
        this.projectId = String.valueOf(user.getProject().getId());
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().name();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.salary = String.valueOf(user.getSalary());
        this.email = user.getEmail();
        this.gender = user.getGender().name();
        this.birthday = user.getBirthday().toString();
        this.hireDay = user.getHireDay().toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHireDay() {
        return hireDay;
    }

    public void setHireDay(String hireDay) {
        this.hireDay = hireDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
