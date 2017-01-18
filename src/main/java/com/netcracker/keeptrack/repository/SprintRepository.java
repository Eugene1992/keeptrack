package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository interface for Sprint entity.
 */
public interface SprintRepository extends JpaRepository<Sprint, Integer> {

    @Query("select s from Sprint s where s.name  = :name")
    Sprint getSprintByName(@Param("name") String name);
}
