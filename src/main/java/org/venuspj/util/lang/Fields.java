package org.venuspj.util.lang;

import static org.venuspj.util.collect.Arrays2.asArray;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import org.venuspj.util.base.Preconditions;
import org.venuspj.util.exception.IllegalAccessRuntimeException;
import org.venuspj.util.exception.NullArgumentException;
import org.venuspj.util.exception.VIllegalArgumentException;

/**
 * {@link Field}用のユーティリティクラスです。
 */
public abstract class Fields {

  public static final Field[] EMPTY_ARRAY = new Field[]{};

  /**
   * {@link Field}によって表される{@code static}フィールドの値を返します。
   *
   * @param <T> フィールドの型
   * @param field フィールド。{@literal null}であってはいけません
   * @return {@code static}フィールドで表現される値
   * @throws IllegalAccessRuntimeException 基本となるフィールドにアクセスできない場合
   * @see Field#get(Object)
   */
  @SuppressWarnings("unchecked")
  public static <T> T get(final Field field)
      throws IllegalAccessRuntimeException {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    return (T) get(field, null);
  }

  /**
   * 指定されたオブジェクトについて、{@link Field}によって表されるフィールドの値を返します。
   *
   * @param <T> フィールドの型
   * @param field フィールド。{@literal null}であってはいけません
   * @param target 表現されるフィールド値の抽出元オブジェクト。フィールドが{@literal static}の場合は {@literal null}
   * @return オブジェクト{@code obj}内で表現される値
   * @throws IllegalAccessRuntimeException 基本となるフィールドにアクセスできない場合
   * @see Field#get(Object)
   */
  @SuppressWarnings("unchecked")
  public static <T> T get(final Field field, final Object target)
      throws IllegalAccessRuntimeException {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    try {
      return (T) field.get(target);
    } catch (final IllegalAccessException ex) {
      throw new IllegalAccessRuntimeException(
          field.getDeclaringClass(),
          ex);
    }
  }

  /**
   * {@literal static}な {@link Field}の値をintとして取得します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @return フィールドの値
   * @throws IllegalAccessRuntimeException {@link IllegalAccessException}が発生した場合
   * @see #getInt(Field, Object)
   */
  public static int getInt(final Field field)
      throws IllegalAccessRuntimeException {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    return getInt(field, null);
  }

  /**
   * {@link Field}の値をintとして取得します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @param target ターゲットオブジェクト。フィールドが{@literal static}の場合は{@literal null}
   * @return フィールドの値
   * @throws IllegalAccessRuntimeException {@link IllegalAccessException}が発生した場合
   * @see Field#getInt(Object)
   */
  public static int getInt(final Field field, final Object target)
      throws IllegalAccessRuntimeException {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    try {
      return field.getInt(target);
    } catch (final IllegalAccessException ex) {
      throw new IllegalAccessRuntimeException(
          field.getDeclaringClass(),
          ex);
    }
  }

  /**
   * {@literal static}な {@link Field}の値を {@link String}として取得します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @return フィールドの値
   * @throws IllegalAccessRuntimeException {@link IllegalAccessException}が発生した場合
   * @see #getString(Field, Object)
   */
  public static String getString(final Field field)
      throws IllegalAccessRuntimeException {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    return getString(field, null);
  }

  /**
   * {@link Field}の値を {@link String}として取得します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @param target ターゲットオブジェクト。フィールドが{@literal static}の場合は{@literal null}
   * @return フィールドの値
   * @throws IllegalAccessRuntimeException {@link IllegalAccessException}が発生した場合
   * @see Field#get(Object)
   */
  public static String getString(final Field field, final Object target)
      throws IllegalAccessRuntimeException {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    try {
      return (String) field.get(target);
    } catch (final IllegalAccessException ex) {
      throw new IllegalAccessRuntimeException(
          field.getDeclaringClass(),
          ex);
    }
  }

  /**
   * {@link Field}オブジェクトによって表される{@code static}フィールドを、指定された新しい値に設定します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @param value {@literal static}フィールドの新しい値
   * @throws IllegalAccessRuntimeException 基本となるフィールドにアクセスできない場合
   * @see Field#set(Object, Object)
   */
  public static void set(final Field field, final Object value)
      throws IllegalAccessRuntimeException {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    set(field, null, value);
  }

  /**
   * {@link Field}オブジェクトによって表される指定されたオブジェクト引数のフィールドを、指定された新しい値に設定します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @param target フィールドを変更するオブジェクト。フィールドが{@literal static}の場合は{@literal null}
   * @param value 変更中の{@code target}の新しいフィールド値
   * @throws IllegalAccessRuntimeException 基本となるフィールドにアクセスできない場合
   * @see Field#set(Object, Object)
   */
  public static void set(final Field field, final Object target,
      final Object value) throws IllegalAccessRuntimeException {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    try {
      field.set(target, value);
    } catch (final IllegalAccessException e) {
      throw new IllegalAccessRuntimeException(
          field.getDeclaringClass(),
          e);
    } catch (final IllegalArgumentException e) {
      final Class<?> clazz = field.getDeclaringClass();
      final Class<?> fieldClass = field.getType();
      final Class<?> valueClass = value == null ? null : value.getClass();
      final Class<?> targetClass =
          target == null ? field.getDeclaringClass() : target.getClass();
      throw new VIllegalArgumentException("field", "EUTL0094", asArray(
          clazz.getName(),
          clazz.getClassLoader(),
          fieldClass.getName(),
          fieldClass.getClassLoader(),
          field.getName(),
          valueClass == null ? null : valueClass.getName(),
          valueClass == null ? null : valueClass.getClassLoader(),
          value,
          targetClass == null ? null : targetClass.getName(),
          targetClass == null ? null : targetClass.getClassLoader()), e);
    }
  }

  /**
   * インスタンスフィールドかどうか返します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @return インスタンスフィールドなら{@literal true}
   */
  public static boolean isInstanceField(final Field field) {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    return !Modifier.isStatic(field.getModifiers());
  }

  /**
   * パブリックフィールドかどうか返します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @return パブリックフィールドなら{@literal true}
   */
  public static boolean isPublicField(final Field field) {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    return Modifier.isPublic(field.getModifiers());
  }

  /**
   * ファイナルフィールドかどうか返します。
   *
   * @param field フィールド。{@literal null}であってはいけません
   * @return ファイナルフィールドなら{@literal true}
   */
  public static boolean isFinalField(final Field field) {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    return Modifier.isFinal(field.getModifiers());
  }

  /**
   * パラメタ化されたコレクション型のフィールドの要素型を返します。
   *
   * @param field パラメタ化されたコレクション型のフィールド。{@literal null}であってはいけません
   * @return コレクションの要素型
   */
  public static Class<?> getElementTypeOfCollection(final Field field) {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    final Type type = field.getGenericType();
    return Genericses.getRawClass(Genericses
        .getElementTypeOfCollection(type));
  }

  /**
   * パラメタ化されたマップ型のフィールドのキー型を返します。
   *
   * @param field パラメタ化されたマップ型のフィールド。{@literal null}であってはいけません
   * @return マップのキー型
   */
  public static Class<?> getKeyTypeOfMap(final Field field) {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    final Type type = field.getGenericType();
    return Genericses.getRawClass(Genericses.getKeyTypeOfMap(type));
  }

  /**
   * パラメタ化されたマップ型のフィールドの値型を返します。
   *
   * @param field パラメタ化されたマップ型のフィールド。{@literal null}であってはいけません
   * @return マップの値型
   */
  public static Class<?> getValueTypeOfMap(final Field field) {
    Preconditions.checkNotNull(field, () -> new NullArgumentException("field"));

    final Type type = field.getGenericType();
    return Genericses.getRawClass(Genericses.getValueTypeOfMap(type));
  }

}

