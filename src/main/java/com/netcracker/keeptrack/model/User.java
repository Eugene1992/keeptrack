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
@Table(name = "app_User")
public class User extends BaseEntity {

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
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The project is managed by the user if he is a project manager.
     */
    @OneToOne
    @JoinColumn(name = "managed_project_id", unique = true)
    private Project managedProject;

    /**
     * The tasks are created by the user if he is a project manager.
     */
    @OneToMany(mappedBy = "creator")
    private Set<Task> createdTasks;

    /**
     * The tasks are assigned by the user if he is an employee.
     */
    @OneToMany(mappedBy = "assigner")
    private Set<Task> assignedTasks;

    /**
     * User role for system functions separation.
     */
    private Role role;

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
    private int salary;

    /**
     * User email.
     */
    private String email;

    /**
     * User gender.
     */
    private Gender gender;

    /**
     * User birthday.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    /**
     * User hire day.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate hireDay;

    public User() {
    }

    public User(String username, String password, Role role, String firstName, String lastName,
                int salary, String email, Gender gender, LocalDate birthday, LocalDate hireDay) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.hireDay = hireDay;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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

        User user = (User) o;

        if (salary != user.salary) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (project != null ? !project.equals(user.project) : user.project != null) return false;
        if (managedProject != null ? !managedProject.equals(user.managedProject) : user.managedProject != null)
            return false;
        if (createdTasks != null ? !createdTasks.equals(user.createdTasks) : user.createdTasks != null) return false;
        if (assignedTasks != null ? !assignedTasks.equals(user.assignedTasks) : user.assignedTasks != null)
            return false;
        if (role != user.role) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (gender != user.gender) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        return hireDay != null ? hireDay.equals(user.hireDay) : user.hireDay == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (managedProject != null ? managedProject.hashCode() : 0);
        result = 31 * result + (createdTasks != null ? createdTasks.hashCode() : 0);
        result = 31 * result + (assignedTasks != null ? assignedTasks.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (hireDay != null ? hireDay.hashCode() : 0);
        return result;
    }
}
