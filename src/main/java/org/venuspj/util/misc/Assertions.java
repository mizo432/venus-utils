package org.venuspj.util.misc;

import static org.venuspj.util.collect.Arrays2.asArray;

import org.venuspj.util.base.Preconditions;
import org.venuspj.util.exception.NullArgumentException;
import org.venuspj.util.exception.VIllegalArgumentException;
import org.venuspj.util.exception.VIllegalStateException;
import org.venuspj.util.exception.VIndexOutOfBoundsException;

/**
 * 表明についてのユーティリティクラスです。
 *
 * @deprecated use {@link Preconditions}
 */
@Deprecated
public abstract class Assertions {

  /**
   * 引数が<code>null</code>でないことを表明します。
   *
   * @param argName {@code null} であってはならない引数の名前
   * @param argValue 引数の値
   * @throws NullArgumentException 引数が<code>null</code>の場合。
   */
  public static void assertArgumentNotNull(final String argName,
      final Object argValue) {
    if (argValue == null) {
      throw new NullArgumentException(argName);
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
  public static void assertArgumentArrayIndex(final String argName,
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

  /**
   * 引数が不正でないことを表明します。
   *
   * @param argName 不正であってはならない引数の名前
   * @param expression 事前条件
   * @param description 不正な引数であることの説明
   * @throws VIllegalArgumentException {@code expression}がfalseの場合。
   */
  public static void assertArgument(final String argName,
      final boolean expression, final String description) {
    if (!expression) {
      throw new VIllegalArgumentException(argName, "EUTL0009", asArray(
          argName,
          description));
    }
  }

  /**
   * 状態が不正でないことを表明します。
   *
   * @param expression 事前条件
   * @param description 不正な状態であることの説明
   * @throws VIllegalStateException {@code expression}がfalseの場合。
   */
  public static void assertState(final boolean expression,
      final String description) {
    if (!expression) {
      throw new VIllegalStateException(description);
    }
  }

  /**
   * indexが不正でないことを表明します。
   *
   * @param expression 事前条件
   * @param description 不正なindexであることの説明
   * @throws VIndexOutOfBoundsException {@code expression}がfalseの場合。
   */
  public static void assertIndex(final boolean expression,
      final String description) {
    if (!expression) {
      throw new VIndexOutOfBoundsException(description);
    }
  }

}
