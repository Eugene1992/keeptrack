package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository interface for Sprint entity.
 */
public interface SprintRepository extends JpaRepository<Sprint, Integer> {
}
