package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository interface for Sprint entity.
 */
public interface StatisticRepository extends JpaRepository<User, Integer> {

    @Query("select count(u) from User u where u.role = 'CUSTOMER'")
    Long getTotalCustomersCount();

    @Query("select count(p) from Project p")
    Long getTotalProjectsCount();

    @Query("select count(t) from Task t")
    Long getTotalTasksCount();

    @Query("select count(u) from User u where u.role = 'EMPLOYEE'")
    Long getTotalEmployeesCount();

    @Query("select t from Task t order by t.startDate desc")
    List<Task> getLatestTasks(Pageable pageable);
}
