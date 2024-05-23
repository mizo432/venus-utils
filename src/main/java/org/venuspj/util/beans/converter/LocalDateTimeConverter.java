package org.venuspj.util.beans.converter;

import java.time.LocalDateTime;
import org.venuspj.util.beans.Converter;
import org.venuspj.util.convert.LocalDateTimeConversions;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.strings2.Strings2;


public class LocalDateTimeConverter implements Converter<LocalDateTime> {

  private final String pattern;

  public LocalDateTimeConverter(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean isTarget(Class<?> clazz) {
    return clazz == LocalDateTime.class;

  }

  @Override
  public String getAsString(Object value) {
    if (isTarget(value.getClass())) {
      return StringConversions.toString(value, pattern);

    }
    return null;

  }

  @Override
  public LocalDateTime getAsObject(String value) {
    if (Strings2.isEmpty(value)) {
      return null;
    }
    return LocalDateTimeConversions.toLocalDateTime(value, pattern);
  }
}
