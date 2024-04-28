package org.venuspj.util.beans.converter;

import java.sql.Time;
import java.text.DecimalFormat;
import org.venuspj.util.beans.EmptyRuntimeException;
import org.venuspj.util.convert.TimeConversions;
import org.venuspj.util.strings2.Strings2;

/**
 * 時間用のコンバータです。
 */
public class TimeConverter implements Converter<Time> {

  /**
   * 時間のパターンです。
   */
  protected String pattern;

  /**
   * インスタンスを構築します。
   *
   * @param pattern 時間のパターン
   */
  public TimeConverter(String pattern) {
    if (Strings2.isEmpty(pattern)) {
      throw new EmptyRuntimeException("pattern");
    }
    this.pattern = pattern;
  }

  public Time getAsObject(String value) {
    if (Strings2.isEmpty(value)) {
      return null;
    }
    return TimeConversions.toTime(value, pattern);
  }

  public String getAsString(Object value) {
    if (isTarget(value.getClass())) {
      return new DecimalFormat(pattern).format(value);
    }
    return null;
  }

  public boolean isTarget(Class<?> clazz) {
    return clazz == Time.class;
  }

}
