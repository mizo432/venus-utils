package org.venuspj.util.validate;

import java.util.Optional;

public interface Validation {

  Optional<String> run();

  static ValidationErrorMessage of(ErrorCondition condition) {
    return errorMessage -> {
        if (condition.hasError()) {
            return () -> Optional.ofNullable(errorMessage.get());

        } else {
            return Optional::empty;
        }
    };
  }

  static ValidationErrorMessage notNull(NullableSupplier input) {
    return of(input::isNull);
  }
}
