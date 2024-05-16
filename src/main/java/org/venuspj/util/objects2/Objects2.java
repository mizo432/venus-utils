package org.venuspj.util.objects2;

import static org.venuspj.util.base.Preconditions.checkNotNull;
import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.collect.Lists2.newArrayListWithCapacity;
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
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.venuspj.util.collect.Sets2;
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

  public static ToStringHelper toStringHelper(Object self) {
    return new ToStringHelper(self);
  }


  public static final class ToStringHelper {

    private final Object instance;
    private final List<ValueHolder> valueHolders = newArrayList();
    boolean prettyPrint = true;
    private boolean omitNullValues = false;
    private boolean multiLine = false;
    private boolean hideFieldNames = false;

    private ToStringHelper(Object anInstance) {
      this.instance = anInstance;
    }

    /**
     * omitNullValuesフラグをtrueに設定します。
     * <p>
     * このフラグは、null値を出力から省略するかどうかを決定します。
     *
     * @return 現在のToStringHelperオブジェクト。
     */
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

    @Override
    public String toString() {

      if (!ToStringContext.INSTANCE.startProcessing(instance)) {
        return toSimpleReferenceString(instance);
      }

      try {
        if (isPrimitiveLike(instance.getClass())) {
          return instance.toString();
        }
        if (instance instanceof Enum<?>) {
          return ((Enum<?>) instance).name();
        }
        IndentationAwareStringBuilder builder = new IndentationAwareStringBuilder();
        if (isIterable(instance)) {
          internalToString(instance, builder);
          return builder.toString();
        }
        builder.append(toSimpleReferenceString(instance));
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
        builder.append(toSimpleReferenceString(instance));
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

    /**
     * インスタンスのクラスに宣言された全てのフィールドをToStringHelperオブジェクトに追加します。
     *
     * @return フィールドが追加されたToStringHelperオブジェクト。
     */
    public ToStringHelper addAllFields() {
      if (isNull(instance)) {
        return this;
      }
      if (isPrimitiveLike(instance.getClass()) || isIterable(instance)
          || instance instanceof Enum<?>) {
        return this;
      }
      for (Field field : instance.getClass().getDeclaredFields()) {
        addField(field);
      }
      return this;
    }

    /**
     * インスタンスのクラスで宣言されたすべてのフィールドをToStringHelperオブジェクトに追加します。
     *
     * @return フィールドが追加されたToStringHelperオブジェクト。
     */
    public ToStringHelper addAllDeclaredFields() {
      if (isNull(instance)) {
        return this;
      }
      if (isPrimitiveLike(instance.getClass()) || isIterable(instance)
          || instance instanceof Enum<?>) {
        return this;
      }
      for (Field field : getAllDeclaredFields(instance.getClass())) {
        addField(field);
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
        if (isPrimitiveLike(object.getClass())) {
          sb.append(object.toString());
          return;
        }
        if (object instanceof Enum<?>) {
          sb.append(((Enum<?>) object).name());
          return;
        }
        if (object instanceof Collection<?>) {
          serializeCollection((Collection<?>) object, sb);
          return;
        }
        if (object instanceof Map<?, ?>) {
          serializeMap((Map<?, ?>) object, sb);
          return;
        }
        if (object instanceof Object[]) {
          serializeCollection(Arrays.asList((Object[]) object), sb);
          return;
        }
        if (object instanceof CharSequence) {
          sb.append("\"")
              .append(((CharSequence) object).toString().replace("\n", "\\n").replace("\r", "\\r"))
              .append("\"");
          return;
        }
        if (object.getClass().isPrimitive()) {
          sb.append(String.valueOf(object));
        } else {
          toStringHelper(object).addAllDeclaredFields().configFrom(this).convertToString(sb);
        }
      } else {
        toStringHelper(object).addAllDeclaredFields().configFrom(this).convertToString(sb);
      }
    }

    private void serializeMap(Map<?, ?> object, IndentationAwareStringBuilder sb) {
      serializeCollection(object.entrySet(), sb);
    }

    private ToStringHelper configFrom(ToStringHelper toStringHelper) {
      this.omitNullValues = toStringHelper.omitNullValues;
      this.multiLine = toStringHelper.multiLine;
      this.hideFieldNames = toStringHelper.hideFieldNames;
      return this;
    }

    private static final Set<Class<?>> primitiveLikeClasses = newHashSet(
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
        Boolean.class,
        URI.class,
        Long.class,
        String.class,
        Enum.class,
        Double.class);

    private boolean isPrimitiveLike(Class<?> aClazz) {
      return primitiveLikeClasses.contains(aClazz);

    }

    private static final int MAX_RECORD = 5;

    private <I> void serializeCollection(Collection<I> object, IndentationAwareStringBuilder sb) {
      List<I> wkList = newArrayListWithCapacity(object.size());
      wkList.addAll(object);
      int collectionSize = wkList.size();
      sb.append("[");

      if (multiLine) {
        sb.increaseIndent();
      }
      for (int index = 0; index < collectionSize; index++) {
        if (multiLine) {
          sb.newLine();
        }
        if (MAX_RECORD <= index) {
          sb.append(" 計 ").append(String.valueOf(collectionSize)).append("件");
          break;
        }
        Object indexedObject = wkList.get(index);
        if (indexedObject instanceof Map.Entry<?, ?>) {
          Map.Entry<?, ?> entry = (Entry<?, ?>) indexedObject;
          internalToString(entry.getKey(), sb);
          sb.append(":");
          internalToString(entry.getValue(), sb);
        } else {
          internalToString(indexedObject, sb);
        }
        if (index + 1 < collectionSize && index + 1 != MAX_RECORD) {
          sb.append(", ");
        }

      }
      if (multiLine) {
        sb.decreaseIndent();
      }
      if (!(collectionSize == 0) && multiLine) {
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

  /**
   * このクラスはtoString() メソッドのためのコンテキストを提供し、オブジェクトが現在処理中かどうかを追跡できるようにします。
   * <p>
   * これにより、無限再帰を防ぎます。
   */
  static class ToStringContext {

    public static ToStringContext INSTANCE = new ToStringContext();

    /**
     * この変数は、toString()メソッドのコンテキストを提供し、オブジェクトが現在処理中であるかを追跡するThreadLocalオブジェクトです。
     * <p>
     * これにより、無限再帰が防止されます。
     *
     * @see ToStringContext#currentlyProcessed
     * @see ThreadLocal
     * @see Set
     */
    static ThreadLocal<Set<Object>> currentlyProcessed = new ThreadLocal<>();

    /**
     * 指定されたオブジェクトの処理を開始します。
     *
     * @param obj 処理するべきオブジェクト。
     * @return オブジェクトが正常に処理リストに追加された場合はtrue、それ以外の場合はfalseを返します。
     */
    public boolean startProcessing(Object obj) {
      if (isNull(currentlyProcessed.get())) {
        currentlyProcessed.set(Sets2.newHashSet());
      }
      return currentlyProcessed.get().add(obj);
    }

    /**
     * 現在処理中のオブジェクトから指定したオブジェクトを削除します。
     *
     * @param obj 現在処理中のオブジェクトから削除するオブジェクト
     */
    public void endProcessing(Object obj) {
      currentlyProcessed.get().remove(obj);

    }

  }
}
