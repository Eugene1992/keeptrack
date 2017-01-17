package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
    User getManagerByFirstAndLastName(@Param("firstName") String firstName,
                                      @Param("lastName") String lastName);

    @Transactional
    @Modifying
    @Query("update User u set u.project = :project where u.id = :id")
    void setManagerToProject(@Param("project") Project project,
                             @Param("id") Integer id);
}
