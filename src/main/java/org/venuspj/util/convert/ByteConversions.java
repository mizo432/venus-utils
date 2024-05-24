package org.venuspj.util.convert;

import java.text.SimpleDateFormat;
import org.venuspj.util.primitives.Strings2;
import org.venuspj.util.text.DecimalFormats;

/**
 * {@link Byte}用の変換ユーティリティです。
 */
public abstract class ByteConversions {

  /**
   * {@link Byte}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@link Byte}
   */
  public static Byte toByte(final Object o) {
    return toByte(o, null);
  }

  /**
   * {@link Byte}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@link Byte}
   */
  public static Byte toByte(final Object o, final String pattern) {
    if (o == null) {
      return null;
    } else if (o instanceof Byte) {
      return (Byte) o;
    } else if (o instanceof Number) {
      return Byte.valueOf(((Number) o).byteValue());
    } else if (o instanceof String) {
      return toByte((String) o);
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return Byte.valueOf(new SimpleDateFormat(pattern).format(o));
      }
      return Byte.valueOf((byte) ((java.util.Date) o).getTime());
    } else if (o instanceof Boolean) {
      return ((Boolean) o).booleanValue() ? Byte.valueOf((byte) 1) : Byte
          .valueOf((byte) 0);
    } else {
      return toByte(o.toString());
    }
  }

  private static Byte toByte(final String s) {
    if (Strings2.isEmpty(s)) {
      return null;
    }
    return Byte.valueOf(DecimalFormats.normalize(s));
  }

  /**
   * {@literal byte}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@literal byte}
   */
  public static byte toPrimitiveByte(final Object o) {
    return toPrimitiveByte(o, null);
  }

  /**
   * {@literal byte}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@literal byte}
   */
  public static byte toPrimitiveByte(final Object o, final String pattern) {
    if (o == null) {
      return 0;
    } else if (o instanceof Number) {
      return ((Number) o).byteValue();
    } else if (o instanceof String) {
      return toPrimitiveByte((String) o);
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return Byte.parseByte(new SimpleDateFormat(pattern).format(o));
      }
      return (byte) ((java.util.Date) o).getTime();
    } else if (o instanceof Boolean) {
      return ((Boolean) o).booleanValue() ? (byte) 1 : (byte) 0;
    } else {
      return toPrimitiveByte(o.toString());
    }
  }

  private static byte toPrimitiveByte(final String s) {
    if (Strings2.isEmpty(s)) {
      return 0;
    }
    return Byte.parseByte(DecimalFormats.normalize(s));
  }

}
