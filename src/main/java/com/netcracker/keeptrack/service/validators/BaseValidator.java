package com.netcracker.keeptrack.service.validators;

import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class provide common validation behavior for all entity classes.
 */
public class BaseValidator {

    /**
     * Validates positive number value.
     */
    public void validateNumber(Errors errors, String fieldName, String fieldValue, String msg) {
        if (!fieldValue.isEmpty()) {
            Integer number = Integer.parseInt(fieldValue);
            if (number < 0) {
                errors.rejectValue(fieldName, msg);
            }
        }
    }

    /**
     * Validates entity name property for empty and regex check.
     */
    public void validateName(Errors errors, String name, String lengthMsg, String regex) {
        validateStringField(errors, "name", name, "valid.required.name", lengthMsg, regex);
    }

    /**
     * Validates specified string property for empty and regex check.
     */
    public void validateStringField(Errors errors, String fieldName, String fieldValue,
                                    String requiredMsg, String lengthMsg, String regex) {
        if (fieldValue.isEmpty()) {
            errors.rejectValue(fieldName, requiredMsg);
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(fieldValue);
            if (!matcher.matches()) {
                errors.rejectValue(fieldName, lengthMsg);
            }
        }
    }

    /**
     * Validates password for empty and regex check.
     */
    public void validatePassword(Errors errors, String password, String passwordMsg, String regex) {
        if (password.isEmpty()) {
            errors.rejectValue("password", "valid.required.password");
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);
            if (!matcher.matches()) {
                errors.rejectValue("password", passwordMsg);
            }
        }
    }

    /**
     * Validates entity name property for empty and not null check.
     */
    public void validateNotNullAndEmpty(Errors errors, String fieldName, String fieldValue, String msg) {
        if (fieldValue == null || fieldValue.isEmpty()) {
            errors.rejectValue(fieldName, msg);
        }
    }

    /**
     * Validates start and end dates for empty and not null check.
     * Also checks dates valid order. End date cannot be less than the start date,
     * start date can not be greater than the end date.
     */
    public void validateDates(Errors errors, String startDate, String endDate) {
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            LocalDate parsedStartDate = LocalDate.parse(startDate);
            LocalDate parsedEndDate = LocalDate.parse(endDate);
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
}
