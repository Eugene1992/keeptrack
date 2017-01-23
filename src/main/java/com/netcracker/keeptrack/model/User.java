package com.netcracker.keeptrack.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * A person who is hired to work for a project.
 *
 * @see Gender
 */
@Entity
@Table(name = "app_User", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
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
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "managed_project_id", unique = true)
    private Project managedProject;

    /**
     * The tasks are created by the user if he is a project manager.
     */
    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Task> createdTasks;

    /**
     * The tasks are assigned by the user if he is an employee.
     */
    @OneToMany(mappedBy = "assigner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Task> assignedTasks;

    /**
     * User role for system functions separation.
     */
    @Enumerated(value = EnumType.STRING)
    private Role role;

    /**
     * User first name.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * User last name.
     */
    @Column(name = "last_name")
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
    @Enumerated(value = EnumType.STRING)
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

    public User(String username, String password, Project project, Project managedProject, Set<Task> createdTasks, Set<Task> assignedTasks, Role role,
                String firstName, String lastName, int salary, String email, Gender gender, LocalDate birthday, LocalDate hireDay) {
        this.username = username;
        this.password = password;
        this.project = project;
        this.managedProject = managedProject;
        this.createdTasks = createdTasks;
        this.assignedTasks = assignedTasks;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getManagedProject() {
        return managedProject;
    }

    public void setManagedProject(Project managedProject) {
        this.managedProject = managedProject;
    }

    public Set<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(Set<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public Set<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(Set<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (salary != user.salary) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
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

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", project=" + project
                + ", managedProject=" + managedProject
                + ", createdTasks=" + createdTasks
                + ", assignedTasks=" + assignedTasks
                + ", role=" + role
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", salary=" + salary
                + ", email='" + email + '\''
                + ", gender=" + gender
                + ", birthday=" + birthday
                + ", hireDay=" + hireDay
                + '}';
    }
}
