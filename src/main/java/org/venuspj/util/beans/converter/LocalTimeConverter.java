package org.venuspj.util.beans.converter;

import java.time.LocalTime;
import org.venuspj.util.convert.LocalTimeConversions;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.strings2.Strings2;

public class LocalTimeConverter implements Converter<LocalTime> {

  private final String pattern;

  public LocalTimeConverter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean isTarget(Class<?> clazz) {
    return clazz == LocalTime.class;
  }

  @Override
  public String getAsString(Object value) {
    if (isTarget(value.getClass())) {
      return StringConversions.toString(value, pattern);
    }

    return null;


  }

  @Override
  public LocalTime getAsObject(String value) {
    if (Strings2.isEmpty(value)) {
      return null;
    }
    return LocalTimeConversions.toLocalTime(value, pattern);

  }
}
