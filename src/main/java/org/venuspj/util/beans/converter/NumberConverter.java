package org.venuspj.util.beans.converter;

import java.text.DecimalFormat;
import java.text.ParseException;
import org.venuspj.util.beans.Converter;
import org.venuspj.util.beans.EmptyRuntimeException;
import org.venuspj.util.exception.ParseRuntimeException;
import org.venuspj.util.primitives.Strings2;

/**
 * 数値用のコンバータです。
 */
public class NumberConverter implements Converter<Number> {

  /**
   * 数値のパターンです。
   */
  protected String pattern;

  /**
   * インスタンスを構築します。
   *
   * @param pattern 数値のパターン
   */
  public NumberConverter(String pattern) {
    if (Strings2.isEmpty(pattern)) {
      throw new EmptyRuntimeException("pattern");
    }
    this.pattern = pattern;
  }

  @Override
  public String getAsString(Object value) {
    if (isTarget(value.getClass())) {
      return new DecimalFormat(pattern).format(value);
    }
    return null;
  }

  public Number getAsObject(String value) {
    if (Strings2.isEmpty(value)) {
      return null;
    }
    try {
      return new DecimalFormat(pattern).parse(value);
    } catch (ParseException e) {
      throw new ParseRuntimeException(e);
    }

  }

  public boolean isTarget(Class<?> clazz) {
    return Number.class.isAssignableFrom(clazz);

  }

}

