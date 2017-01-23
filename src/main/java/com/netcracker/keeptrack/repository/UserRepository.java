package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository interface for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Returns employees who are not involved in one of the projects.
     *
     * @return list of free employees
     */
    @Query("select u from User u where u.project is null and u.role = 'EMPLOYEE'")
    List<User> getFreeEmployees();

    /**
     * Returns project managers who are not involved in one of the projects.
     *
     * @return list of free project managers
     */
    @Query("select u from User u where u.managedProject is null and u.role = 'PM'")
    List<User> getFreeManagers();

    /**
     * Returns the user by specified first and last names.
     *
     * @param firstName of the required user
     * @param lastName of the required user
     * @return specified user
     */
    @Query("select u from User u where u.firstName = :firstName and u.lastName = :lastName")
    User getUserByFirstAndLastName(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName);

    /**
     * Returns the user by specified username.
     *
     * @param username of the required user
     * @return specified user
     */
    @Query("select u from User u where u.username = :username")
    User getUserByUsername(@Param("username") String username);

    /**
     * Returns the user by specified email.
     *
     * @param email of the required user
     * @return specified user
     */
    @Query("select u from User u where u.email = :email")
    User getUserByEmail(@Param("email") String email);

    /**
     * Returns all employees in the system.
     *
     * @return list of all employees
     */
    @Query("select u from User u where u.role = 'EMPLOYEE'")
    List<User> getAllEmployees();

    /**
     * Returns all managers in the system.
     *
     * @return list of all employees
     */
    @Query("select u from User u where u.role = 'PM'")
    List<User> getAllManagers();

    /**
     * Returns the total number of customers in the system.
     *
     * @return total number of customers
     */
    @Query("select count(u) from User u where u.role = 'CUSTOMER'")
    Long getTotalCustomersCount();

    /**
     * Returns the total number of employees in the system.
     *
     * @return total number of employees
     */
    @Query("select count(u) from User u where u.role = 'EMPLOYEE'")
    Long getTotalEmployeesCount();
}
