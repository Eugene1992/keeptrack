package com.netcracker.keeptrack.service.validators;

import com.netcracker.keeptrack.service.ProjectService;
import com.netcracker.keeptrack.web.dto.ProjectDTO;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class provide validation behavior for the Project by implementing the
 * {@link Validator} interface.
 *
 * @see Validator
 */
@Component
public class ProjectValidator implements Validator {

    @Autowired
    private ProjectService projectService;

    @Override
    public boolean supports(Class<?> aClass) {
        return SprintDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ProjectDTO project = (ProjectDTO) obj;

        final Pattern PROJECT_NAME_PATTERN = Pattern.compile("\\w{3,20}");
        final Pattern PROJECT_DESCRIPTION_PATTERN = Pattern.compile("\\w{10,}");
        final Matcher PROJECT_NAME_MATCHER = PROJECT_NAME_PATTERN.matcher(project.getName());
        final Matcher PROJECT_DESCRIPTION_MATCHER = PROJECT_DESCRIPTION_PATTERN.matcher(project.getDescription());

        boolean isNew = project.getId() == null;
        String projectName = project.getName();

        if (projectName == null || projectName.isEmpty()) {
            errors.rejectValue("name", "valid.required.name");
        } else if (!PROJECT_NAME_MATCHER.matches()) {
            errors.rejectValue("name", "valid.length.project.name");
        }

        if (isNew && projectService.checkProjectName(projectName)) {
            errors.rejectValue("name", "valid.existed.project.name");
        }

        String status = project.getStatus();

        if (status == null || status.isEmpty()) {
            errors.rejectValue("status", "valid.required.status");
        }

        String description = project.getDescription();

        if (description == null || description.isEmpty()) {
            errors.rejectValue("description", "valid.required.description");
        } else if (!PROJECT_DESCRIPTION_MATCHER.matches()) {
            errors.rejectValue("description", "valid.length.project.description");
        }

        String projectId = project.getManagerId();

        if (projectId == null || projectId.isEmpty()) {
            errors.rejectValue("managerId", "valid.required.project.manager");
        }

        String startDate = project.getStartDate();
        String endDate = project.getEndDate();

        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            LocalDate parsedStartDate = LocalDate.parse(project.getStartDate());
            LocalDate parsedEndDate = LocalDate.parse(project.getEndDate());
            if (parsedStartDate.isAfter(parsedEndDate)) {
                errors.rejectValue("startDate", "valid.invalid.start-date");
            }
            if (parsedEndDate.isBefore(parsedStartDate)) {
                errors.rejectValue("endDate", "valid.invalid.end-date");
            }
        } else {
            if (startDate == null || startDate.isEmpty()) {
                errors.rejectValue("startDate", "valid.required.start-date");
            }
            if (endDate == null || endDate.isEmpty()) {
                errors.rejectValue("endDate", "valid.required.end-date");
            }
        }
    }

    public static void main(String[] args) {

    }
}
