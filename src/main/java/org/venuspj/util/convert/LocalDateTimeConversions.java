package org.venuspj.util.convert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class LocalDateTimeConversions {

  public static LocalDateTime toLocalDateTime(String value, String pattern) {
    if(value == null || value.isEmpty()) {
      return null;
    }
    return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
  }
}
