package org.venuspj.util.precondition;


import static org.venuspj.util.collect.Arrays2.asArray;
import static org.venuspj.util.primitives.Strings2.lenientFormat;

import java.util.function.Supplier;
import org.venuspj.util.exception.VIllegalArgumentException;


/**
 * Preconditionsは、コードの前提条件をチェックするさまざまな静的メソッドを提供するユーティリティクラスです。
 */
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
    checkArgument(expression, () -> new IllegalArgumentException());
  }

  public static <E extends RuntimeException> void checkArgument(boolean expression,
      Supplier<E> runtimeExceptionSupplier) {
    if (!expression) {
      throw runtimeExceptionSupplier.get();
    }
  }

  /**
   * 与えられた参照がnullでないことを確認します。
   *
   * @param reference nullであることを確認する参照
   * @param runtimeExceptionSupplier ランタイム例外を作成するためのサプライヤ
   * @param <T> 参照の型
   * @param <E> ランタイム例外の型
   * @return 参照がnullでない場合、参照自体を返します
   * @throws E 参照がnullである場合、サプライヤによって供給されるランタイム例外がスローされます
   */
  public static <T, E extends RuntimeException> T checkNotNull(T reference,
      Supplier<E> runtimeExceptionSupplier) {
    if (reference == null) {
      throw runtimeExceptionSupplier.get();
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
    checkArgument(expression,
        () -> new IllegalArgumentException(String.valueOf(errorMessage)));
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
    checkArgument(expression,
        () -> new IllegalArgumentException(lenientFormat(errorMessageTemplate, errorMessageArgs)));
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * @param expression a boolean expression
   * @throws IllegalStateException if {@code expression} is false
   */
  public static void checkState(boolean expression) {
    checkState(expression, IllegalStateException::new);
  }

  /**
   * Ensures the truth of an expression involving the state of the calling instance, but not
   * involving any parameters to the calling method.
   *
   * @param expression a boolean expression
   * @param errorMessage the exception message to use if the check fails; will be converted to a
   * string using {@link String#valueOf(Object)}
   * @throws IllegalStateException if {@code expression} is false
   */
  public static void checkState(boolean expression, Object errorMessage) {
    checkState(expression, () -> new IllegalStateException(String.valueOf(errorMessage)));
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
   */
  public static void checkState(
      boolean expression,
      String errorMessageTemplate,
      Object... errorMessageArgs) {
    checkState(expression,
        () -> new IllegalStateException(lenientFormat(errorMessageTemplate, errorMessageArgs)));
  }

  /**
   * Ensures that an object reference passed as a parameter to the calling method is not null.
   *
   * @param reference an object reference
   * @return the non-null reference that was validated
   * @throws NullPointerException if {@code reference} is null
   */
  public static <T> T checkNotNull(T reference) {
    return checkNotNull(reference, NullPointerException::new);

  }

  /**
   * 呼び出し元のメソッドにパラメータとして渡されたオブジェクトリファレンスがnullでないことを確認します。
   *
   * @param reference オブジェクトリファレンス
   * @param errorMessage チェックが失敗した場合に使用する例外メッセージ； {@link String#valueOf(Object)}を使用して文字列に変換されます
   * @return 検証されたnullでないリファレンス
   * @throws NullPointerException {@code reference}がnullの場合
   */
  public static <T> T checkNotNull(T reference, Object errorMessage) {
    return checkNotNull(reference, () -> new NullPointerException(String.valueOf(errorMessage)));

  }

  /**
   * 指定された参照がnullでないことを確認します。
   *
   * @param reference nullでないことを確認する対象の参照
   * @param errorMessageTemplate 参照がnullの場合に使用するエラーメッセージのテンプレート
   * @param errorMessageArgs エラーメッセージのテンプレートに代入する引数
   * @param <T> 参照の型
   * @return 参照がnullでない場合、その非nullの参照
   * @throws NullPointerException 参照がnullの場合
   */
  public static <T> T checkNotNull(
      T reference, String errorMessageTemplate, Object... errorMessageArgs) {
    return checkNotNull(reference,
        () -> new NullPointerException(lenientFormat(errorMessageTemplate, errorMessageArgs)));
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

  public static <E extends RuntimeException> void checkState(boolean expression,
      Supplier<E> runtimeExceptionSupplier) {
    if (!expression) {
      throw runtimeExceptionSupplier.get();
    }
  }

  /**
   * インデックスが不正でないことを表明します。
   *
   * @param argName {@code null} であってはならない引数の名前
   * @param argValue インデックスの値
   * @param arraySize インデックスが参照する配列の長さ
   * @throws VIllegalArgumentException 引数が配列のインデックスとして不正な場合場合。
   */
  public static void checkArgumentArrayIndex(final String argName,
      final int argValue, final int arraySize) {

    if (argValue < 0) {
      throw new VIllegalArgumentException(
          argName,
          "EUTL0014",
          asArray(argName));
    }
    if (argValue >= arraySize) {
      throw new VIllegalArgumentException(argName, "EUTL0015", asArray(
          argName,
          arraySize));
    }
  }

}
