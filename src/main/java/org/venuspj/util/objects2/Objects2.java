package org.venuspj.util.objects2;

import static org.venuspj.util.base.Preconditions.checkNotNull;
import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.collect.Maps2.newHashMap;
import static org.venuspj.util.collect.Sets2.newHashSet;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.venuspj.util.strings2.Strings2;

public class Objects2 {

  public static boolean isNull(Object object) {
    return Objects.isNull(object);
  }

  public static boolean nonNull(Object object) {
    return Objects.nonNull(object);
  }

  public static boolean equal(Object a, Object b) {
    return Objects.equals(a, b);
  }

  public static int hash(Object... values) {
    return Objects.hash(values);
  }

  public static ToStringHelper toStringHelper(Class<?> selfClass) {
    return toStringHelper(selfClass.getSimpleName());
  }

  public static ToStringHelper toStringHelper(Object self) {
    return new ToStringHelper(self);
  }


  public static final class ToStringHelper {

    private final String className;
    private final Object instance;
    private final List<ValueHolder> valueHolders = newArrayList();
    boolean prettyPrint = true;
    private boolean omitNullValues = false;
    private boolean multiLine = false;
    private boolean hideFieldNames = false;

    private ToStringHelper(String className) {
      this.className = checkNotNull(className);
      this.instance = null;
    }

    private ToStringHelper(Object anInstance) {
      this.className = anInstance.getClass().getSimpleName();
      this.instance = anInstance;
    }

    public ToStringHelper omitNullValues() {
      omitNullValues = true;
      return this;
    }

    public ToStringHelper add(String name, Object value) {
      return addHolder(name, value);
    }

    public ToStringHelper add(String name, boolean value) {
      return addHolder(name, String.valueOf(value));
    }

    public ToStringHelper add(String name, char value) {
      return addHolder(name, String.valueOf(value));
    }

    public ToStringHelper add(String name, double value) {
      return addHolder(name, String.valueOf(value));
    }

    public ToStringHelper add(String name, float value) {
      return addHolder(name, String.valueOf(value));
    }

    public ToStringHelper add(String name, int value) {
      return addHolder(name, String.valueOf(value));
    }

    public ToStringHelper add(String name, long value) {
      return addHolder(name, String.valueOf(value));
    }

    public ToStringHelper addValue(Object value) {
      return addHolder(value);
    }

    public ToStringHelper addValue(boolean value) {
      return addHolder(String.valueOf(value));
    }

    public ToStringHelper addValue(char value) {
      return addHolder(String.valueOf(value));
    }

    public ToStringHelper addValue(double value) {
      return addHolder(String.valueOf(value));
    }

    public ToStringHelper addValue(float value) {
      return addHolder(String.valueOf(value));
    }

    public ToStringHelper addValue(int value) {
      return addHolder(String.valueOf(value));
    }

    public ToStringHelper addValue(long value) {
      return addHolder(String.valueOf(value));
    }

    @Override
    public String toString() {

      if (!ToStringContext.INSTANCE.startProcessing(instance)) {
        return toSimpleReferenceString(instance);
      }

      try {
          if (isPrimitiveLike(instance.getClass())) {
              return instance.toString();
          }
        IndentationAwareStringBuilder builder = new IndentationAwareStringBuilder();
        if (isIterable(instance)) {
          internalToString(instance, builder);
          return builder.toString();
        }
        builder.append(className);
        builder.append("{");
        String nextSeparator = "";
        if (multiLine && valueHolders.size() > 1) {
          builder.increaseIndent();
        }
        for (ValueHolder part : valueHolders) {
          if (!omitNullValues || part.value != null) {
            if (multiLine && valueHolders.size() > 1) {
              builder.newLine();
            } else {
              builder.append(nextSeparator);
              nextSeparator = ", ";
            }
            if (part.name != null && !hideFieldNames) {
              builder.append(part.name).append(":");
            }
            internalToString(part.value, builder);
          }
        }
        if (multiLine && valueHolders.size() > 1) {
          builder.decreaseIndent().newLine();
        }
        builder.append("}");
        return builder.toString();
      } finally {
        ToStringContext.INSTANCE.endProcessing(instance);
      }
    }

    private boolean isIterable(Object object) {
      return (object instanceof Iterable<?>) ||
          (object instanceof Map<?, ?>) ||
          (object instanceof Object[]) ||
          (object instanceof byte[]) ||
          (object instanceof char[]) ||
          (object instanceof int[]) ||
          (object instanceof boolean[]) ||
          (object instanceof long[]) ||
          (object instanceof float[]) ||
          (object instanceof double[]);

    }

    private void convertToString(IndentationAwareStringBuilder builder) {
      if (!ToStringContext.INSTANCE.startProcessing(instance)) {
        builder.append(toSimpleReferenceString(instance));
        return;
      }
      try {
        builder.append(className);
        builder.append("{");
        String nextSeparator = "";
        if (multiLine && valueHolders.size() > 1) {
          builder.increaseIndent();
        }
        for (ValueHolder part : valueHolders) {
          if (!omitNullValues || part.value != null) {
            if (multiLine && valueHolders.size() > 1) {
              builder.newLine();
            } else {
              builder.append(nextSeparator);
              nextSeparator = ", ";
            }
            if (part.name != null && !hideFieldNames && valueHolders.size() != 1) {
              builder.append(part.name).append(":");
            }
            internalToString(part.value, builder);
          }
        }
        if (multiLine && valueHolders.size() > 1) {
          builder.decreaseIndent().newLine();
        }
        builder.append("}");
        return;
      } finally {
        ToStringContext.INSTANCE.endProcessing(instance);
      }
    }

    private ToStringHelper.ValueHolder addHolder() {
      ToStringHelper.ValueHolder valueHolder = new ToStringHelper.ValueHolder();
      valueHolders.add(valueHolder);
      return valueHolder;
    }

    private ToStringHelper addHolder(Object value) {
      ToStringHelper.ValueHolder valueHolder = addHolder();
      valueHolder.value = value;
      return this;
    }

    private ToStringHelper addHolder(String name, Object value) {
      ToStringHelper.ValueHolder valueHolder = addHolder();
      valueHolder.value = value;
      valueHolder.name = checkNotNull(name);
      return this;
    }

    public ToStringHelper addAllFields() {
      if (nonNull(instance)) {
          for (Field field : instance.getClass().getDeclaredFields()) {
              addField(field);
          }
      }
      return this;
    }

    public ToStringHelper addAllDeclaredFields() {
        if (nonNull(instance)) {
            for (Field field : getAllDeclaredFields(instance.getClass())) {
                addField(field);
            }
        }
      return this;
    }

    private List<Field> getAllDeclaredFields(Class<?> clazz) {
      Class<?> current = clazz;
      List<Field> result = newArrayList();
      while (nonNull(current)) {
        result.addAll(newArrayList(current.getDeclaredFields()));
        current = current.getSuperclass();

      }
      return result;
    }

    private void addField(Field field) {
      try {
        if (!Modifier.isStatic(field.getModifiers())) {
          field.setAccessible(true);
          add(field.getName(), field.get(instance));
        }
      } catch (IllegalAccessException ignore) {
        // この例外が出ても握り潰す
      }
    }

    private String toSimpleReferenceString(Object obj) {
      return obj.getClass().getSimpleName() + "@" + System.identityHashCode(obj);
    }

    public ToStringHelper multiLine() {
      multiLine = true;
      return this;

    }

    ToStringHelper verbatimValues() {
      prettyPrint = false;
      return this;
    }

    public ToStringHelper defaultConfig() {
      addAllDeclaredFields()
          .omitNullValues()
          .multiLine();
      return this;
    }

    private void internalToString(Object object, IndentationAwareStringBuilder sb) {
      if (isNull(object)) {
        sb.append("null");
        return;
      }
      if (prettyPrint) {
        if (object instanceof Iterable<?>) {
          serializeIterable((Iterable) object, sb);
        } else if (object instanceof Map<?, ?>) {
          serializeMap((Map) object, sb);
        } else if (object instanceof Object[]) {
          sb.append(Arrays.toString((Object[]) object));
        } else if (object instanceof byte[]) {
          sb.append(Arrays.toString((byte[]) object));
        } else if (object instanceof char[]) {
          sb.append(Arrays.toString((char[]) object));
        } else if (object instanceof int[]) {
          sb.append(Arrays.toString((int[]) object));
        } else if (object instanceof boolean[]) {
          sb.append(Arrays.toString((boolean[]) object));
        } else if (object instanceof long[]) {
          sb.append(Arrays.toString((long[]) object));
        } else if (object instanceof float[]) {
          sb.append(Arrays.toString((float[]) object));
        } else if (object instanceof double[]) {
          sb.append(Arrays.toString((double[]) object));
        } else if (object instanceof CharSequence) {
          sb.append("\"")
              .append(((CharSequence) object).toString().replace("\n", "\\n").replace("\r", "\\r"))
              .append("\"");
        } else if (object instanceof Enum<?>) {
          sb.append(((Enum) object).name());
        } else if (object.getClass().isPrimitive()) {
          sb.append(String.valueOf(object));
        } else if (isPrimitiveLike(object.getClass())) {
          sb.append(String.valueOf(object));
        } else {
          toStringHelper(object).addAllDeclaredFields().configFrom(this).convertToString(sb);
        }
      } else {
        toStringHelper(object).addAllDeclaredFields().configFrom(this).convertToString(sb);
      }
    }

    private void serializeMap(Map object, IndentationAwareStringBuilder sb) {
      serializeIterable(object.entrySet(), sb);
    }

    private ToStringHelper configFrom(ToStringHelper toStringHelper) {
      this.omitNullValues = toStringHelper.omitNullValues;
      this.multiLine = toStringHelper.multiLine;
      this.hideFieldNames = toStringHelper.hideFieldNames;
      return this;
    }

    private boolean isPrimitiveLike(Class<?> aClazz) {
      final Set<Class<?>> primitiveLikeClasses = newHashSet(
          Integer.class,
          LocalTime.class,
          LocalDateTime.class,
          YearMonth.class,
          Year.class,
          LocalDate.class,
          Short.class,
          UUID.class,
          Currency.class,
          Locale.class,
          Boolean.class, URI.class, Long.class, Double.class);
      return primitiveLikeClasses.contains(aClazz);

    }

    private void serializeIterable(Iterable<?> object, IndentationAwareStringBuilder sb) {
      Iterator<?> iterator = object.iterator();
      sb.append(object.getClass().getSimpleName()).append("[");
      if (multiLine) {
        sb.increaseIndent();
      }
      boolean wasEmpty = true;
      while (iterator.hasNext()) {
        wasEmpty = false;
        if (multiLine) {
          sb.newLine();
        }
        internalToString(iterator.next(), sb);
        if (iterator.hasNext()) {
          sb.append(",");
        }
      }
      if (multiLine) {
        sb.decreaseIndent();
      }
      if (!wasEmpty && multiLine) {
        sb.newLine();
      }
      sb.append("]");
    }

    private static final class ValueHolder {

      String name;
      Object value;
    }

    private static class IndentationAwareStringBuilder {

      StringBuilder builder = new StringBuilder();
      String indentationString = "  ";
      String newLineString = "\n";

      int indentation = 0;

      IndentationAwareStringBuilder increaseIndent() {
        indentation++;
        return this;
      }

      IndentationAwareStringBuilder decreaseIndent() {
        indentation--;
        return this;
      }

      IndentationAwareStringBuilder append(CharSequence string) {
        if (indentation > 0) {
          String replacement = newLineString + Strings2.repeat(indentationString, indentation);
          String indented = string.toString().replace(newLineString, replacement);
          builder.append(indented);
        } else {
          builder.append(string);
        }
        return this;
      }

      IndentationAwareStringBuilder newLine() {
        builder.append(newLineString).append(Strings2.repeat(indentationString, indentation));
        return this;
      }

      public String toString() {
        return builder.toString();
      }
    }
  }

  static class ToStringContext {

    public static ToStringContext INSTANCE = new ToStringContext();

    static ThreadLocal<Map<Object, Boolean>> currentlyProcessed = new ThreadLocal<>();

    public boolean startProcessing(Object obj) {
        if (isNull(currentlyProcessed.get())) {
            currentlyProcessed.set(newHashMap());
        }
      return currentlyProcessed.get().put(obj, Boolean.TRUE) == null;
    }

    public void endProcessing(Object obj) {
      currentlyProcessed.get().remove(obj);
    }

  }
}
