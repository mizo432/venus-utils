package org.venuspj.util.beans.converter;

import org.venuspj.util.beans.Converter;
import org.venuspj.util.convert.BooleanConversions;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.strings2.Strings2;

public class BooleanConverter implements Converter<Boolean> {


  @Override
  public boolean isTarget(Class<?> clazz) {
    return clazz == Boolean.class;
  }

  @Override
  public String getAsString(Object value) {
    if (isTarget(value.getClass())) {
      return StringConversions.toString(value);
    }
    return null;

  }

  @Override
  public Boolean getAsObject(String value) {
    if (Strings2.isEmpty(value)) {
      return null;
    }
    return BooleanConversions.toBoolean(value);

  }
}
