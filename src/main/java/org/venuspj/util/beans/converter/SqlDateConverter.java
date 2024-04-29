package org.venuspj.util.beans.converter;

import org.venuspj.util.beans.EmptyRuntimeException;
import org.venuspj.util.convert.SqlDateConversions;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.strings2.Strings2;

/**
 * SQLの日付用のコンバータです。
 */
public class SqlDateConverter implements Converter<java.sql.Date> {

  /**
   * 日付のパターンです。
   */
  protected String pattern;

  /**
   * インスタンスを構築します。
   *
   * @param pattern 日付のパターン
   */
  public SqlDateConverter(String pattern) {
    if (Strings2.isEmpty(pattern)) {
      throw new EmptyRuntimeException("pattern");
    }
    this.pattern = pattern;
  }

  public java.sql.Date getAsObject(String value) {
    if (Strings2.isEmpty(value)) {
      return null;
    }
    return SqlDateConversions.toDate(value, pattern);
  }

  public String getAsString(Object value) {
    if (isTarget(value.getClass())) {
      return StringConversions.toString(value, pattern);
    }
    return null;
  }

  public boolean isTarget(Class<?> clazz) {
    return clazz == java.sql.Date.class;
  }
}
