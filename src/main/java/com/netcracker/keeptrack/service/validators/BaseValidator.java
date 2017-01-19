package com.netcracker.keeptrack.service.validators;

import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class provide base validation behavior for all entity classes.
 */
public class BaseValidator {

    /**
     * Class provide base validation behavior for all entity classes.
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
     * Class provide base validation behavior for all entity classes.
     */
    public void validateName(Errors errors, String name, String lengthMsg, String regex) {
        if (name.isEmpty()) {
            errors.rejectValue("name", "valid.required.name");
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                errors.rejectValue("name", lengthMsg);
            }
        }
    }

    /**
     * Class provide base validation behavior for all entity classes.
     */
    public void validateByRegex(Errors errors, String fieldName, String fieldValue, String msg, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fieldValue);
        if (!matcher.matches()) {
            errors.rejectValue(fieldName, msg);
        }
    }

    /**
     * Class provide base validation behavior for all entity classes.
     */
    public void validateNotNullAndEmpty(Errors errors, String fieldName, String fieldValue, String msg) {
        if (fieldValue == null || fieldValue.isEmpty()) {
            errors.rejectValue(fieldName, msg);
        }
    }

    /**
     * Class provide base validation behavior for all entity classes.
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
