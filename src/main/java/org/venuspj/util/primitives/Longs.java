package org.venuspj.util.primitives;

import static org.venuspj.util.base.Preconditions.checkArgument;
import static org.venuspj.util.base.Preconditions.checkElementIndex;
import static org.venuspj.util.base.Preconditions.checkNotNull;
import static org.venuspj.util.base.Preconditions.checkPositionIndexes;

import java.io.Serial;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import org.jetbrains.annotations.NotNull;
import org.venuspj.util.base.Converter;

/**
 * Longsクラスは、プリミティブなlong値を操作するためのユーティリティメソッドを提供します。
 */
public final class Longs {

  private Longs() {
  }

  /**
   * BYTES変数は、long値を格納するために必要なバイト数を表します。
   * <p>
   * それは、long値のビット数を1バイトのビット数で割ることにより計算されます。
   * <p>
   * 注意: BYTESの値は定数であり、変更することはできません。
   * <p>
   * 使用方法： int bytesRequired = BYTES;
   */
  public static final int BYTES = Long.SIZE / Byte.SIZE;

  /**
   * longデータ型として表現できる2の最大の累乗。
   * <p>
   * この定数の値は、(Long.SIZE - 2) ビット左に1をシフトすることで計算されます。
   */
  public static final long MAX_POWER_OF_TWO = 1L << (Long.SIZE - 2);

  /**
   * 与えられたlong値のハッシュコードを計算します。
   *
   * @param value ハッシュコードを計算する必要があるlong値
   * @return 与えられたlong値のハッシュコード
   */
  public static int hashCode(long value) {
    return Long.hashCode(value);

  }

  /**
   * 2つのlong型の値を比較します。
   *
   * @param a 比較する最初のlong型の値
   * @param b 比較する2つ目のlong型の値
   * @return {@code a} が {@code b} と等しい場合は0; {@code a} が {@code b} より小さい場合は0より小さい値; {@code a} が
   * {@code b} より大きい場合は0より大きい値を返します。
   */
  public static int compare(long a, long b) {
    return Long.compare(a, b);
  }

  /**
   * 与えられた対象の値が配列の中に存在するかをチェックします。
   *
   * @param array 検索対象の配列
   * @param target 配列内の存在を確認するための値
   * @return ターゲットの値が配列内に存在する場合はtrue、そうでない場合はfalse
   */
  public static boolean contains(long[] array, long target) {
    for (long value : array) {
      if (value == target) {
        return true;
      }
    }
    return false;
  }

  /**
   * このメソッドは配列中で指定値が初めて現れるインデックスを返します。
   *
   * @param array 空でも可能な {@code long} 値の配列
   * @param target 原始型の {@code long} 値
   * @return {@code array[i] == target} を満たす最小のインデックス {@code i}。存在しない場合は {@code -1} を返します。
   */
  public static int indexOf(long[] array, long target) {
    return indexOf(array, target, 0, array.length);
  }

  /**
   * 指定された範囲内で指定された配列内の目標要素の最初の出現のインデックスを返します。
   * <p>
   * この範囲は、開始と終了のインデックスによって定義されます。
   *
   * @param array 検索対象の配列
   * @param target 検索対象の目標要素
   * @param start 検索を開始するインデックス（これ自体を含む）
   * @param end 検索を終了するインデックス（これ自体は含まない）
   * @return 目標要素の最初の出現のインデックス。だが、指定した範囲内に目標要素が見つからない場合は-1を返します。
   */
  private static int indexOf(long[] array, long target, int start, int end) {
    for (int i = start; i < end; i++) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the start position of the first occurrence of the specified {@code target} within
   * {@code array}, or {@code -1} if there is no such occurrence.
   *
   * <p>More formally, returns the lowest index {@code i} such that
   * {@code Arrays.copyOfRange(array, i, i + target.length)} contains exactly the same elements as
   * {@code target}.
   *
   * @param array the array to search for the sequence {@code target}
   * @param target the array to search for as a sub-sequence of {@code array}
   */
  public static int indexOf(long[] array, long[] target) {
    checkNotNull(array, "array");
    checkNotNull(target, "target");
    if (target.length == 0) {
      return 0;
    }

    outer:
    for (int i = 0; i < array.length - target.length + 1; i++) {
      for (int j = 0; j < target.length; j++) {
        if (array[i + j] != target[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  /**
   * {@code array}のなかから{@code target}の最後の出現インデックスを返します。
   *
   * @param array {@code long}値の配列、空の可能性あり
   * @param target プリミティブな{@code long}値
   * @return {@code array[i] == target}となる最大のインデックス{@code i}を返します。該当するインデックスが存在しない場合は{@code
   * -1}を返します。
   */
  public static int lastIndexOf(long[] array, long target) {
    return lastIndexOf(array, target, 0, array.length);
  }

  /**
   * 指定された範囲内で指定された対象要素の最後の出現インデックスを返します。
   *
   * @param array 検索する配列
   * @param target 見つけるターゲット要素
   * @param start 検索範囲の開始インデックス（含む）
   * @param end 検索範囲の終了インデックス（含まず）
   * @return 指定された範囲内での目標要素の最後の出現のインデックス、または 要素が見つからない場合は-1
   */
  private static int lastIndexOf(long[] array, long target, int start, int end) {
    for (int i = end - 1; i >= start; i--) {
      if (array[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * {@code array}内の最小値を返します。
   *
   * @param array {@code long}型の値の<a href="">非空</a>配列
   * @return 配列内で他の値すべてよりも小さいか等しい値
   * @throws IllegalArgumentException {@code array}が空の場合に発生します
   */
  public static long min(long... array) {
    checkArgument(array.length > 0);
    long min = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }
    return min;
  }

  /**
   * Returns the greatest value present in {@code array}.
   *
   * @param array a <i>nonempty</i> array of {@code long} values
   * @return the value present in {@code array} that is greater than or equal to every other value
   * in the array
   * @throws IllegalArgumentException if {@code array} is empty
   */
  public static long max(long... array) {
    checkArgument(array.length > 0);
    long max = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }

  /**
   * {@code [min..max]}の閉区間内で、{@code value}に最も近い値を返します。
   *
   * <p>{@code value}が{@code [min..max]}の範囲内にある場合、{@code value}はそのまま返されます。
   * {@code value}が{@code min}より小さい場合、{@code min}が返され、
   * {@code value}が{@code max}より大きい場合、{@code max}が返されます。
   *
   * @param value 制約する{@code long}型の値
   * @param min {@code value}を制約する範囲の下限（含む）
   * @param max {@code value}を制約する範囲の上限（含む）
   * @throws IllegalArgumentException {@code min > max}の場合
   */
  public static long constrainToRange(long value, long min, long max) {
    checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", min, max);
    return Math.min(Math.max(value, min), max);
  }

  /**
   * 提供された各配列からの値を単一の配列に結合して返します。例えば、
   * {@code concat(new long[] {a, b}, new long[] {}, new long[] {c}}は配列 {@code {a, b, c}}を返します。
   *
   * @param arrays 0個以上の{@code long}配列
   * @return ソース配列のすべての値を順序通りに含む単一の配列を返します。
   */
  public static long[] concat(long[]... arrays) {
    int length = 0;
    for (long[] array : arrays) {
      length += array.length;
    }
    long[] result = new long[length];
    int pos = 0;
    for (long[] array : arrays) {
      System.arraycopy(array, 0, result, pos, array.length);
      pos += array.length;
    }
    return result;
  }

  /**
   * long値をバイト配列に変換します。
   *
   * @param value 変換するling値
   * @return long値のバイト配列表現
   */
  public static byte[] toByteArray(long value) {
    // Note that this code needs to stay compatible with GWT, which has known
    // bugs when narrowing byte casts of long values occur.
    byte[] result = new byte[8];
    for (int i = 7; i >= 0; i--) {
      result[i] = (byte) (value & 0xffL);
      value >>= 8;
    }
    return result;
  }

  /**
   * バイト配列をlong値に変換します。
   *
   * @param bytes 変換するバイト配列。長さは少なくとも8である必要があります。
   * @return バイト配列によって表されるlong値。
   * @throws IllegalArgumentException バイト配列の長さが8未満の場合。
   */
  public static long fromByteArray(byte[] bytes) {
    checkArgument(bytes.length >= BYTES, "array too small: %s < %s", bytes.length, BYTES);
    return fromBytes(
        bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7]);
  }

  /**
   * Returns the {@code long} value whose byte representation is the given 8 bytes, in big-endian
   * order; equivalent to {@code Longs.fromByteArray(new byte[] {b1, b2, b3, b4, b5, b6, b7, b8})}.
   *
   * @since 7.0
   */
  public static long fromBytes(
      byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
    return (b1 & 0xFFL) << 56
        | (b2 & 0xFFL) << 48
        | (b3 & 0xFFL) << 40
        | (b4 & 0xFFL) << 32
        | (b5 & 0xFFL) << 24
        | (b6 & 0xFFL) << 16
        | (b7 & 0xFFL) << 8
        | (b8 & 0xFFL);
  }

  /*
   * Moving asciiDigits into this static holder class lets ProGuard eliminate and inline the Longs
   * class.
   */
  static final class AsciiDigits {

    private AsciiDigits() {
    }

    private static final byte[] asciiDigits;

    static {
      byte[] result = new byte[128];
      Arrays.fill(result, (byte) -1);
      for (int i = 0; i <= 9; i++) {
        result['0' + i] = (byte) i;
      }
      for (int i = 0; i <= 26; i++) {
        result['A' + i] = (byte) (10 + i);
        result['a' + i] = (byte) (10 + i);
      }
      asciiDigits = result;
    }

    static int digit(char c) {
      return (c < 128) ? asciiDigits[c] : -1;
    }
  }

  /**
   * Parses the specified string as a signed decimal long value. The ASCII character {@code '-'} (
   * <code>'&#92;u002D'</code>) is recognized as the minus sign.
   *
   * <p>Unlike {@link Long#parseLong(String)}, this method returns {@code null} instead of throwing
   * an exception if parsing fails. Additionally, this method only accepts ASCII digits, and returns
   * {@code null} if non-ASCII digits are present in the string.
   *
   * <p>Note that strings prefixed with ASCII {@code '+'} are rejected, even under JDK 7, despite
   * the change to {@link Long#parseLong(String)} for that version.
   *
   * @param string the string representation of a long value
   * @return the long value represented by {@code string}, or {@code null} if {@code string} has a
   * length of zero or cannot be parsed as a long value
   */
  public static Long tryParse(String string) {
    return tryParse(string, 10);
  }

  /**
   * Parses the specified string as a signed long value using the specified radix. The ASCII
   * character {@code '-'} (<code>'&#92;u002D'</code>) is recognized as the minus sign.
   *
   * <p>Unlike {@link Long#parseLong(String, int)}, this method returns {@code null} instead of
   * throwing an exception if parsing fails. Additionally, this method only accepts ASCII digits,
   * and returns {@code null} if non-ASCII digits are present in the string.
   *
   * <p>Note that strings prefixed with ASCII {@code '+'} are rejected, even under JDK 7, despite
   * the change to {@link Long#parseLong(String, int)} for that version.
   *
   * @param string the string representation of an long value
   * @param radix the radix to use when parsing
   * @return the long value represented by {@code string} using {@code radix}, or {@code null} if
   * {@code string} has a length of zero or cannot be parsed as a long value
   * @throws IllegalArgumentException if {@code radix < Character.MIN_RADIX} or
   * {@code radix > Character.MAX_RADIX}
   * @since 19.0
   */
  public static Long tryParse(String string, int radix) {
    if (checkNotNull(string).isEmpty()) {
      return null;
    }
    if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
      throw new IllegalArgumentException(
          "radix must be between MIN_RADIX and MAX_RADIX but was " + radix);
    }
    boolean negative = string.charAt(0) == '-';
    int index = negative ? 1 : 0;
    if (index == string.length()) {
      return null;
    }
    int digit = AsciiDigits.digit(string.charAt(index++));
    if (digit < 0 || digit >= radix) {
      return null;
    }
    long accum = -digit;

    long cap = Long.MIN_VALUE / radix;

    while (index < string.length()) {
      digit = AsciiDigits.digit(string.charAt(index++));
      if (digit < 0 || digit >= radix || accum < cap) {
        return null;
      }
      accum *= radix;
      if (accum < Long.MIN_VALUE + digit) {
        return null;
      }
      accum -= digit;
    }

    if (negative) {
      return accum;
    } else if (accum == Long.MIN_VALUE) {
      return null;
    } else {
      return -accum;
    }
  }

  private static final class LongConverter extends Converter<String, Long> implements Serializable {

    static final LongConverter INSTANCE = new LongConverter();

    @Override
    protected Long doForward(String value) {
      return Long.decode(value);
    }

    @Override
    protected String doBackward(Long value) {
      return value.toString();
    }

    @Override
    public String toString() {
      return "Longs.stringConverter()";
    }

    @Serial
    private Object readResolve() {
      return INSTANCE;
    }

    @Serial
    private static final long serialVersionUID = 1;
  }

  /**
   * Returns a serializable converter object that converts between strings and longs using
   * {@link Long#decode} and {@link Long#toString()}. The returned converter throws
   * {@link NumberFormatException} if the input string is invalid.
   *
   * <p><b>Warning:</b> please see {@link Long#decode} to understand exactly how strings are
   * parsed. For example, the string {@code "0123"} is treated as <i>octal</i> and converted to the
   * value {@code 83L}.
   */
  public static Converter<String, Long> stringConverter() {
    return LongConverter.INSTANCE;
  }

  /**
   * Returns an array containing the same values as {@code array}, but guaranteed to be of a
   * specified minimum length. If {@code array} already has a length of at least {@code minLength},
   * it is returned directly. Otherwise, a new array of size {@code minLength + padding} is
   * returned, containing the values of {@code array}, and zeroes in the remaining places.
   *
   * @param array the source array
   * @param minLength the minimum length the returned array must guarantee
   * @param padding an extra amount to "grow" the array by if growth is necessary
   * @return an array containing the values of {@code array}, with guaranteed minimum length
   * {@code minLength}
   * @throws IllegalArgumentException if {@code minLength} or {@code padding} is negative
   */
  public static long[] ensureCapacity(long[] array, int minLength, int padding) {
    checkArgument(minLength >= 0, "Invalid minLength: %s", minLength);
    checkArgument(padding >= 0, "Invalid padding: %s", padding);
    return (array.length < minLength) ? Arrays.copyOf(array, minLength + padding) : array;
  }

  /**
   * Returns a string containing the supplied {@code long} values separated by {@code separator}.
   * For example, {@code join("-", 1L, 2L, 3L)} returns the string {@code "1-2-3"}.
   *
   * @param separator the text that should appear between consecutive values in the resulting string
   * (but not at the start or end)
   * @param array an array of {@code long} values, possibly empty
   */
  public static String join(String separator, long... array) {
    checkNotNull(separator);
    if (array.length == 0) {
      return "";
    }

    // For pre-sizing a builder, just get the right order of magnitude
    StringBuilder builder = new StringBuilder(array.length * 10);
    builder.append(array[0]);
    for (int i = 1; i < array.length; i++) {
      builder.append(separator).append(array[i]);
    }
    return builder.toString();
  }

  /**
   * Returns a comparator that compares two {@code long} arrays <a
   * href="http://en.wikipedia.org/wiki/Lexicographical_order">lexicographically</a>. That is, it
   * compares, using {@link #compare(long, long)}), the first pair of values that follow any common
   * prefix, or when one array is a prefix of the other, treats the shorter array as the lesser. For
   * example, {@code [] < [1L] < [1L, 2L] < [2L]}.
   *
   * <p>The returned comparator is inconsistent with {@link Object#equals(Object)} (since arrays
   * support only identity equality), but it is consistent with
   * {@link Arrays#equals(long[], long[])}.
   *
   * @since 2.0
   */
  public static Comparator<long[]> lexicographicalComparator() {
    return LexicographicalComparator.INSTANCE;
  }

  private enum LexicographicalComparator implements Comparator<long[]> {
    INSTANCE;

    @Override
    public int compare(long[] left, long[] right) {
      int minLength = Math.min(left.length, right.length);
      for (int i = 0; i < minLength; i++) {
        int result = Longs.compare(left[i], right[i]);
        if (result != 0) {
          return result;
        }
      }
      return left.length - right.length;
    }

    @Override
    public String toString() {
      return "Longs.lexicographicalComparator()";
    }
  }

  /**
   * Sorts the elements of {@code array} in descending order.
   */
  public static void sortDescending(long[] array) {
    checkNotNull(array);
    sortDescending(array, 0, array.length);
  }

  /**
   * Sorts the elements of {@code array} between {@code fromIndex} inclusive and {@code toIndex}
   * exclusive in descending order.
   */
  public static void sortDescending(long[] array, int fromIndex, int toIndex) {
    checkNotNull(array);
    checkPositionIndexes(fromIndex, toIndex, array.length);
    Arrays.sort(array, fromIndex, toIndex);
    reverse(array, fromIndex, toIndex);
  }

  /**
   * Reverses the elements of {@code array}. This is equivalent to
   * {@code Collections.reverse(Longs.asList(array))}, but is likely to be more efficient.
   */
  public static void reverse(long[] array) {
    checkNotNull(array);
    reverse(array, 0, array.length);
  }

  /**
   * Reverses the elements of {@code array} between {@code fromIndex} inclusive and {@code toIndex}
   * exclusive. This is equivalent to
   * {@code Collections.reverse(Longs.asList(array).subList(fromIndex, toIndex))}, but is likely to
   * be more efficient.
   *
   * @throws IndexOutOfBoundsException if {@code fromIndex < 0}, {@code toIndex > array.length}, or
   * {@code toIndex > fromIndex}
   */
  public static void reverse(long[] array, int fromIndex, int toIndex) {
    checkNotNull(array);
    checkPositionIndexes(fromIndex, toIndex, array.length);
    for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
      long tmp = array[i];
      array[i] = array[j];
      array[j] = tmp;
    }
  }

  /**
   * Returns an array containing each value of {@code collection}, converted to a {@code long} value
   * in the manner of {@link Number#longValue}.
   *
   * <p>Elements are copied from the argument collection as if by {@code collection.toArray()}.
   * Calling this method is as thread-safe as calling that method.
   *
   * @param collection a collection of {@code Number} instances
   * @return an array containing the same values as {@code collection}, in the same order, converted
   * to primitives
   * @throws NullPointerException if {@code collection} or any of its elements is null
   * @since 1.0 (parameter was {@code Collection<Long>} before 12.0)
   */
  public static long[] toArray(Collection<? extends Number> collection) {
    if (collection instanceof LongArrayAsList) {
      return ((LongArrayAsList) collection).toLongArray();
    }

    Object[] boxedArray = collection.toArray();
    int len = boxedArray.length;
    long[] array = new long[len];
    for (int i = 0; i < len; i++) {
      // checkNotNull for GWT (do not optimize)
      array[i] = ((Number) checkNotNull(boxedArray[i])).longValue();
    }
    return array;
  }

  public static List<Long> asList(long... backingArray) {
    if (backingArray.length == 0) {
      return Collections.emptyList();
    }
    return new LongArrayAsList(backingArray);
  }

  private static class LongArrayAsList extends AbstractList<Long>
      implements RandomAccess, Serializable {

    final long[] array;
    final int start;
    final int end;

    LongArrayAsList(long[] array) {
      this(array, 0, array.length);
    }

    LongArrayAsList(long[] array, int start, int end) {
      this.array = array;
      this.start = start;
      this.end = end;
    }

    @Override
    public int size() {
      return end - start;
    }

    @Override
    public boolean isEmpty() {
      return false;
    }

    @Override
    public Long get(int index) {
      checkElementIndex(index, size());
      return array[start + index];
    }

    @Override
    public Spliterator.OfLong spliterator() {
      return Spliterators.spliterator(array, start, end, 0);
    }

    @Override
    public boolean contains(Object target) {
      // Overridden to prevent a ton of boxing
      return (target instanceof Long) && Longs.indexOf(array, (Long) target, start, end) != -1;
    }

    @Override
    public int indexOf(Object target) {
      // Overridden to prevent a ton of boxing
      if (target instanceof Long) {
        int i = Longs.indexOf(array, (Long) target, start, end);
        if (i >= 0) {
          return i - start;
        }
      }
      return -1;
    }

    @Override
    public int lastIndexOf(Object target) {
      // Overridden to prevent a ton of boxing
      if (target instanceof Long) {
        int i = Longs.lastIndexOf(array, (Long) target, start, end);
        if (i >= 0) {
          return i - start;
        }
      }
      return -1;
    }

    @Override
    public Long set(int index, Long element) {
      checkElementIndex(index, size());
      long oldValue = array[start + index];
      // checkNotNull for GWT (do not optimize)
      array[start + index] = checkNotNull(element);
      return oldValue;
    }

    @Override
    public @NotNull List<Long> subList(int fromIndex, int toIndex) {
      int size = size();
      checkPositionIndexes(fromIndex, toIndex, size);
      if (fromIndex == toIndex) {
        return Collections.emptyList();
      }
      return new LongArrayAsList(array, start + fromIndex, start + toIndex);
    }

    @Override
    public boolean equals(Object object) {
      if (object == this) {
        return true;
      }
      if (object instanceof LongArrayAsList that) {
        int size = size();
        if (that.size() != size) {
          return false;
        }
        for (int i = 0; i < size; i++) {
          if (array[start + i] != that.array[that.start + i]) {
            return false;
          }
        }
        return true;
      }
      return super.equals(object);
    }

    @Override
    public int hashCode() {
      int result = 1;
      for (int i = start; i < end; i++) {
        result = 31 * result + Longs.hashCode(array[i]);
      }
      return result;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder(size() * 10);
      builder.append('[').append(array[start]);
      for (int i = start + 1; i < end; i++) {
        builder.append(", ").append(array[i]);
      }
      return builder.append(']').toString();
    }

    long[] toLongArray() {
      return Arrays.copyOfRange(array, start, end);
    }

    @Serial
    private static final long serialVersionUID = 0;
  }
}
