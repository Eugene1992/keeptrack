package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository interface for Request entity.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
