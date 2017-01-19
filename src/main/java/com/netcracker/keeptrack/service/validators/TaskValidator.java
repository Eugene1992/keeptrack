package com.netcracker.keeptrack.service.validators;

import com.netcracker.keeptrack.web.dto.TaskDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Class provide validation behavior for the Sprint by implementing the
 * {@link Validator} interface.
 *
 * @see Validator
 */
@Component
public class TaskValidator extends BaseValidator implements Validator {

    private static final String NAME_REGEX = "[\\w ]{3,20}";
    private static final String NAME_LENGTH_MSG = "valid.length.task.name";
    private static final String STATUS_MSG = "valid.required.status";
    private static final String ASSIGNER_MSG = "valid.required.task.assigner";
    private static final String CREATOR_MSG = "valid.required.task.creator";
    private static final String ESTIMATE_MSG = "valid.required.task.estimate";
    private static final String DESCRIPTION_MSG = "valid.required.description";
    private static final String SPRINT_MSG = "valid.required.sprint";
    private static final String NUMBER_MSG = "valid.invalid.number";

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        TaskDTO task = (TaskDTO) obj;

        String taskName = task.getName();
        validateName(errors, taskName, NAME_LENGTH_MSG, NAME_REGEX);

        String taskStartDate = task.getStartDate();
        String taskEndDate = task.getEndDate();
        validateDates(errors, taskStartDate, taskEndDate);

        String taskStatus = task.getStatus();
        validateNotNullAndEmpty(errors, "status", taskStatus, STATUS_MSG);

        String taskAssignerId = task.getAssignerId();
        validateNotNullAndEmpty(errors, "assignerId", taskAssignerId, ASSIGNER_MSG);

        String taskCreatorId = task.getCreatorId();
        validateNotNullAndEmpty(errors, "creatorId", taskCreatorId, CREATOR_MSG);

        String taskSprintId = task.getSprintId();
        validateNotNullAndEmpty(errors, "sprintId", taskSprintId, SPRINT_MSG);

        String taskEstimate = task.getEstimate();
        validateNotNullAndEmpty(errors, "estimate", taskEstimate, ESTIMATE_MSG);
        validateNumber(errors, "estimate", taskEstimate, NUMBER_MSG);

        String taskDescription = task.getDescription();
        validateNotNullAndEmpty(errors, "description", taskDescription, DESCRIPTION_MSG);
    }
}
