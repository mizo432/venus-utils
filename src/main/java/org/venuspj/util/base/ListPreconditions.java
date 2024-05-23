package org.venuspj.util.base;

import java.util.List;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class ListPreconditions {

  public static <E extends RuntimeException> void checkSize(@NotNull List<?> referenceList,
      int index,
      Supplier<E> runtimeExceptionSupplier) {
    NumberPreconditions.checkPositiveOrZero(index,
        () -> new IllegalArgumentException("indexにマイナスの値が設定されています。"));

    if (index >= referenceList.size()) {
      throw runtimeExceptionSupplier.get();
    }


  }

}
