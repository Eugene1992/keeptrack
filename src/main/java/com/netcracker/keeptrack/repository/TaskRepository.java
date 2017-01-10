package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository interface for Task entity.
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
