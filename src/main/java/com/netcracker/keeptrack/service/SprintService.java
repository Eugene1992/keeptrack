package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Sprint;
import org.springframework.stereotype.Service;

/**
 * Sprint interface that describes methods for Sprint entity business logic.
 *
 * @see Sprint
 */
@Service
public interface SprintService {

    void addSprint(Sprint sprint);

    void deleteSprint(Integer id);

    Sprint getSprintById(Integer id);

    void editSprint(Sprint sprint);
}
