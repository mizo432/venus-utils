package org.venuspj.util.validate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ValidationResult {
    List<String> errors();

    default boolean hasError() {
        return !errors().isEmpty();

    }

    default int errorCount() {
        return errors().size();

    }

    static ValidationResult validateAll(Validation... validations) {
        return () -> Arrays.stream(validations)
                .map(Validation::run)
                .filter(Optional::isPresent)
                .map(o -> o.orElseThrow(IllegalStateException::new))
                .collect(Collectors.toUnmodifiableList());
    }
}
