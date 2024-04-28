package org.venuspj.util.base;


import static org.venuspj.util.base.Strings.lenientFormat;

import java.util.function.Supplier;
import org.venuspj.util.functor.Command;


public final class Preconditions {

  private Preconditions() {
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * @param expression a boolean expression
   * @throws IllegalArgumentException if {@code expression} is false
   */
  public static void checkArgument(boolean expression) {
    if (!expression) {
      throw new IllegalArgumentException();
    }
  }

  public static <E extends RuntimeException> void checkArgument(boolean expression,
      Supplier<E> aSupplier) {
    if (!expression) {
      throw aSupplier.get();
    }
  }

  public static void checkArgument(boolean expression, Command aCommand) {
    if (!expression) {
      aCommand.execute();
    }
  }

  public static <T, E extends RuntimeException> T checkNotNull(T reference, Supplier<E> aSupplier) {
    if (reference == null) {
      throw aSupplier.get();
    }
    return reference;
  }

  public static <T> T checkNotNull(T reference, Command aCommand) {
    if (reference == null) {
      aCommand.execute();
    }
    return reference;
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * @param expression a boolean expression
   * @param errorMessage the exception message to use if the check fails; will be converted to a
   * string using {@link String#valueOf(Object)}
   * @throws IllegalArgumentException if {@code expression} is false
   */
  public static void checkArgument(boolean expression, Object errorMessage) {
    if (!expression) {
      throw new IllegalArgumentException(String.valueOf(errorMessage));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * @param expression a boolean expression
   * @param errorMessageTemplate a template for the exception message should the check fail. The
   * message is formed by replacing each {@code %s} placeholder in the template with an argument.
   * These are matched by position - the first {@code %s} gets {@code errorMessageArgs[0]}, etc.
   * Unmatched arguments will be appended to the formatted message in square braces. Unmatched
   * placeholders will be left as-is.
   * @param errorMessageArgs the arguments to be substituted into the message template. Arguments
   * are converted to strings using {@link String#valueOf(Object)}.
   * @throws IllegalArgumentException if {@code expression} is false
   */
  public static void checkArgument(
      boolean expression,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (!expression) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(boolean b, String errorMessageTemplate, char p1) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(boolean b, String errorMessageTemplate, int p1) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(boolean b, String errorMessageTemplate, long p1) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, Object p1) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, char p1, char p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, char p1, int p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, char p1, long p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, char p1, Object p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, int p1, char p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, int p1, int p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, int p1, long p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, int p1, Object p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, long p1, char p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, long p1, int p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, long p1, long p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, long p1, Object p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, Object p1, char p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, Object p1, int p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, Object p1, long p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b, String errorMessageTemplate, Object p1, Object p2) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b,
      String errorMessageTemplate,
      Object p1,
      Object p2,
      Object p3) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2, p3));
    }
  }

  /**
   * Ensures the truth of an expression involving one or more parameters to the calling method.
   *
   * <p>See {@link #checkArgument(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkArgument(
      boolean b,
      String errorMessageTemplate,
      Object p1,
      Object p2,
      Object p3,
      Object p4) {
    if (!b) {
      throw new IllegalArgumentException(lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * @param expression a boolean expression
   * @throws IllegalStateException if {@code expression} is false
   * @see Verify#verify Verify.verify()
   */
  public static void checkState(boolean expression) {
    if (!expression) {
      throw new IllegalStateException();
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * @param expression a boolean expression
   * @param errorMessage the exception message to use if the check fails; will be converted to a
   * string using {@link String#valueOf(Object)}
   * @throws IllegalStateException if {@code expression} is false
   * @see Verify#verify Verify.verify()
   */
  public static void checkState(boolean expression, Object errorMessage) {
    if (!expression) {
      throw new IllegalStateException(String.valueOf(errorMessage));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * @param expression a boolean expression
   * @param errorMessageTemplate a template for the exception message should the check fail. The
   * message is formed by replacing each {@code %s} placeholder in the template with an argument.
   * These are matched by position - the first {@code %s} gets {@code errorMessageArgs[0]}, etc.
   * Unmatched arguments will be appended to the formatted message in square braces. Unmatched
   * placeholders will be left as-is.
   * @param errorMessageArgs the arguments to be substituted into the message template. Arguments
   * are converted to strings using {@link String#valueOf(Object)}.
   * @throws IllegalStateException if {@code expression} is false
   * @see Verify#verify Verify.verify()
   */
  public static void checkState(
      boolean expression,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    if (!expression) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(boolean b, String errorMessageTemplate, char p1) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(boolean b, String errorMessageTemplate, int p1) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(boolean b, String errorMessageTemplate, long p1) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, Object p1) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, char p1, char p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(boolean b, String errorMessageTemplate, char p1, int p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, char p1, long p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, char p1, Object p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(boolean b, String errorMessageTemplate, int p1, char p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(boolean b, String errorMessageTemplate, int p1, int p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(boolean b, String errorMessageTemplate, int p1, long p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, int p1, Object p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, long p1, char p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(boolean b, String errorMessageTemplate, long p1, int p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, long p1, long p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, long p1, Object p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, Object p1, char p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, Object p1, int p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, Object p1, long p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b, String errorMessageTemplate, Object p1, Object p2) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b,
      String errorMessageTemplate,
      Object p1,
      Object p2,
      Object p3) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2, p3));
    }
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * <p>See {@link #checkState(boolean, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static void checkState(
      boolean b,
      String errorMessageTemplate,
      Object p1,
      Object p2,
      Object p3,
      Object p4) {
    if (!b) {
      throw new IllegalStateException(lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
    }
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * @param reference an object reference
   * @return the non-null reference that was validated
   * @throws NullPointerException if {@code reference} is null
   * @see Verify#verifyNotNull Verify.verifyNotNull()
   */
  public static <T> T checkNotNull(T reference) {
    if (reference == null) {
      throw new NullPointerException();
    }
    return reference;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * @param reference an object reference
   * @param errorMessage the exception message to use if the check fails; will be converted to a
   * string using {@link String#valueOf(Object)}
   * @return the non-null reference that was validated
   * @throws NullPointerException if {@code reference} is null
   * @see Verify#verifyNotNull Verify.verifyNotNull()
   */

  public static <T> T checkNotNull(T reference, Object errorMessage) {
    if (reference == null) {
      throw new NullPointerException(String.valueOf(errorMessage));
    }
    return reference;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * @param reference an object reference
   * @param errorMessageTemplate a template for the exception message should the check fail. The
   * message is formed by replacing each {@code %s} placeholder in the template with an argument.
   * These are matched by position - the first {@code %s} gets {@code errorMessageArgs[0]}, etc.
   * Unmatched arguments will be appended to the formatted message in square braces. Unmatched
   * placeholders will be left as-is.
   * @param errorMessageArgs the arguments to be substituted into the message template. Arguments
   * are converted to strings using {@link String#valueOf(Object)}.
   * @return the non-null reference that was validated
   * @throws NullPointerException if {@code reference} is null
   * @see Verify#verifyNotNull Verify.verifyNotNull()
   */

  public static <T> T checkNotNull(
      T reference, String errorMessageTemplate, Object... errorMessageArgs) {
    if (reference == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, errorMessageArgs));
    }
    return reference;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, char p1) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, int p1) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, long p1) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj, String errorMessageTemplate, Object p1) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, char p1, char p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, char p1, int p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, char p1, long p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj, String errorMessageTemplate, char p1, Object p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, int p1, char p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */
  public static <T> T checkNotNull(T obj, String errorMessageTemplate, int p1, int p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, int p1, long p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj, String errorMessageTemplate, int p1, Object p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, long p1, char p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, long p1, int p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(T obj, String errorMessageTemplate, long p1, long p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj, String errorMessageTemplate, long p1, Object p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj, String errorMessageTemplate, Object p1, char p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj, String errorMessageTemplate, Object p1, int p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj, String errorMessageTemplate, Object p1, long p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj, String errorMessageTemplate, Object p1, Object p2) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj,
      String errorMessageTemplate,
      Object p1,
      Object p2,
      Object p3) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2, p3));
    }
    return obj;
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * <p>See {@link #checkNotNull(Object, String, Object...)} for details.
   *
   * @since 20.0 (varargs overload since 2.0)
   */

  public static <T> T checkNotNull(
      T obj,
      String errorMessageTemplate,
      Object p1,
      Object p2,
      Object p3,
      Object p4) {
    if (obj == null) {
      throw new NullPointerException(lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
    }
    return obj;
  }

  /**
   * Ensures that {@code index} specifies a valid <i>element</i> in an array, list or string of size
   * {@code size}. An element index may range from zero, inclusive, to {@code size}, exclusive.
   *
   * @param index a user-supplied index identifying an element of an array, list or string
   * @param size the size of that array, list or string
   * @return the value of {@code index}
   * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
   * {@code size}
   * @throws IllegalArgumentException if {@code size} is negative
   */

  public static int checkElementIndex(int index, int size) {
    return checkElementIndex(index, size, "index");
  }

  /**
   * Ensures that {@code index} specifies a valid <i>element</i> in an array, list or string of size
   * {@code size}. An element index may range from zero, inclusive, to {@code size}, exclusive.
   *
   * @param index a user-supplied index identifying an element of an array, list or string
   * @param size the size of that array, list or string
   * @param desc the text to use to describe this index in an error message
   * @return the value of {@code index}
   * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than
   * {@code size}
   * @throws IllegalArgumentException if {@code size} is negative
   */

  public static int checkElementIndex(int index, int size, String desc) {
    // Carefully optimized for execution by hotspot (explanatory comment above)
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
    }
    return index;
  }

  private static String badElementIndex(int index, int size, String desc) {
    if (index < 0) {
      return lenientFormat("%s (%s) must not be negative", desc, index);
    } else if (size < 0) {
      throw new IllegalArgumentException("negative size: " + size);
    } else { // index >= size
      return lenientFormat("%s (%s) must be less than size (%s)", desc, index, size);
    }
  }

  /**
   * Ensures that {@code index} specifies a valid <i>position</i> in an array, list or string of
   * size {@code size}. A position index may range from zero to {@code size}, inclusive.
   *
   * @param index a user-supplied index identifying a position in an array, list or string
   * @param size the size of that array, list or string
   * @return the value of {@code index}
   * @throws IndexOutOfBoundsException if {@code index} is negative or is greater than {@code size}
   * @throws IllegalArgumentException if {@code size} is negative
   */

  public static int checkPositionIndex(int index, int size) {
    return checkPositionIndex(index, size, "index");
  }

  /**
   * Ensures that {@code index} specifies a valid <i>position</i> in an array, list or string of
   * size {@code size}. A position index may range from zero to {@code size}, inclusive.
   *
   * @param index a user-supplied index identifying a position in an array, list or string
   * @param size the size of that array, list or string
   * @param desc the text to use to describe this index in an error message
   * @return the value of {@code index}
   * @throws IndexOutOfBoundsException if {@code index} is negative or is greater than {@code size}
   * @throws IllegalArgumentException if {@code size} is negative
   */

  public static int checkPositionIndex(int index, int size, String desc) {
    // Carefully optimized for execution by hotspot (explanatory comment above)
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
    }
    return index;
  }

  private static String badPositionIndex(int index, int size, String desc) {
    if (index < 0) {
      return lenientFormat("%s (%s) must not be negative", desc, index);
    } else if (size < 0) {
      throw new IllegalArgumentException("negative size: " + size);
    } else { // index > size
      return lenientFormat("%s (%s) must not be greater than size (%s)", desc, index, size);
    }
  }

  /**
   * Ensures that {@code start} and {@code end} specify a valid <i>positions</i> in an array, list
   * or string of size {@code size}, and are in order. A position index may range from zero to
   * {@code size}, inclusive.
   *
   * @param start a user-supplied index identifying a starting position in an array, list or string
   * @param end a user-supplied index identifying a ending position in an array, list or string
   * @param size the size of that array, list or string
   * @throws IndexOutOfBoundsException if either index is negative or is greater than {@code size},
   * or if {@code end} is less than {@code start}
   * @throws IllegalArgumentException if {@code size} is negative
   */
  public static void checkPositionIndexes(int start, int end, int size) {
    // Carefully optimized for execution by hotspot (explanatory comment above)
    if (start < 0 || end < start || end > size) {
      throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
    }
  }

  private static String badPositionIndexes(int start, int end, int size) {
    if (start < 0 || start > size) {
      return badPositionIndex(start, size, "start index");
    }
    if (end < 0 || end > size) {
      return badPositionIndex(end, size, "end index");
    }
    // end < start
    return lenientFormat("end index (%s) must not be less than start index (%s)", end, start);
  }

}
