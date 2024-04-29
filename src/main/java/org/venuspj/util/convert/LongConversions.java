package org.venuspj.util.convert;

import java.text.SimpleDateFormat;
import org.venuspj.util.strings2.Strings2;
import org.venuspj.util.text.DecimalFormats;

/**
 * {@link Long}用の変換ユーティリティです。
 */
public abstract class LongConversions {

  /**
   * {@link Long}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@link Long}
   */
  public static Long toLong(final Object o) {
    return toLong(o, null);

  }

  /**
   * {@link Long}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@link Long}
   */
  public static Long toLong(final Object o, final String pattern) {
    if (o == null) {
      return null;
    } else if (o instanceof Long) {
      return (Long) o;
    } else if (o instanceof Number) {
      return ((Number) o).longValue();
    } else if (o instanceof String) {
      return toLong((String) o);
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return Long.valueOf(new SimpleDateFormat(pattern).format(o));
      }
      return ((java.util.Date) o).getTime();
    } else if (o instanceof Boolean) {
      return (Boolean) o ? Long.valueOf(1) : Long
          .valueOf(0);
    } else {
      return toLong(o.toString());
    }
  }

  private static Long toLong(final String s) {
    if (Strings2.isEmpty(s)) {
      return null;
    }
    return Long.valueOf(DecimalFormats.normalize(s));
  }

  /**
   * 変換された{@literal long}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@literal long}
   */
  public static long toPrimitiveLong(final Object o) {
    return toPrimitiveLong(o, null);
  }

  /**
   * {@literal long}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@literal long}
   */
  public static long toPrimitiveLong(final Object o, final String pattern) {
    if (o == null) {
      return 0;
    } else if (o instanceof Number) {
      return ((Number) o).longValue();
    } else if (o instanceof String) {
      return toPrimitiveLong((String) o);
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return Long.parseLong(new SimpleDateFormat(pattern).format(o));
      }
      return ((java.util.Date) o).getTime();
    } else if (o instanceof Boolean) {
      return (Boolean) o ? 1 : 0;
    } else {
      return toPrimitiveLong(o.toString());
    }
  }

  private static long toPrimitiveLong(final String s) {
    if (Strings2.isEmpty(s)) {
      return 0;
    }
    return Long.parseLong(DecimalFormats.normalize(s));
  }

}
