package com.netcracker.keeptrack.service.validators;

import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Class provide validation behavior for the User by implementing the
 * {@link Validator} interface.
 *
 * @see Validator
 */
@Component
public class UserValidator extends BaseValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    private static final String USERNAME_REGEX = "[\\w ]{3,10}";
    private static final String NAME_REGEX = "[\\w ]{3,15}";
    private static final String PASSWORD_REGEX = "[\\w]{6,20}";
    private static final String NAME_REQUIRED_MSG = "valid.required.name";
    private static final String USERNAME_LENGTH_MSG = "valid.length.user.username";
    private static final String FIRST_NAME_LENGTH_MSG = "valid.length.user.firstname";
    private static final String PASSWORD_MSG = "valid.user.password";
    private static final String LAST_NAME_LENGTH_MSG = "valid.length.user.lastname";
    private static final String PROJECT_MSG = "valid.required.user.project";
    private static final String ROLE_MSG = "valid.required.user.role";
    private static final String GENDER_MSG = "valid.required.user.gender";
    private static final String BIRTHDAY_MSG = "valid.required.user.birthday";
    private static final String HIREDAY_MSG = "valid.required.user.hireday";
    private static final String SALARY_MSG = "valid.required.user.salary";
    private static final String EMAIL_MSG = "valid.required.user.email";
    private static final String EXIST_NAMES_MSG = "valid.exist.user.names";
    private static final String EXIST_USERNAME_MSG = "valid.exist.user.username";
    private static final String EXIST_EMAIL_MSG = "valid.exist.user.email";

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserDTO user = (UserDTO) obj;

        boolean isNew = user.getId() == null;

        String userName = user.getUsername();
        validateStringField(errors, "username", userName, NAME_REQUIRED_MSG, USERNAME_LENGTH_MSG, USERNAME_REGEX);

        if (isNew && userRepository.getUserByUsername(userName) != null) {
            errors.rejectValue("username", EXIST_USERNAME_MSG);
        }

        String userFirstName = user.getFirstName();
        validateStringField(errors, "firstName", userFirstName, NAME_REQUIRED_MSG, FIRST_NAME_LENGTH_MSG, NAME_REGEX);

        String userLastName = user.getLastName();
        validateStringField(errors, "lastName", userFirstName, NAME_REQUIRED_MSG, LAST_NAME_LENGTH_MSG, NAME_REGEX);

        if (isNew && userRepository.getUserByFirstAndLastName(userFirstName, userLastName) != null) {
            errors.rejectValue("firstName", EXIST_NAMES_MSG);
        }

        String userEmail = user.getEmail();
        validateNotNullAndEmpty(errors, "email", userEmail, EMAIL_MSG);

        if (isNew && userRepository.getUserByEmail(userEmail) != null) {
            errors.rejectValue("email", EXIST_EMAIL_MSG);
        }

        String userPassword = user.getPassword();
        validatePassword(errors, userPassword, PASSWORD_MSG, PASSWORD_REGEX);

        String userSalary = user.getSalary();
        validateNumber(errors, "salary", userSalary, SALARY_MSG);

        String userProject = user.getProjectId();
        validateNotNullAndEmpty(errors, "project", userProject, PROJECT_MSG);

        String userRole = user.getRole();
        validateNotNullAndEmpty(errors, "role", userRole, ROLE_MSG);

        String userGender = user.getRole();
        validateNotNullAndEmpty(errors, "gender", userGender, GENDER_MSG);

        String userBirthday = user.getBirthday();
        validateNotNullAndEmpty(errors, "birthday", userBirthday, BIRTHDAY_MSG);

        String userHireDay = user.getBirthday();
        validateNotNullAndEmpty(errors, "hireDay", userHireDay, HIREDAY_MSG);
    }
}
