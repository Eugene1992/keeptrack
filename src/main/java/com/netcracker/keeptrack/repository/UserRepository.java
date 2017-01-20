package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository interface for User entity.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.project is null and u.role = 'EMPLOYEE'")
    List<User> getFreeEmployees();

    @Query("select u from User u where u.managedProject is null and u.role = 'PM'")
    List<User> getFreeManagers();

    @Query("select u from User u where u.firstName = :firstName and u.lastName = :lastName")
    User getUserByFirstAndLastName(@Param("firstName") String firstName,
                                   @Param("lastName") String lastName);

    @Query("select u from User u where u.username = :username")
    User getUserByUsername(@Param("username") String username);

    @Query("select u from User u where u.email = :email")
    User getUserByEmail(@Param("email") String email);

    @Query("select u from User u where u.role = 'EMPLOYEE'")
    List<User> getAllEmployees();

    @Query("select u from User u where u.role = 'PM'")
    List<User> getAllManagers();
}
