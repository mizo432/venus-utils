package org.venuspj.util.convert;

import java.text.SimpleDateFormat;
import org.venuspj.util.primitives.Strings2;
import org.venuspj.util.text.DecimalFormats;

/**
 * {@link Float}用の変換ユーティリティです。
 */
public abstract class FloatConversions {

  /**
   * {@link Float}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@link Float}
   */
  public static Float toFloat(final Object o) {
    return toFloat(o, null);
  }

  /**
   * {@link Float}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@link Float}
   */
  public static Float toFloat(final Object o, final String pattern) {
    if (o == null) {
      return null;
    } else if (o instanceof Float) {
      return (Float) o;
    } else if (o instanceof Number) {
      return ((Number) o).floatValue();
    } else if (o instanceof String) {
      return toFloat((String) o);
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return Float.valueOf(new SimpleDateFormat(pattern).format(o));
      }
      return (float) ((java.util.Date) o).getTime();
    } else {
      return toFloat(o.toString());
    }
  }

  private static Float toFloat(final String s) {
    if (Strings2.isEmpty(s)) {
      return null;
    }
    return Float.valueOf(DecimalFormats.normalize(s));
  }

  /**
   * {@literal float}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@literal float}
   */
  public static float toPrimitiveFloat(final Object o) {
    return toPrimitiveFloat(o, null);
  }

  /**
   * {@literal float}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@literal float}
   */
  public static float toPrimitiveFloat(final Object o, final String pattern) {
    if (o == null) {
      return 0;
    } else if (o instanceof Number) {
      return ((Number) o).floatValue();
    } else if (o instanceof String) {
      return toPrimitiveFloat((String) o);
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return Float
            .parseFloat(new SimpleDateFormat(pattern).format(o));
      }
      return ((java.util.Date) o).getTime();
    } else {
      return toPrimitiveFloat(o.toString());
    }
  }

  private static float toPrimitiveFloat(final String s) {
    if (Strings2.isEmpty(s)) {
      return 0;
    }
    return Float.parseFloat(DecimalFormats.normalize(s));
  }

}
