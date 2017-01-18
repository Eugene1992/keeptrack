package com.netcracker.keeptrack.service.validators;

import com.netcracker.keeptrack.service.SprintService;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * Class provide validation behavior for the Sprint by implementing the
 * {@link org.springframework.validation.Validator} interface.
 *
 * @see org.springframework.validation.Validator
 */
@Component
public class SprintValidator implements Validator {

    @Autowired
    private SprintService sprintService;

    @Override
    public boolean supports(Class<?> aClass) {
        return SprintDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        SprintDTO sprint = (SprintDTO) obj;

        boolean isNew = sprint.getId() == null;
        String sprintName = sprint.getName();

        if (sprintName == null || sprintName.isEmpty()) {
            errors.rejectValue("name", "valid.required.sprint.name");
        }

        if (sprintName != null && sprintName.matches("[a-zA-Z0-9]{3,10}")) {
            errors.rejectValue("name", "valid.length.sprint.name");
        }

        if (isNew && sprintService.checkSprintName(sprintName)) {
            errors.rejectValue("name", "valid.existed.sprint.name");
        }

        String status = sprint.getStatus();

        if (status == null || status.isEmpty()) {
            errors.rejectValue("status", "valid.required.sprint.status");
        }

        String description = sprint.getDescription();

        if (description == null || description.isEmpty()) {
            errors.rejectValue("description", "valid.required.sprint.description");
        }

        if (description != null && description.matches("[a-zA-Z0-9]{10,100}")) {
            errors.rejectValue("description", "valid.length.sprint.description");
        }

        String projectId = sprint.getProjectId();

        if (projectId == null || projectId.isEmpty()) {
            errors.rejectValue("name", "valid.required.sprint.project");
        }

        String startDate = sprint.getStartDate();
        String endDate = sprint.getEndDate();

        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            LocalDate parsedStartDate = LocalDate.parse(sprint.getStartDate());
            LocalDate parsedEndDate = LocalDate.parse(sprint.getEndDate());
            if (parsedStartDate.isAfter(parsedEndDate)) {
                errors.rejectValue("startDate", "valid.invalid.sprint.start-date");
            }
            if (parsedEndDate.isBefore(parsedStartDate)) {
                errors.rejectValue("endDate", "valid.invalid.sprint.end-date");
            }
        } else {
            if (startDate == null || startDate.isEmpty()) {
                errors.rejectValue("startDate", "valid.required.sprint.start-date");
            }
            if (endDate == null || endDate.isEmpty()) {
                errors.rejectValue("endDate", "valid.required.sprint.end-date");
            }
        }
    }
}
