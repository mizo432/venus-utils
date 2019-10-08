package org.venuspj.util.validate;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ValidationTest {
    String startDate = "2019/10/01";
    String endDate = "2019/10/31";

    @Test
    public void testValidation() {
        ValidationResult actual = ValidationResult.validateAll(
                startDateIsNotNull(),
                startDateHasWellFormat(),
                endDateIsNotNull(),
                endDateHasWellFormat()
        );

        assertThat(actual).isNotNull();
        System.out.println(actual.errors());

    }

    private Validation endDateHasWellFormat() {
        return Validation.of(() -> {
            try {
                LocalDate.parse(endDate);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }).errorMessage(() -> "endDate is invalid format.");

    }

    private Validation endDateIsNotNull() {
        return Validation
                .notNull(() -> endDate)
                .errorMessage(() -> "endDate is required.");
    }

    private Validation startDateHasWellFormat() {
        return Validation.of(() -> {
            try {
                LocalDate.parse(startDate);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }).errorMessage(() -> "startDate is invalid format.");
    }

    private Validation startDateIsNotNull() {
        return Validation
                .notNull(() -> startDate)
                .errorMessage(() -> "startDate is required.");

    }
}
