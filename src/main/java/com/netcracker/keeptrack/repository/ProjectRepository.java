package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository interface for Project entity.
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
