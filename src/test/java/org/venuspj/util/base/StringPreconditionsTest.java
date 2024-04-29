package org.venuspj.util.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.venuspj.util.exception.EmptyArgumentException;

class StringPreconditionsTest {

  @Test
  void checkNotEmpty_withNotEmptyString_shouldPass() {
    String input = "notEmpty";
    assertThat(StringPreconditions.checkNotEmpty(input, "input")).isEqualTo(input);
  }

  @Test
  void checkNotEmpty_withEmptyString_shouldThrowException() {
    String input = "";
    assertThatExceptionOfType(EmptyArgumentException.class).isThrownBy(
        () -> StringPreconditions.checkNotEmpty(input, "input"));
  }

  @Test
  void checkNotEmpty_withNullString_shouldThrowException() {
    String input = null;
    assertThatExceptionOfType(EmptyArgumentException.class).isThrownBy(
        () -> StringPreconditions.checkNotEmpty(input, "input"));
  }
}
