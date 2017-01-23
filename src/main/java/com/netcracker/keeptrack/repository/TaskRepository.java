package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository interface for Task entity.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    /**
     * Returns the task by specified name.
     *
     * @param name of the required task
     * @return specified task
     */
    @Query("select t from Task t where t.name  = :name")
    Task getTaskByName(@Param("name") String name);

    /**
     * Returns the latest by date specified number of tasks.
     *
     * @param pageable abstract interface for pagination information
     * @return list of latest task
     */
    @Query("select t from Task t order by t.startDate desc")
    List<Task> getLatestTasks(Pageable pageable);

    /**
     * Returns the total number of tasks.
     *
     * @return total number of tasks
     */
    @Query("select count(t) from Task t")
    Long getTotalTasksCount();
}
