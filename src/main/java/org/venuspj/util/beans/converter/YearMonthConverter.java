package org.venuspj.util.beans.converter;

import java.time.YearMonth;
import org.venuspj.util.beans.Converter;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.convert.YearMonthConversions;
import org.venuspj.util.primitives.Strings2;

public class YearMonthConverter implements Converter<YearMonth> {

  private final String pattern;


  public YearMonthConverter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean isTarget(Class<?> clazz) {
    return clazz == YearMonth.class;
  }

  @Override
  public String getAsString(Object value) {
    if (isTarget(value.getClass())) {
      return StringConversions.toString(value, pattern);
    }
    return null;

  }

  @Override
  public YearMonth getAsObject(String value) {
    if (Strings2.isEmpty(value)) {
      return null;
    }
    return YearMonthConversions.toYearMonth(value, pattern);

  }
}
