package org.venuspj.util.base;

import java.util.function.Supplier;

public class ArrayPreconditions {

  public static <E extends RuntimeException> void checkLength(Object[] referenceArray, int index,
      Supplier<E> runtimeExceptionSupplier) {
    NumberPreconditions.checkPositiveOrZero(index,
        () -> new IllegalArgumentException("indexにマイナスの値が設定されています。"));

    if (index >= referenceArray.length) {
      throw runtimeExceptionSupplier.get();
    }


  }
}
