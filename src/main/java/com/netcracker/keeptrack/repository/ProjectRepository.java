package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository interface for Project entity.
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("select u from User u where u.managedProject.id = :id")
    User getProjectManager(@Param("id") Integer id);

    @Query("select count(u) from User u where u.project.id = :id")
    Long getProjectEmployeesCount(@Param("id") Integer id);

    @Query("select count(s) from Sprint s where s.project.id = :id")
    Long getProjectSprintsCount(@Param("id") Integer id);
}
