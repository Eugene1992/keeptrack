package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository interface for Project entity.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    /**
     * Returns project manager by specified managed project id.
     *
     * @param id identifier of the managed project
     * @return specified project manager of the specified project
     */
    @Query("select u from User u where u.managedProject.id = :id")
    User getProjectManager(@Param("id") Integer id);

    /**
     * Returns the total number of employees in specified project.
     *
     * @param id identifier of the required project
     * @return total number of employees of the specified project
     */
    @Query("select count(u) from User u where u.project.id = :id")
    Long getProjectEmployeesCount(@Param("id") Integer id);

    /**
     * Returns the total number of sprints in specified project.
     *
     * @param id identifier of the required project
     * @return total number of sprints of the specified project
     */
    @Query("select count(s) from Sprint s where s.project.id = :id")
    Long getProjectSprintsCount(@Param("id") Integer id);

    /**
     * Returns the project by specified name.
     *
     * @param name of the required project
     * @return specified project
     */
    @Query("select p from Project p where p.name = :name")
    Project getProjectByName(@Param("name") String name);

    /**
     * Returns the total number of projects in the system.
     *
     * @return total number of projects
     */
    @Query("select count(p) from Project p")
    Long getTotalProjectsCount();
}
