package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository interface for User entity.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
