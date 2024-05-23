package org.venuspj.util.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PreconditionsTest {

  @Test()
  void checkNotNull1() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(
        () -> Preconditions.checkNotNull(null));
  }


  @Test
  void checkNotNull2() {
    Integer actual = Preconditions.checkNotNull(Integer.valueOf(1));
    assertThat(actual).isEqualTo(1);
  }


  @Test
  void checkArgument1() {
    Preconditions.checkArgument(true, () -> new RuntimeException("test"));
  }

  @Test
  void checkArgument2() {
    assertThatThrownBy(
        () -> Preconditions.checkArgument(false, () -> new RuntimeException("test")))
        .isInstanceOf(RuntimeException.class);

  }

  @Test
  void checkState1() {
    assertThatExceptionOfType(IllegalStateException.class).isThrownBy(
        () -> Preconditions.checkState(false));
  }

  @Test
  void checkState2() {
    Preconditions.checkState(true);
  }

  @Test
  void checkState3() {
    assertThatExceptionOfType(IllegalStateException.class).isThrownBy(
        () -> Preconditions.checkState(false, "Illegal State"));
  }

  @Test
  void checkState4() {
    Preconditions.checkState(true, "Illegal State");
  }

  @Test
  void checkState5() {
    assertThatExceptionOfType(IllegalStateException.class).isThrownBy(
        () -> Preconditions.checkState(false, "Illegal State: %s", "Not true"));
  }

  @Test
  void checkState6() {
    Preconditions.checkState(true, "Illegal State: %s", "Not true");
  }
}
