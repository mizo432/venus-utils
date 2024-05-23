package org.venuspj.util.convert;

import java.text.DecimalFormat;
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
    return switch (value) {
      case null -> null;
      case String s -> s;
      case Boolean bool -> bool ? "1" : "0";
      case Number number -> toString(number, pattern);
      case LocalDate localDate -> toString(localDate, pattern);
      case LocalDateTime localDateTime -> toString(localDateTime, pattern);
      case LocalTime localTime -> toString(localTime, pattern);
      case YearMonth yearMonth -> toString(yearMonth, pattern);
      case MonthDay monthDay -> toString(monthDay, pattern);
      case byte[] bytes -> Base64Us.encode(bytes);
      default -> value.toString();
    };
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
