package org.venuspj.util.beans.converter;

import java.time.MonthDay;
import org.venuspj.util.beans.Converter;
import org.venuspj.util.convert.MonthDayConversions;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.primitives.Strings2;

public class MonthDayConverter implements Converter<MonthDay> {

  private final String pattern;

  public MonthDayConverter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean isTarget(Class<?> clazz) {
    return clazz == MonthDay.class;

  }

  @Override
  public String getAsString(Object value) {
    if (isTarget(value.getClass())) {
      return StringConversions.toString(value, pattern);
    }

    return null;

  }

  @Override
  public MonthDay getAsObject(String value) {
    if (Strings2.isEmpty(value)) {
      return null;
    }
    return MonthDayConversions.toMonthDay(value, pattern);

  }
}
