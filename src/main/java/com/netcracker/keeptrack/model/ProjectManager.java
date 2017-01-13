package com.netcracker.keeptrack.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * A person who is hired to work for a project.
 *
 * @see Gender
 */
@Entity
public class ProjectManager extends BaseEntity {

    @OneToMany(mappedBy = "creator")
    private Set<Sprint> sprints;

    @OneToMany(mappedBy = "creator")
    private Set<Task> tasks;


    Project project;
}
