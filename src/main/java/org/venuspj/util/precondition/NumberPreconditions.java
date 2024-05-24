package org.venuspj.util.precondition;

import java.util.function.Supplier;

public class NumberPreconditions {

  public static <E extends RuntimeException> int checkPositiveOrZero(int ref,
      Supplier<E> runtimeExceptionSupplier) {
    if (ref < 0) {
      throw runtimeExceptionSupplier.get();
    }
    return ref;
  }

  public static <E extends RuntimeException> long checkPositiveOrZero(long ref,
      Supplier<E> runtimeExceptionSupplier) {
    if (ref < 0) {
      throw runtimeExceptionSupplier.get();
    }
    return ref;
  }

  public static int checkPositive(int ref, String label) {
    return checkPositive(ref,
        () -> new IllegalArgumentException(String.format("$d must positive.", label)));
  }

  public static <E extends RuntimeException> int checkPositive(int ref,
      Supplier<E> runtimeExceptionSupplier) {
    if (ref <= 0) {
      throw runtimeExceptionSupplier.get();
    }
    return ref;
  }
}
