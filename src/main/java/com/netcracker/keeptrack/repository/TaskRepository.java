package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository interface for Task entity.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
