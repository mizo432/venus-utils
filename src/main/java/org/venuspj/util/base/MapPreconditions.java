package org.venuspj.util.base;

import java.util.Map;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class MapPreconditions {

  public static <E extends RuntimeException> void checkSize(@NotNull Map<?, ?> referenceMap,
      int size,
      Supplier<E> runtimeExceptionSupplier) {
    NumberPreconditions.checkPositiveOrZero(size,
        () -> new IllegalArgumentException("sizeにマイナスの値が設定されています。"));

    if (size >= referenceMap.size()) {
      throw runtimeExceptionSupplier.get();
    }


  }

}
