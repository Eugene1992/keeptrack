package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository interface for Sprint entity.
 */
@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer> {

    /**
     * Returns the sprint by specified name.
     *
     * @param name of the required sprint
     * @return specified sprint
     */
    @Query("select s from Sprint s where s.name  = :name")
    Sprint getSprintByName(@Param("name") String name);

    /**
     * Returns specified project sprints.
     *
     * @param projectId id of the specified project
     * @return project sprints
     */
    @Query("select s from Sprint s where s.project.id = :id")
    List<Sprint> getProjectSprints(@Param("id") Integer projectId);
}
