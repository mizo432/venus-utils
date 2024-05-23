package org.venuspj.util.convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import org.venuspj.util.strings2.Strings2;
import org.venuspj.util.text.AbstractDecimalFormatSymbols;

/**
 * {@link Number}用の変換ユーティリティです。
 */
public abstract class NumberConversionUtil {

  /**
   * 適切な {@link Number}に変換します。
   *
   * @param clazz 変換先の型
   * @param o 変換元のオブジェクト
   * @return {@literal type}に変換された{@link Number}
   */
  public static Object convertNumber(final Class<?> clazz, final Object o) {
    if (clazz == Integer.class) {
      return IntegerConversions.toInteger(o);
    } else if (clazz == BigDecimal.class) {
      return BigDecimalConversions.toBigDecimal(o);
    } else if (clazz == Double.class) {
      return DoubleConversions.toDouble(o);
    } else if (clazz == Long.class) {
      return LongConversions.toLong(o);
    } else if (clazz == Float.class) {
      return FloatConversions.toFloat(o);
    } else if (clazz == Short.class) {
      return ShortConversions.toShort(o);
    } else if (clazz == BigInteger.class) {
      return BigIntegerConversions.toBigInteger(o);
    } else if (clazz == Byte.class) {
      return ByteConversions.toByte(o);
    }
    return o;
  }

  /**
   * 指定されたプリミティブ型に対応するラッパー型に変換して返します。
   *
   * @param type プリミティブ型
   * @param o 変換元のオブジェクト
   * @return 指定されたプリミティブ型に対応するラッパー型に変換されたオブジェクト
   */
  public static Object convertPrimitiveWrapper(final Class<?> type,
      final Object o) {
    if (type == int.class) {
      final Integer i = IntegerConversions.toInteger(o);
      if (i != null) {
        return i;
      }
      return 0;
    } else if (type == double.class) {
      final Double d = DoubleConversions.toDouble(o);
      if (d != null) {
        return d;
      }
      return (double) 0;
    } else if (type == long.class) {
      final Long l = LongConversions.toLong(o);
      if (l != null) {
        return l;
      }
      return 0L;
    } else if (type == float.class) {
      final Float f = FloatConversions.toFloat(o);
      if (f != null) {
        return f;
      }
      return (float) 0;
    } else if (type == short.class) {
      final Short s = ShortConversions.toShort(o);
      if (s != null) {
        return s;
      }
      return (short) 0;
    } else if (type == boolean.class) {
      final Boolean b = BooleanConversions.toBoolean(o);
      if (b != null) {
        return b;
      }
      return Boolean.FALSE;
    } else if (type == byte.class) {
      final Byte b = ByteConversions.toByte(o);
      if (b != null) {
        return b;
      }
      return (byte) 0;
    }
    return o;
  }

  /**
   * デリミタを削除します。
   *
   * @param value 文字列の値
   * @param locale ロケール
   * @return デリミタを削除した結果の文字列
   */
  public static String removeDelimeter(String value, final Locale locale) {
    final String groupingSeparator = findGroupingSeparator(locale);
    if (groupingSeparator != null) {
      value = Strings2.replace(value, groupingSeparator, "");
    }
    return value;
  }

  /**
   * グルーピング用のセパレータを探します。
   *
   * @param locale ロケール
   * @return グルーピング用のセパレータ
   */
  public static String findGroupingSeparator(final Locale locale) {
    final java.text.DecimalFormatSymbols symbol = getDecimalFormatSymbols(locale);
    return Character.toString(symbol.getGroupingSeparator());
  }

  /**
   * 数値のセパレータを返します。
   *
   * @param locale ロケール
   * @return 数値のセパレータ
   */
  public static String findDecimalSeparator(final Locale locale) {
    final java.text.DecimalFormatSymbols symbol = getDecimalFormatSymbols(locale);
    return Character.toString(symbol.getDecimalSeparator());
  }

  private static java.text.DecimalFormatSymbols getDecimalFormatSymbols(
      final Locale locale) {
    java.text.DecimalFormatSymbols symbol;
    if (locale != null) {
      symbol = AbstractDecimalFormatSymbols.getDecimalFormatSymbols(locale);
    } else {
      symbol = AbstractDecimalFormatSymbols.getDecimalFormatSymbols();
    }
    return symbol;
  }

}
