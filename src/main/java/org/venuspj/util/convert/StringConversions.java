package org.venuspj.util.convert;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import org.venuspj.util.misc.Base64Us;

/**
 * {@link String}用の変換ユーティリティです。
 */
public class StringConversions {

  /**
   * WAVE DASHです。
   */
  public static final int WAVE_DASH = 0x301c;

  /**
   * FULLWIDTH TILDEです。
   */
  public static final int FULLWIDTH_TILDE = 0xff5e;

  /**
   * インスタンスを構築します。
   */
  protected StringConversions() {
  }

  /**
   * 文字列に変換します。
   *
   * @param value 値
   * @return 変換された結果
   */
  public static String toString(Object value) {
    return toString(value, null);
  }

  /**
   * 文字列に変換します。
   *
   * @param value 値
   * @param pattern パターン
   * @return 変換された結果
   */
  public static String toString(Object value, String pattern) {
    if (value == null) {
      return null;
    } else if (value instanceof String) {
      return (String) value;
    } else if (value instanceof Timestamp) {
      return toString((Timestamp) value, pattern);
    } else if (value instanceof java.sql.Date) {
      return toString((java.sql.Date) value, pattern);
    } else if (value instanceof java.util.Date) {
      return toString((java.util.Date) value, pattern);
    } else if (value instanceof Number) {
      return toString((Number) value, pattern);
    } else if (value instanceof LocalDate) {
      return toString((LocalDate) value, pattern);
    } else if (value instanceof LocalDateTime) {
      return toString((LocalDateTime) value, pattern);
    } else if (value instanceof LocalTime) {
      return toString((LocalTime) value, pattern);
    } else if (value instanceof YearMonth) {
      return toString((YearMonth) value, pattern);
    } else if (value instanceof MonthDay) {
      return toString((MonthDay) value, pattern);
    } else if (value instanceof byte[]) {
      return Base64Us.encode((byte[]) value);
    } else {
      return value.toString();
    }
  }

  public static String toString(YearMonth value, String pattern) {
    if (value == null) {
      return null;
    }
    return value.format(DateTimeFormatter.ofPattern(pattern));
  }

  public static String toString(LocalTime value, String pattern) {
    if (value == null) {
      return null;
    }
    return value.format(DateTimeFormatter.ofPattern(pattern));
  }

  public static String toString(LocalDateTime value, String pattern) {
    if (value == null) {
      return null;
    }
    return value.format(DateTimeFormatter.ofPattern(pattern));
  }

  public static String toString(LocalDate value, String pattern) {
    if (value == null) {
      return null;
    }
    return value.format(DateTimeFormatter.ofPattern(pattern));
  }

  public static String toString(MonthDay value, String pattern) {
    if (value == null) {
      return null;
    }
    return value.format(DateTimeFormatter.ofPattern(pattern));
  }

  /**
   * 文字列に変換します。
   *
   * @param value 値
   * @param pattern パターン
   * @return 変換された結果
   */
  public static String toString(Number value, String pattern) {
    if (value != null) {
      if (pattern != null) {
        return new DecimalFormat(pattern).format(value);
      }
      return value.toString();
    }
    return null;
  }

  /**
   * 文字列に変換します。
   *
   * @param value 値
   * @param pattern パターン
   * @return 変換された結果
   */
  public static String toString(java.util.Date value, String pattern) {
    if (value != null) {
      if (pattern != null) {
        return new SimpleDateFormat(pattern).format(value);
      }
      return value.toString();
    }
    return null;
  }

  /**
   * WAVE DASH(U+301C)をFULLWIDTH TILDE(U+FF5E)に変換します。
   *
   * @param source ソース
   * @return 変換結果
   */
  public static String fromWaveDashToFullwidthTilde(String source) {
    if (source == null) {
      return null;
    }
    StringBuffer result = new StringBuffer(source.length());
    char ch;
    for (int i = 0; i < source.length(); i++) {
      ch = source.charAt(i);
      switch (ch) {
        case WAVE_DASH:
          ch = FULLWIDTH_TILDE;
          break;
        default:
          break;
      }
      result.append(ch);
    }
    return result.toString();
  }
}
