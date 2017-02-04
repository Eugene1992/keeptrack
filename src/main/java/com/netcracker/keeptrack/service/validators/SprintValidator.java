package com.netcracker.keeptrack.service.validators;

import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.service.SprintService;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * Class provide validation behavior for the Sprint by implementing the
 * {@link Validator} interface.
 * Also inherited from the base validator that allows common validation methods.
 *
 * @see Validator
 * @see BaseValidator
 */
@Component
public class SprintValidator extends BaseValidator implements Validator {

    @Autowired
    private SprintService sprintService;

    /**
     * Validation error messages.
     */
    private static final String NAME_REGEX = "[\\w ]{3,10}";
    private static final String NAME_LENGTH_MSG = "valid.length.sprint.name";
    private static final String STATUS_MSG = "valid.required.status";
    private static final String DESCRIPTION_MSG = "valid.required.description";
    private static final String PROJECT_MSG = "valid.required.sprint.project";
    private static final String SPRINT_DELAY_MSG = "valid.sprint.delay";

    /**
     * Register which {@code Class} must support the validation.
     *
     * @param aClass validated {@link Class} instance
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return SprintDTO.class.isAssignableFrom(aClass);
    }

    /**
     * Validates Sprint DTO fields.
     * If we add new sprint(request comes from adding form) also check whether
     * the sprint exists with the specified name.
     *
     * @param obj validated object
     * @param errors validation errors
     */
    @Override
    public void validate(Object obj, Errors errors) {
        SprintDTO sprint = (SprintDTO) obj;

        boolean isNew = sprint.getId() == null;
        String sprintName = sprint.getName();

        validateName(errors, sprintName, NAME_LENGTH_MSG, NAME_REGEX);

        if (isNew && sprintService.checkSprintName(sprintName)) {
            errors.rejectValue("name", "valid.existed.sprint.name");
        }

        String status = sprint.getStatus();
        validateNotNullAndEmpty(errors, "status", status, STATUS_MSG);

        String description = sprint.getDescription();
        validateNotNullAndEmpty(errors, "description", description, DESCRIPTION_MSG);

        String projectId = sprint.getProjectId();
        validateNotNullAndEmpty(errors, "projectId", projectId, PROJECT_MSG);

        String startDate = sprint.getStartDate();
        String endDate = sprint.getEndDate();
        validateDates(errors, startDate, endDate);

        Sprint latestSprint = sprintService.getProjectLatestSprint(Integer.parseInt(projectId));
        if (latestSprint != null && startDate != null && !startDate.isEmpty()) {
            LocalDate newSprintStartDate = LocalDate.parse(startDate);
            if (latestSprint.getEndDate().isAfter(newSprintStartDate)) {
                errors.rejectValue("startDate", SPRINT_DELAY_MSG);
            }
        }
    }
}
