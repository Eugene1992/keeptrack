package com.netcracker.keeptrack.service.validators;

import com.netcracker.keeptrack.service.ProjectService;
import com.netcracker.keeptrack.web.dto.ProjectDTO;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Class provide validation behavior for the Project by implementing the
 * {@link Validator} interface.
 * Also inherited from the base validator that allows common validation methods.
 *
 * @see Validator
 * @see BaseValidator
 */
@Component
public class ProjectValidator extends BaseValidator implements Validator {

    @Autowired
    private ProjectService projectService;

    /**
     * Validation error messages.
     */
    private static final String NAME_REGEX = "[\\w ]{3,20}";
    private static final String DESCRIPTION_MSG = "valid.required.description";
    private static final String NAME_LENGTH_MSG = "valid.length.project.name";
    private static final String MANAGER_MSG = "valid.required.project.manager";
    private static final String STATUS_MSG = "valid.required.status";

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
     * Validates Project DTO fields.
     * If we add new project(request comes from adding form) also check whether
     * the project exists with the specified name.
     *
     * @param obj validated object
     * @param errors validation errors
     */
    @Override
    public void validate(Object obj, Errors errors) {
        ProjectDTO project = (ProjectDTO) obj;

        boolean isNew = project.getId() == null;

        String projectName = project.getName();
        validateName(errors, projectName, NAME_LENGTH_MSG, NAME_REGEX);

        if (isNew && projectService.checkProjectName(projectName)) {
            errors.rejectValue("name", "valid.existed.project.name");
        }

        String status = project.getStatus();
        validateNotNullAndEmpty(errors, "status", status, STATUS_MSG);

        String description = project.getDescription();
        validateNotNullAndEmpty(errors, "description", description, DESCRIPTION_MSG);

        String projectId = project.getManagerId();
        validateNotNullAndEmpty(errors, "projectId", projectId, MANAGER_MSG);

        String startDate = project.getStartDate();
        String endDate = project.getEndDate();
        validateDates(errors, startDate, endDate);
    }
}
