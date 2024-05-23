package org.venuspj.util.beans;


import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.collect.Maps2.newHashMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.venuspj.util.beans.converter.BooleanConverter;
import org.venuspj.util.beans.converter.LocalDateConverter;
import org.venuspj.util.beans.converter.LocalDateTimeConverter;
import org.venuspj.util.beans.converter.LocalTimeConverter;
import org.venuspj.util.beans.converter.MonthDayConverter;
import org.venuspj.util.beans.converter.NumberConverter;
import org.venuspj.util.beans.converter.YearMonthConverter;
import org.venuspj.util.beans.factory.BeanDescFactory;
import org.venuspj.util.convert.LocalDateConversions;
import org.venuspj.util.convert.LocalTimeConversions;
import org.venuspj.util.convert.MonthDayConversions;
import org.venuspj.util.convert.YearMonthConversions;
import org.venuspj.util.strings2.Strings2;

/**
 * JavaBeansやMapに対して操作を行う抽象クラスです。
 *
 * @param <S> JavaBeansに対して操作を行うサブタイプです。
 */
public abstract class AbstractCopy<S extends AbstractCopy<S>> {

  /**
   * 空文字列の配列です。
   */
  protected static final String[] EMPTY_STRING_ARRAY = Strings2.EMPTY_STRING_ARRAY;

  protected static final LocalDateConverter DEFAULT_LOCAL_DATE_CONVERTER = new LocalDateConverter(
      LocalDateConversions.getPattern(Locale.getDefault()));
  protected static final LocalDateTimeConverter DEFAULT_LOCAL_DATETIME_CONVERTER = new LocalDateTimeConverter(
      LocalDateConversions.getPattern(Locale.getDefault()));
  protected static final LocalTimeConverter DEFAULT_LOCAL_TIME_CONVERTER = new LocalTimeConverter(
      LocalTimeConversions.getPattern(Locale.getDefault()));
  protected static final YearMonthConverter DEFAULT_YEAR_MONTH_CONVERTER = new YearMonthConverter(
      YearMonthConversions.getPattern(Locale.getDefault()));
  protected static final MonthDayConverter DEFAULT_MONTH_DAY_CONVERTER = new MonthDayConverter(
      MonthDayConversions.getPattern(Locale.getDefault()));
  protected static final BooleanConverter DEFAULT_BOOLEAN_CONVERTER = new BooleanConverter();

  /**
   * 操作の対象に含めるプロパティ名の配列です。
   */
  protected String[] includePropertyNames = EMPTY_STRING_ARRAY;

  /**
   * 操作の対象に含めないプロパティ名の配列です。
   */
  protected String[] excludePropertyNames = Strings2.EMPTY_STRING_ARRAY;

  /**
   * null値のプロパティを操作の対象外にするかどうかです。
   */
  protected boolean excludesNull = false;

  /**
   * 空白を操作の対象外にするかどうかです。
   */
  protected boolean excludesWhitespace = false;

  /**
   * プレフィックスです。
   */
  protected String prefix;

  /**
   * JavaBeanのデリミタです。
   */
  protected char beanDelimiter = '$';

  /**
   * Mapのデリミタです。
   */
  protected char mapDelimiter = '.';

  /**
   * 特定のプロパティに関連付けられたコンバータです。
   */
  protected Map<String, Converter<?>> converterMap = newHashMap();

  /**
   * 特定のプロパティに関連付けられていないコンバータです。
   */
  protected List<Converter<?>> converters = newArrayList();

  /**
   * CharSequenceの配列をStringの配列に変換します。
   *
   * @param charSequenceArray CharSequenceの配列
   * @return Stringの配列
   */
  protected String[] toStringArray(CharSequence[] charSequenceArray) {
    int length = charSequenceArray.length;
    String[] stringArray = new String[length];
    for (int index = 0; index < length; index++) {
      stringArray[index] = charSequenceArray[index].toString();
    }
    return stringArray;
  }

  /**
   * 操作の対象に含めるプロパティ名を指定します。
   *
   * @param propertyNames プロパティ名の配列
   * @return このインスタンス自身
   */
  @SuppressWarnings("unchecked")
  public S includes(CharSequence... propertyNames) {
    this.includePropertyNames = toStringArray(propertyNames);
    return (S) this;
  }

  /**
   * 操作の対象に含めないプロパティ名を指定します。
   *
   * @param propertyNames プロパティ名の配列
   * @return このインスタンス自身
   */
  @SuppressWarnings("unchecked")
  public S excludes(CharSequence... propertyNames) {
    this.excludePropertyNames = toStringArray(propertyNames);
    return (S) this;
  }

  /**
   * null値のプロパティを操作の対象外にします。
   *
   * @return このインスタンス自身
   */
  @SuppressWarnings("unchecked")
  public S excludesNull() {
    this.excludesNull = true;
    return (S) this;
  }

  /**
   * 空白のプロパティを操作の対象外にします。
   *
   * @return このインスタンス自身
   */
  @SuppressWarnings("unchecked")
  public S excludesWhitespace() {
    this.excludesWhitespace = true;
    return (S) this;
  }

  /**
   * プレフィックスを指定します。
   *
   * @param prefix プレフィックス
   * @return このインスタンス自身
   */
  @SuppressWarnings("unchecked")
  public S prefix(CharSequence prefix) {
    this.prefix = prefix.toString();
    return (S) this;
  }

  /**
   * JavaBeansのデリミタを設定します。
   *
   * @param beanDelimiter JavaBeansのデリミタ
   * @return このインスタンス自身
   */
  @SuppressWarnings("unchecked")
  public S beanDelimiter(char beanDelimiter) {
    this.beanDelimiter = beanDelimiter;
    return (S) this;
  }

  /**
   * Mapのデリミタを設定します。
   *
   * @param mapDelimiter Mapのデリミタ
   * @return このインスタンス自身
   */
  @SuppressWarnings("unchecked")
  public S mapDelimiter(char mapDelimiter) {
    this.mapDelimiter = mapDelimiter;
    return (S) this;
  }

  /**
   * コンバータを設定します。
   *
   * @param converter
   * @param propertyNames
   * @return このインスタンス自身
   */
  @SuppressWarnings("unchecked")
  public S converter(Converter<?> converter, CharSequence... propertyNames) {
    if (propertyNames.length == 0) {
      converters.add(converter);
    } else {
      for (CharSequence name : propertyNames) {
        converterMap.put(name.toString(), converter);
      }
    }
    return (S) this;
  }

  public S localDateConverter(String pattern, CharSequence... propertyNames) {
    return converter(new LocalDateConverter(pattern), propertyNames);
  }

  public S localDateTimeConverter(String pattern, CharSequence... propertyNames) {
    return converter(new LocalDateTimeConverter(pattern), propertyNames);
  }

  public S localTimeConverter(String pattern, CharSequence... propertyNames) {
    return converter(new LocalTimeConverter(pattern), propertyNames);
  }

  public S yearMonthConverter(String pattern, CharSequence... propertyNames) {
    return converter(new YearMonthConverter(pattern), propertyNames);
  }

  public S monthDayConverter(String pattern, CharSequence... propertyNames) {
    return converter(new MonthDayConverter(pattern), propertyNames);
  }

  /**
   * 数値のコンバータを設定します。
   *
   * @param pattern 数値のパターン
   * @param propertyNames プロパティ名の配列
   * @return このインスタンス自身
   */
  public S numberConverter(String pattern, CharSequence... propertyNames) {
    return converter(new NumberConverter(pattern), propertyNames);
  }

  /**
   * 対象のプロパティかどうかを返します。
   *
   * @param name プロパティ名
   * @return 対象のプロパティかどうか
   */
  protected boolean isTargetProperty(String name) {
    if (prefix != null && !name.startsWith(prefix)) {
      return false;
    }
    if (includePropertyNames.length > 0) {
      for (String s : includePropertyNames) {
        if (s.equals(name)) {
          for (String s2 : excludePropertyNames) {
            if (s2.equals(name)) {
              return false;
            }
          }
          return true;
        }
      }
      return false;
    }
    if (excludePropertyNames.length > 0) {
      for (String s : excludePropertyNames) {
        if (s.equals(name)) {
          return false;
        }
      }
      return true;
    }
    return true;
  }

  /**
   * BeanからBeanにコピーを行います。
   *
   * @param src コピー元
   * @param dest コピー先
   */
  protected void copyBeanToBean(Object src, Object dest) {
    BeanDesc srcBeanDesc = BeanDescFactory.getBeanDesc(src.getClass());
    BeanDesc destBeanDesc = BeanDescFactory.getBeanDesc(dest.getClass());
    int size = srcBeanDesc.getPropertyDescSize();
    for (int i = 0; i < size; i++) {
      PropertyDesc srcPropertyDesc = srcBeanDesc.getPropertyDesc(i);
      String srcPropertyName = srcPropertyDesc.getPropertyName();
      if (!srcPropertyDesc.isReadable()
          || !isTargetProperty(srcPropertyName)) {
        continue;
      }
      String destPropertyName = trimPrefix(srcPropertyName);
      if (!destBeanDesc.hasPropertyDesc(destPropertyName)) {
        continue;
      }
      PropertyDesc destPropertyDesc = destBeanDesc
          .getPropertyDesc(destPropertyName);
      if (!destPropertyDesc.isWritable()) {
        continue;
      }
      Object value = srcPropertyDesc.getValue(src);
      if (value instanceof String && excludesWhitespace
          && ((String) value).trim().isEmpty()) {
        continue;
      }
      if (value == null && excludesNull) {
        continue;
      }
      value = convertValue(value, destPropertyName, destPropertyDesc
          .getPropertyType());
      destPropertyDesc.setValue(dest, value);
    }
  }

  /**
   * BeanからMapにコピーを行います。
   *
   * @param src コピー元
   * @param dest コピー先
   */
  protected void copyBeanToMap(Object src, Map<String, Object> dest) {
    BeanDesc srcBeanDesc = BeanDescFactory.getBeanDesc(src.getClass());
    int size = srcBeanDesc.getPropertyDescSize();
    for (int i = 0; i < size; i++) {
      PropertyDesc srcPropertyDesc = srcBeanDesc.getPropertyDesc(i);
      String srcPropertyName = srcPropertyDesc.getPropertyName();
      if (!srcPropertyDesc.isReadable()
          || !isTargetProperty(srcPropertyName)) {
        continue;
      }
      Object value = srcPropertyDesc.getValue(src);
      if (value instanceof String && excludesWhitespace
          && ((String) value).trim().isEmpty()) {
        continue;
      }
      if (value == null && excludesNull) {
        continue;
      }
      String destPropertyName = trimPrefix(srcPropertyName.replace(
          beanDelimiter, mapDelimiter));
      value = convertValue(value, destPropertyName, null);
      dest.put(destPropertyName, value);
    }
  }

  /**
   * MapからBeanにコピーを行います。
   *
   * @param src コピー元
   * @param dest コピー先
   */
  protected void copyMapToBean(Map<String, Object> src, Object dest) {
    BeanDesc destBeanDesc = BeanDescFactory.getBeanDesc(dest.getClass());
    for (String srcPropertyName : src.keySet()) {
      if (!isTargetProperty(srcPropertyName)) {
        continue;
      }
      String destPropertyName = trimPrefix(srcPropertyName.replace(
          mapDelimiter, beanDelimiter));
      if (!destBeanDesc.hasPropertyDesc(destPropertyName)) {
        continue;
      }
      PropertyDesc destPropertyDesc = destBeanDesc
          .getPropertyDesc(destPropertyName);
      if (!destPropertyDesc.isWritable()) {
        continue;
      }
      Object value = src.get(srcPropertyName);
      if (value instanceof String && excludesWhitespace
          && ((String) value).trim().isEmpty()) {
        continue;
      }
      if (value == null && excludesNull) {
        continue;
      }
      value = convertValue(value, destPropertyName, destPropertyDesc
          .getPropertyType());
      destPropertyDesc.setValue(dest, value);
    }
  }

  /**
   * MapからMapにコピーを行います。
   *
   * @param src コピー元
   * @param dest コピー先
   */
  protected void copyMapToMap(Map<String, Object> src,
      Map<String, Object> dest) {
    for (String srcPropertyName : src.keySet()) {
      if (!isTargetProperty(srcPropertyName)) {
        continue;
      }
      String destPropertyName = trimPrefix(srcPropertyName);
      Object value = src.get(srcPropertyName);
      if (value instanceof String && excludesWhitespace
          && ((String) value).trim().isEmpty()) {
        continue;
      }
      if (value == null && excludesNull) {
        continue;
      }
      value = convertValue(value, destPropertyName, null);
      dest.put(destPropertyName, value);
    }
  }

  /**
   * プレフィックスを削ります。
   *
   * @param propertyName プロパティ名
   * @return 削った結果
   */
  protected String trimPrefix(String propertyName) {
    if (prefix == null) {
      return propertyName;
    }
    return propertyName.substring(prefix.length());
  }

  /**
   * 値を変換します。
   *
   * @param value 値
   * @param destPropertyName コピー先のプロパティ名
   * @param destPropertyClass コピー先のプロパティクラス
   * @return 変換後の値
   */
  protected Object convertValue(Object value, String destPropertyName,
      Class<?> destPropertyClass) {
    if (value == null || (value.getClass() != String.class
        && destPropertyClass != null
        && destPropertyClass != String.class)) {
      return value;
    }
    Converter<?> converter = converterMap.get(destPropertyName);
    if (converter == null) {
      Class<?> targetClass = value.getClass() != String.class ? value
          .getClass() : destPropertyClass;
      if (targetClass == null) {
        return value;
      }
      for (Class<?> clazz = targetClass; clazz != Object.class
          && clazz != null; clazz = clazz.getSuperclass()) {
        converter = findConverter(clazz);
        if (converter != null) {
          break;
        }
      }
      if (converter == null && destPropertyClass != null) {
        converter = findDefaultConverter(targetClass);
      }
      if (converter == null) {
        return value;
      }
    }
    try {
      if (value.getClass() == String.class) {
        return converter.getAsObject((String) value);
      }
      return converter.getAsString(value);
    } catch (Throwable cause) {
      throw new ConverterRuntimeException(destPropertyName, value, cause);
    }
  }

  /**
   * クラスに対応するコンバータを探します。
   *
   * @param clazz クラス
   * @return コンバータ
   */
  protected Converter<?> findConverter(Class<?> clazz) {
    for (Converter<?> c : converters) {
      if (c.isTarget(clazz)) {
        return c;
      }
    }
    return null;
  }

  /**
   * クラスに対応するデフォルトのコンバータを探します。
   *
   * @param clazz クラス
   * @return コンバータ
   */
  protected Converter<?> findDefaultConverter(Class<?> clazz) {
    if (clazz == LocalDate.class) {
      return DEFAULT_LOCAL_DATE_CONVERTER;
    }

    if (clazz == LocalDateTime.class) {
      return DEFAULT_LOCAL_DATETIME_CONVERTER;
    }

    if (clazz == LocalTime.class) {
      return DEFAULT_LOCAL_TIME_CONVERTER;
    }

    if (clazz == YearMonth.class) {
      return DEFAULT_YEAR_MONTH_CONVERTER;
    }

    if (clazz == MonthDay.class) {
      return DEFAULT_MONTH_DAY_CONVERTER;
    }
    if (clazz == Boolean.class) {
      return DEFAULT_BOOLEAN_CONVERTER;
    }

    return null;
  }

  protected void assignSourceProperties(AbstractCopy<?> sourceAbstractCopy) {
    this.excludePropertyNames = sourceAbstractCopy.excludePropertyNames;
    this.includePropertyNames = sourceAbstractCopy.includePropertyNames;
    this.excludesNull = sourceAbstractCopy.excludesNull;
    this.prefix = sourceAbstractCopy.prefix;
    this.beanDelimiter = sourceAbstractCopy.beanDelimiter;
    this.mapDelimiter = sourceAbstractCopy.mapDelimiter;

  }
}
