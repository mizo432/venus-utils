package org.venuspj.util.base;

import static org.venuspj.util.base.Preconditions.checkNotNull;

import java.io.Serial;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * {@link Enum}インスタンスを扱うためのユーティリティメソッド群
 */
public final class Enums {

  private Enums() {
  }

  /**
   * 指定されたenum定数を表すFieldオブジェクトを返します。
   *
   * @param enumValue enum定数
   * @return 指定されたenum定数を表すFieldオブジェクト
   */
  public static Field getField(Enum<?> enumValue) {
    Class<?> clazz = enumValue.getDeclaringClass();
    try {
      return clazz.getDeclaredField(enumValue.name());
    } catch (NoSuchFieldException impossible) {
      throw new AssertionError(impossible);
    }
  }

  /**
   * 指定されたEnumクラスのEnum定数と一致する値を返すOptionalを取得します。
   *
   * @param <T> Enum型
   * @param enumClass Enum型を表すクラスオブジェクト
   * @param value 一致する値
   * @return Enum定数が見つかった場合はそのEnum定数を含むOptional、見つからなかった場合は空のOptionalを返します。
   */
  public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> enumClass, String value) {
    checkNotNull(enumClass);
    checkNotNull(value);
    return Platform.getEnumIfPresent(enumClass, value);
  }

  private static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>>
      enumConstantCache = new WeakHashMap<>();

  private static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> populateCache(
      Class<T> enumClass) {
    Map<String, WeakReference<? extends Enum<?>>> result = new HashMap<>();
    for (T enumInstance : EnumSet.allOf(enumClass)) {
      result.put(enumInstance.name(), new WeakReference<Enum<?>>(enumInstance));
    }
    enumConstantCache.put(enumClass, result);
    return result;
  }

  static <T extends Enum<T>> Map<String, WeakReference<? extends Enum<?>>> getEnumConstants(
      Class<T> enumClass) {
    synchronized (enumConstantCache) {
      Map<String, WeakReference<? extends Enum<?>>> constants = enumConstantCache.get(enumClass);
      if (constants == null) {
        constants = populateCache(enumClass);
      }
      return constants;
    }
  }

  /**
   * 指定した列挙型クラスのEnum定数に文字列を変換するConverterを返します。
   *
   * @param enumClass Enum型のクラスオブジェクト
   * @param <T> Enum型
   * @return 文字列をEnum定数に変換するためのConverter
   */
  public static <T extends Enum<T>> Converter<String, T> stringConverter(final Class<T> enumClass) {
    return new StringConverter<T>(enumClass);
  }

  private static final class StringConverter<T extends Enum<T>> extends Converter<String, T>
      implements Serializable {

    private final Class<T> enumClass;

    StringConverter(Class<T> enumClass) {
      this.enumClass = checkNotNull(enumClass);
    }

    @Override
    protected T doForward(String value) {
      return Enum.valueOf(enumClass, value);
    }

    @Override
    protected String doBackward(T enumValue) {
      return enumValue.name();
    }

    @Override
    public boolean equals(Object object) {
      if (object instanceof StringConverter) {
        StringConverter<?> that = (StringConverter<?>) object;
        return this.enumClass.equals(that.enumClass);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return enumClass.hashCode();
    }

    @Override
    public String toString() {
      return "Enums.stringConverter(" + enumClass.getName() + ".class)";
    }

    @Serial
    private static final long serialVersionUID = 0L;
  }
}
