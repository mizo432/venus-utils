package org.venuspj.util.convert;

import java.text.SimpleDateFormat;
import org.venuspj.util.strings2.Strings2;
import org.venuspj.util.text.DecimalFormats;

/**
 * {@link Double}用の変換ユーティリティです。
 */
public abstract class DoubleConversions {

  /**
   * {@link Double}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@link Double}
   */
  public static Double toDouble(final Object o) {
    return toDouble(o, null);
  }

  /**
   * {@link Double}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@link Double}
   */
  public static Double toDouble(final Object o, final String pattern) {
    if (o == null) {
      return null;
    } else if (o instanceof Double) {
      return (Double) o;
    } else if (o instanceof Number) {
      return ((Number) o).doubleValue();
    } else if (o instanceof String) {
      return toDouble((String) o);
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return Double.valueOf(new SimpleDateFormat(pattern).format(o));
      }
      return (double) ((java.util.Date) o).getTime();
    } else {
      return toDouble(o.toString());
    }
  }

  private static Double toDouble(final String s) {
    if (Strings2.isEmpty(s)) {
      return null;
    }
    return Double.valueOf(DecimalFormats.normalize(s));
  }

  /**
   * {@literal double}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@literal double}
   */
  public static double toPrimitiveDouble(final Object o) {
    return toPrimitiveDouble(o, null);
  }

  /**
   * {@literal double}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@literal double}
   */
  public static double toPrimitiveDouble(final Object o, final String pattern) {
    if (o == null) {
      return 0;
    } else if (o instanceof Number) {
      return ((Number) o).doubleValue();
    } else if (o instanceof String) {
      return toPrimitiveDouble((String) o);
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return Double.parseDouble(new SimpleDateFormat(pattern)
            .format(o));
      }
      return ((java.util.Date) o).getTime();
    } else {
      return toPrimitiveDouble(o.toString());
    }
  }

  private static double toPrimitiveDouble(final String s) {
    if (Strings2.isEmpty(s)) {
      return 0;
    }
    return Double.parseDouble(DecimalFormats.normalize(s));
  }

}
