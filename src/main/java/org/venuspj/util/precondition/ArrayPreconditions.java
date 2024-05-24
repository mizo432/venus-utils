package org.venuspj.util.precondition;

import static org.venuspj.util.collect.Arrays2.asArray;

import java.util.function.Supplier;
import org.venuspj.util.exception.EmptyArgumentException;

public class ArrayPreconditions {

  public static <E extends RuntimeException> void checkLength(Object[] referenceArray, int index,
      Supplier<E> runtimeExceptionSupplier) {
    NumberPreconditions.checkPositiveOrZero(index,
        () -> new IllegalArgumentException("indexにマイナスの値が設定されています。"));

    if (index >= referenceArray.length) {
      throw runtimeExceptionSupplier.get();
    }


  }

  public static class StringPreconditions {

    /**
     * Throws an {@link EmptyArgumentException} if the given reference is null or empty.
     *
     * @param reference the reference to be checked
     * @param argName the name of the argument to be checked
     * @return the reference if it is not empty
     * @throws EmptyArgumentException if the reference is null or empty
     */
    public static String checkNotEmpty(CharSequence reference, String argName) {
      if (reference == null || reference.isEmpty()) {
        throw new EmptyArgumentException(
            argName,
            "EUTL0010",
            asArray(argName));

      }
      return reference.toString();
    }

    /**
     * Checks if the given reference is not empty.
     *
     * @param reference the reference to be checked
     * @param aSupplier a supplier that provides the exception to be thrown if the reference is
     * empty
     * @param <E> the type of the exception
     * @return the reference if it is not empty
     * @throws E if the reference is null or empty
     */
    public static <E extends RuntimeException> String checkNotEmpty(String reference,
        Supplier<E> aSupplier) {
      if (reference == null || reference.isEmpty()) {
        throw aSupplier.get();

      }
      return reference;
    }

  }
}
