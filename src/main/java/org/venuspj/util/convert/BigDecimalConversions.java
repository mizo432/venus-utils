package org.venuspj.util.convert;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import org.venuspj.util.strings2.Strings2;

/**
 * {@link BigDecimal}用の変換ユーティリティです。
 */
public abstract class BigDecimalConversions {

  /**
   * {@link BigDecimal}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @return 変換された{@link BigDecimal}
   */
  public static BigDecimal toBigDecimal(final Object o) {
    return toBigDecimal(o, null);
  }

  /**
   * {@link BigDecimal}に変換します。
   *
   * @param o 変換元のオブジェクト
   * @param pattern パターン文字列
   * @return 変換された{@link BigDecimal}
   */
  public static BigDecimal toBigDecimal(final Object o, final String pattern) {
    if (o == null) {
      return null;
    } else if (o instanceof BigDecimal) {
      return (BigDecimal) o;
    } else if (o instanceof java.util.Date) {
      if (pattern != null) {
        return new BigDecimal(new SimpleDateFormat(pattern).format(o));
      }
      return new BigDecimal(Long.toString(((java.util.Date) o).getTime()));
    } else if (o instanceof String) {
      final String s = (String) o;
      if (Strings2.isEmpty(s)) {
        return null;
      }
      return normalize(new BigDecimal(s));
    } else {
      return normalize(new BigDecimal(o.toString()));
    }
  }

  /**
   * {@link BigDecimal}を文字列に変換します。
   *
   * @param dec 変換元の{@link BigDecimal}
   * @return 変換された文字列
   */
  public static String toString(final BigDecimal dec) {
    return dec.toPlainString();
  }

  /**
   * {@link BigDecimal}を正規化します。
   *
   * @param dec 変換元の{@link BigDecimal}
   * @return 正規化されたデータ
   */
  private static BigDecimal normalize(final BigDecimal dec) {
    return new BigDecimal(dec.toPlainString());
  }

}
