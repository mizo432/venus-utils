package org.venuspj.util.lang;

import static org.venuspj.util.base.Preconditions.checkNotNull;
import static org.venuspj.util.collect.Arrays2.asArray;
import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.collect.Maps2.newHashMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.venuspj.util.base.StringPreconditions;
import org.venuspj.util.exception.ClassNotFoundRuntimeException;
import org.venuspj.util.exception.EmptyArgumentException;
import org.venuspj.util.exception.IllegalAccessRuntimeException;
import org.venuspj.util.exception.InstantiationRuntimeException;
import org.venuspj.util.exception.InvocationTargetRuntimeException;
import org.venuspj.util.exception.NoSuchConstructorRuntimeException;
import org.venuspj.util.exception.NoSuchFieldRuntimeException;
import org.venuspj.util.exception.NoSuchMethodRuntimeException;
import org.venuspj.util.exception.NullArgumentException;
import org.venuspj.util.strings2.Strings2;

/**
 * {@link Class}用のユーティリティクラスです。
 */
public abstract class Classes {

  /**
   * ラッパー型からプリミティブ型へのマップ
   */
  protected static final Map<Class<?>, Class<?>> wrapperToPrimitiveMap =
      newHashMap();

  /**
   * プリミティブ型からラッパー型へのマップ
   */
  protected static final Map<Class<?>, Class<?>> primitiveToWrapperMap =
      newHashMap();

  /**
   * プリミティブ型の名前からクラスへのマップ
   */
  protected static final Map<String, Class<?>> primitiveNameToClassMap =
      newHashMap();

  static {
    wrapperToPrimitiveMap.put(Boolean.class, Boolean.TYPE);
    wrapperToPrimitiveMap.put(Character.class, Character.TYPE);
    wrapperToPrimitiveMap.put(Byte.class, Byte.TYPE);
    wrapperToPrimitiveMap.put(Short.class, Short.TYPE);
    wrapperToPrimitiveMap.put(Integer.class, Integer.TYPE);
    wrapperToPrimitiveMap.put(Long.class, Long.TYPE);
    wrapperToPrimitiveMap.put(Float.class, Float.TYPE);
    wrapperToPrimitiveMap.put(Double.class, Double.TYPE);

    primitiveToWrapperMap.put(Boolean.TYPE, Boolean.class);
    primitiveToWrapperMap.put(Character.TYPE, Character.class);
    primitiveToWrapperMap.put(Byte.TYPE, Byte.class);
    primitiveToWrapperMap.put(Short.TYPE, Short.class);
    primitiveToWrapperMap.put(Integer.TYPE, Integer.class);
    primitiveToWrapperMap.put(Long.TYPE, Long.class);
    primitiveToWrapperMap.put(Float.TYPE, Float.class);
    primitiveToWrapperMap.put(Double.TYPE, Double.class);

    primitiveNameToClassMap.put(Boolean.TYPE.getName(), Boolean.TYPE);
    primitiveNameToClassMap.put(Character.TYPE.getName(), Character.TYPE);
    primitiveNameToClassMap.put(Byte.TYPE.getName(), Byte.TYPE);
    primitiveNameToClassMap.put(Short.TYPE.getName(), Short.TYPE);
    primitiveNameToClassMap.put(Integer.TYPE.getName(), Integer.TYPE);
    primitiveNameToClassMap.put(Long.TYPE.getName(), Long.TYPE);
    primitiveNameToClassMap.put(Float.TYPE.getName(), Float.TYPE);
    primitiveNameToClassMap.put(Double.TYPE.getName(), Double.TYPE);
  }

  /**
   * 現在のスレッドのコンテキストクラスローダを使って、 指定された文字列名を持つクラスまたはインタフェースに関連付けられた、 {@link Class}オブジェクトを返します。
   *
   * @param <T> {@link Class}オブジェクトが表すクラス
   * @param className 要求するクラスの完全修飾名。{@literal null}や空文字列であってはいけません
   * @return 指定された名前を持つクラスの{@link Class}オブジェクト
   * @throws ClassNotFoundRuntimeException クラスが見つからなかった場合
   * @see Class#forName(String, boolean, ClassLoader)
   */
  public static <T> Class<T> forName(final String className)
      throws ClassNotFoundRuntimeException {
    StringPreconditions.checkNotEmpty(className, () -> new EmptyArgumentException(
        "className",
        "EUTL0010",
        asArray("className")));

    return forName(className, Thread
        .currentThread()
        .getContextClassLoader());
  }

  /**
   * 指定されたクラスローダを使って、 指定された文字列名を持つクラスまたはインタフェースに関連付けられた{@link Class} オブジェクトを返します。
   *
   * @param <T> {@link Class}オブジェクトが表すクラス
   * @param className 要求するクラスの完全修飾名。{@literal null}や空文字列であってはいけません
   * @param loader クラスのロード元である必要があるクラスローダ
   * @return 指定された名前を持つクラスの{@link Class}オブジェクト
   * @throws EmptyArgumentException クラス名が{@literal null}または空文字列だった場合
   * @throws ClassNotFoundRuntimeException クラスが見つからなかった場合
   * @see Class#forName(String, boolean, ClassLoader)
   */
  @SuppressWarnings("unchecked")
  public static <T> Class<T> forName(final String className,
      final ClassLoader loader) throws ClassNotFoundRuntimeException {
    StringPreconditions.checkNotEmpty(className, () -> new EmptyArgumentException(
        "className",
        "EUTL0010",
        asArray("className")));
    try {
      return (Class<T>) Class.forName(className, true, loader);
    } catch (final ClassNotFoundException e) {
      throw new ClassNotFoundRuntimeException(e);
    }
  }

  /**
   * 現在のスレッドのコンテキストクラスローダを使って、 指定された文字列名を持つクラスまたはインタフェースに関連付けられた、 {@link Class}オブジェクトを返します。
   * <p>
   * クラスが見つからなかった場合は{@code null}を返します。
   * </p>
   *
   * @param <T> {@link Class}オブジェクトが表すクラス
   * @param className 要求するクラスの完全修飾名
   * @return 指定された名前を持つクラスの{@link Class}オブジェクト
   * @see Class#forName(String)
   */
  public static <T> Class<T> forNameNoException(final String className) {
    return forNameNoException(className, Thread
        .currentThread()
        .getContextClassLoader());
  }

  /**
   * 指定されたクラスローダを使って、 指定された文字列名を持つクラスまたはインタフェースに関連付けられた、 {@link Class} オブジェクトを返します。
   * <p>
   * クラスが見つからなかった場合は{@code null}を返します。
   * </p>
   *
   * @param <T> {@link Class}オブジェクトが表すクラス
   * @param className 要求するクラスの完全修飾名
   * @param loader クラスのロード元である必要があるクラスローダ
   * @return 指定された名前を持つクラスの{@link Class}オブジェクト
   * @see Class#forName(String)
   */
  @SuppressWarnings("unchecked")
  public static <T> Class<T> forNameNoException(final String className,
      final ClassLoader loader) {
    if (Strings2.isEmpty(className)) {
      return null;
    }
    try {
      return (Class<T>) Class.forName(className, true, loader);
    } catch (final Throwable ignore) {
      return null;
    }
  }

  /**
   * プリミティブクラスの場合は、ラッパークラスに変換して返します。
   *
   * @param className クラス名。{@literal null}や空文字列であってはいけません
   * @return {@link Class}
   * @throws EmptyArgumentException クラス名が{@literal null}または空文字列だった場合
   * @throws ClassNotFoundRuntimeException {@link ClassNotFoundException}がおきた場合
   * @see #forName(String)
   */
  public static Class<?> convertClass(final String className)
      throws ClassNotFoundRuntimeException {
    StringPreconditions.checkNotEmpty(className, () -> new EmptyArgumentException(
        "className",
        "EUTL0010",
        asArray("className")));
    final Class<?> clazz = primitiveNameToClassMap.get(className);
    if (clazz != null) {
      return clazz;
    }
    return forName(className);
  }

  /**
   * 指定されたクラスのデフォルトコンストラクタで、クラスの新しいインスタンスを作成および初期化します。
   *
   * @param <T> {@link Class}オブジェクトが表すクラス
   * @param clazz クラスを表す{@link Class}オブジェクト。{@literal null}であってはいけません
   * @return デフォルトコンストラクタを呼び出すことで作成される新規オブジェクト
   * @throws InstantiationRuntimeException 基本となるコンストラクタを宣言するクラスが{@code abstract}クラスを表す場合
   * @throws IllegalAccessRuntimeException 実パラメータ数と仮パラメータ数が異なる場合、 プリミティブ引数のラップ解除変換が失敗した場合、
   * またはラップ解除後、 メソッド呼び出し変換によってパラメータ値を対応する仮パラメータ型に変換できない場合、 このコンストラクタが列挙型に関連している場合
   * @see Constructor#newInstance(Object[])
   */
  public static <T> T newInstance(final Class<T> clazz)
      throws InstantiationRuntimeException, IllegalAccessRuntimeException {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));

    try {
      return clazz.getConstructor(new Class[]{}).newInstance();
    } catch (final InstantiationException e) {
      throw new InstantiationRuntimeException(clazz, e);
    } catch (final IllegalAccessException e) {
      throw new IllegalAccessRuntimeException(clazz, e);
    } catch (NoSuchMethodException e) {
      throw new NoSuchMethodRuntimeException(clazz, clazz.getSimpleName(), new Class[]{}, e);
    } catch (InvocationTargetException e) {
      throw new InvocationTargetRuntimeException(clazz, e);
    }
  }

  /**
   * 指定されたクラスをコンテキストクラスローダから取得し、デフォルトコンストラクタで、クラスの新しいインスタンスを作成および初期化します。
   *
   * @param <T> 生成するインスタンスの型
   * @param className クラス名。{@literal null}や空文字列であってはいけません
   * @return デフォルトコンストラクタを呼び出すことで作成される新規オブジェクト
   * @throws ClassNotFoundRuntimeException クラスが見つからなかった場合
   * @throws InstantiationRuntimeException 基本となるコンストラクタを宣言するクラスが{@code abstract}クラスを表す場合
   * @throws IllegalAccessRuntimeException 実パラメータ数と仮パラメータ数が異なる場合、 プリミティブ引数のラップ解除変換が失敗した場合、
   * またはラップ解除後、 メソッド呼び出し変換によってパラメータ値を対応する仮パラメータ型に変換できない場合、 このコンストラクタが列挙型に関連している場合
   * @see #newInstance(Class)
   * @see #forName(String)
   */
  @SuppressWarnings("unchecked")
  public static <T> T newInstance(final String className)
      throws ClassNotFoundRuntimeException,
      InstantiationRuntimeException, IllegalAccessRuntimeException {
    checkNotNull(className, () -> new NullArgumentException("className"));

    return (T) newInstance(forName(className));
  }

  /**
   * 指定されたクラスを指定のクラスローダから取得し、デフォルトコンストラクタで、クラスの新しいインスタンスを作成および初期化します。
   *
   * @param <T> 生成するインスタンスの型
   * @param className クラス名。{@literal null}や空文字列であってはいけません
   * @param loader クラスローダ
   * @return 新しいインスタンス
   * @throws ClassNotFoundRuntimeException {@link ClassNotFoundException}がおきた場合
   * @throws InstantiationRuntimeException {@link InstantiationException}がおきた場合
   * @throws IllegalAccessRuntimeException {@link IllegalAccessException}がおきた場合
   * @see #newInstance(Class)
   * @see #forName(String, ClassLoader)
   */
  @SuppressWarnings("unchecked")
  public static <T> T newInstance(final String className,
      final ClassLoader loader) throws ClassNotFoundRuntimeException,
      InstantiationRuntimeException, IllegalAccessRuntimeException {
    StringPreconditions.checkNotEmpty(className, () -> new EmptyArgumentException(
        "className",
        "EUTL0010",
        asArray("className")));

    return (T) newInstance(forName(className, loader));
  }

  /**
   * 代入可能かどうかを返します。
   *
   * @param toClass 代入先のクラス。{@literal null}であってはいけません
   * @param fromClass 代入元のクラス。{@literal null}であってはいけません
   * @return 代入可能かどうか
   * @see Class#isAssignableFrom(Class)
   */
  public static boolean isAssignableFrom(final Class<?> toClass,
      Class<?> fromClass) {
    checkNotNull(toClass, () -> new NullArgumentException("toClass"));
    checkNotNull(fromClass, () -> new NullArgumentException("fromClass"));

    if (toClass == Object.class && !fromClass.isPrimitive()) {
      return true;
    }
    if (toClass.isPrimitive()) {
      fromClass = getPrimitiveClassIfWrapper(fromClass);
    }
    return toClass.isAssignableFrom(fromClass);
  }

  /**
   * ラッパークラスをプリミティブクラスに変換します。
   *
   * @param clazz ラッパークラス。{@literal null}であってはいけません
   * @return 引数がラッパークラスならプリミティブクラス、それ以外の場合は{@literal null}
   */
  public static Class<?> getPrimitiveClass(@NotNull final Class<?> clazz) {
    checkNotNull(clazz, "clazz");

    return wrapperToPrimitiveMap.get(clazz);
  }

  /**
   * ラッパークラスならプリミティブクラスに、 そうでなければそのままクラスを返します。
   *
   * @param clazz クラス。{@literal null}であってはいけません
   * @return 引数がラッパークラスならプリミティブクラス、それ以外の場合は引数で渡されたクラス
   */
  public static Class<?> getPrimitiveClassIfWrapper(final Class<?> clazz) {
    checkNotNull(clazz, "clazz");

    final Class<?> ret = getPrimitiveClass(clazz);
    if (ret != null) {
      return ret;
    }
    return clazz;
  }

  /**
   * プリミティブクラスをラッパークラスに変換します。
   *
   * @param clazz プリミティブクラス。{@literal null}であってはいけません
   * @return 引数がプリミティブクラスならラッパークラス、それ以外の場合は{@literal null}
   */
  public static Class<?> getWrapperClass(final Class<?> clazz) {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));

    return primitiveToWrapperMap.get(clazz);
  }

  /**
   * クラスがプリミティブの場合はラッパークラス、そうでない場合はもとのクラスを返します。
   *
   * @param clazz クラス。{@literal null}であってはいけません
   * @return 引数がプリミティブクラスならラッパークラス、それ以外の場合は引数で渡されたクラス
   */
  public static Class<?> getWrapperClassIfPrimitive(final Class<?> clazz) {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));

    final Class<?> ret = getWrapperClass(clazz);
    if (ret != null) {
      return ret;
    }
    return clazz;
  }

  /**
   * {@link Class}オブジェクトが表すクラスの指定された{@code public}コンストラクタをリフレクトする {@link Constructor}オブジェクトを返します。
   *
   * @param <T> {@link Class}オブジェクトが表すクラス
   * @param clazz クラスの{@link Class}オブジェクト。{@literal null}であってはいけません
   * @param argTypes パラメータ配列
   * @return 指定された{@code argTypes}と一致する{@code public}コンストラクタの {@link Constructor}オブジェクト
   * @throws NoSuchConstructorRuntimeException 一致するコンストラクタが見つからない場合
   * @see Class#getConstructor(Class...)
   */
  public static <T> Constructor<T> getConstructor(final Class<T> clazz,
      final Class<?>... argTypes)
      throws NoSuchConstructorRuntimeException {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));

    try {
      return clazz.getConstructor(argTypes);
    } catch (final NoSuchMethodException e) {
      throw new NoSuchConstructorRuntimeException(clazz, argTypes, e);
    }
  }

  /**
   * {@link Class}オブジェクトが表すクラスまたはインタフェースの指定されたコンストラクタをリフレクトする {@link Constructor}オブジェクトを返します。
   *
   * @param <T> {@link Class}オブジェクトが表すクラス
   * @param clazz クラスの{@link Class}オブジェクト。{@literal null}であってはいけません
   * @param argTypes パラメータ配列
   * @return 指定された{@code argTypes}と一致するコンストラクタの{@link Constructor}オブジェクト
   * @throws NoSuchConstructorRuntimeException 一致するコンストラクタが見つからない場合
   * @see Class#getDeclaredConstructor(Class...)
   */
  public static <T> Constructor<T> getDeclaredConstructor(
      final Class<T> clazz, final Class<?>... argTypes)
      throws NoSuchConstructorRuntimeException {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));

    try {
      return clazz.getDeclaredConstructor(argTypes);
    } catch (final NoSuchMethodException e) {
      throw new NoSuchConstructorRuntimeException(clazz, argTypes, e);
    }
  }

  /**
   * {@link Class}オブジェクトが表すクラスまたはインタフェースの指定された{@code public}メンバフィールドをリフレクトする
   * {@link Field}オブジェクトを返します。
   *
   * @param clazz クラスの{@link Class}オブジェクト。{@literal null}であってはいけません
   * @param name フィールド名。{@literal null}や空文字列であってはいけません
   * @return {@code name}で指定されたこのクラスの{@link Field}オブジェクト
   * @throws EmptyArgumentException フィールド名が{@literal null}または空文字列だった場合
   * @throws NoSuchFieldRuntimeException 指定された名前のフィールドが見つからない場合
   * @see Class#getField(String)
   */
  public static Field getField(final Class<?> clazz, final String name)
      throws NoSuchFieldRuntimeException {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));
    StringPreconditions.checkNotEmpty(name, () -> new EmptyArgumentException(
        "name",
        "EUTL0010",
        asArray("name")));

    try {
      return clazz.getField(name);
    } catch (final NoSuchFieldException e) {
      throw new NoSuchFieldRuntimeException(clazz, name, e);
    }
  }

  /**
   * {@link Class}オブジェクトが表すクラスまたはインタフェースの指定された宣言フィールドをリフレクトする{@link Field} オブジェクトを返します。
   *
   * @param clazz クラスの{@link Class}オブジェクト。{@literal null}であってはいけません
   * @param name フィールド名。{@literal null}や空文字列であってはいけません
   * @return {@code name}で指定されたこのクラスの{@link Field}オブジェクト
   * @throws NoSuchFieldRuntimeException 指定された名前のフィールドが見つからない場合
   * @see Class#getDeclaredField(String)
   */
  public static Field getDeclaredField(final Class<?> clazz, final String name)
      throws NoSuchFieldRuntimeException {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));
    StringPreconditions.checkNotEmpty(name, () -> new EmptyArgumentException(
        "name",
        "EUTL0010",
        asArray("name")));

    try {
      return clazz.getDeclaredField(name);
    } catch (final NoSuchFieldException e) {
      throw new NoSuchFieldRuntimeException(clazz, name, e);
    }
  }

  /**
   * {@link Class}オブジェクトが表すクラスまたはインタフェースの指定された{@code public}メンバメソッドをリフレクトする
   * {@link Method}オブジェクトを返します。
   *
   * @param clazz クラスの{@link Class}オブジェクト。{@literal null}であってはいけません
   * @param name メソッドの名前。{@literal null}や空文字列であってはいけません
   * @param argTypes パラメータのリスト
   * @return 指定された{@code name}および{@code argTypes}と一致する{@link Method}オブジェクト
   * @throws EmptyArgumentException メソッド名が{@literal null}または空文字列だった場合
   * @throws NoSuchMethodRuntimeException 一致するメソッドが見つからない場合
   * @see Class#getMethod(String, Class...)
   */
  public static Method getMethod(final Class<?> clazz, final String name,
      final Class<?>... argTypes) throws NoSuchMethodRuntimeException {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));
    StringPreconditions.checkNotEmpty(name, () -> new EmptyArgumentException(
        "name",
        "EUTL0010",
        asArray("name")));

    try {
      return clazz.getMethod(name, argTypes);
    } catch (final NoSuchMethodException e) {
      throw new NoSuchMethodRuntimeException(clazz, name, argTypes, e);
    }
  }

  /**
   * {@link Class}オブジェクトが表すクラスまたはインタフェースの指定されたメンバメソッドをリフレクトする{@link Method} オブジェクトを返します。
   *
   * @param clazz クラスの{@link Class}オブジェクト。{@literal null}であってはいけません
   * @param name メソッドの名前。{@literal null}や空文字列であってはいけません
   * @param argTypes パラメータのリスト
   * @return 指定された{@code name}および{@code argTypes}と一致する{@link Method}オブジェクト
   * @throws NoSuchMethodRuntimeException 一致するメソッドが見つからない場合
   * @see Class#getDeclaredMethod(String, Class...)
   */
  public static Method getDeclaredMethod(final Class<?> clazz,
      final String name, final Class<?>... argTypes)
      throws NoSuchMethodRuntimeException {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));
    StringPreconditions.checkNotEmpty(name, () -> new EmptyArgumentException(
        "name",
        "EUTL0010",
        asArray("name")));

    try {
      return clazz.getDeclaredMethod(name, argTypes);
    } catch (final NoSuchMethodException e) {
      throw new NoSuchMethodRuntimeException(clazz, name, argTypes, e);
    }
  }

  /**
   * パッケージ名を返します。
   *
   * @param clazz クラス。{@literal null}であってはいけません
   * @return パッケージ名
   */
  public static String getPackageName(final Class<?> clazz) {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));

    final String fqcn = clazz.getName();
    final int pos = fqcn.lastIndexOf('.');
    if (pos > 0) {
      return fqcn.substring(0, pos);
    }
    return null;
  }

  /**
   * FQCNからパッケージ名を除いた名前を返します。
   *
   * @param className クラス名。{@literal null}や空文字列であってはいけません
   * @return FQCNからパッケージ名を除いた名前
   */
  public static String getShortClassName(final String className) {
    StringPreconditions.checkNotEmpty(className, () -> new EmptyArgumentException(
        "className",
        "EUTL0010",
        asArray("className")));

    final int i = className.lastIndexOf('.');
    if (i > 0) {
      return className.substring(i + 1);
    }
    return className;
  }

  /**
   * FQCNをパッケージ名とFQCNからパッケージ名を除いた名前に分けます。
   *
   * @param className クラス名。{@literal null}や空文字列であってはいけません
   * @return パッケージ名とFQCNからパッケージ名を除いた名前
   */
  public static String[] splitPackageAndShortClassName(final String className) {
    StringPreconditions.checkNotEmpty(className, () -> new EmptyArgumentException(
        "className",
        "EUTL0010",
        asArray("className")));

    final String[] ret = new String[2];
    final int i = className.lastIndexOf('.');
    if (i > 0) {
      ret[0] = className.substring(0, i);
      ret[1] = className.substring(i + 1);
    } else {
      ret[1] = className;
    }
    return ret;
  }

  /**
   * 配列の場合は要素のクラス名に{@literal []}を加えた文字列、それ以外はクラス名そのものを返します。
   *
   * @param clazz クラス。{@literal null}であってはいけません
   * @return クラス名
   */
  public static String getSimpleClassName(final Class<?> clazz) {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));

    if (clazz.isArray()) {
      return getSimpleClassName(clazz.getComponentType()) + "[]";
    }
    return clazz.getName();
  }

  /**
   * クラス名をリソースパスとして表現します。
   *
   * @param clazz クラス。{@literal null}であってはいけません
   * @return リソースパス
   * @see #getResourcePath(String)
   */
  public static String getResourcePath(final Class<?> clazz) {
    checkNotNull(clazz, () -> new NullArgumentException("clazz"));

    return getResourcePath(clazz.getName());
  }

  /**
   * クラス名をリソースパスとして表現します。
   *
   * @param className クラス名。{@literal null}や空文字列であってはいけません
   * @return リソースパス
   */
  public static String getResourcePath(final String className) {
    StringPreconditions.checkNotEmpty(className, () -> new EmptyArgumentException(
        "className",
        "EUTL0010",
        asArray("className")));

    return Strings2.replace(className, ".", "/") + ".class";
  }

  /**
   * クラス名の要素を結合します。
   *
   * @param s1 クラス名の要素1
   * @param s2 クラス名の要素2
   * @return 結合された名前
   */
  public static String concatName(final String s1, final String s2) {
    if (Strings2.isEmpty(s1) && Strings2.isEmpty(s2)) {
      return null;
    }
    if (!Strings2.isEmpty(s1) && Strings2.isEmpty(s2)) {
      return s1;
    }
    if (Strings2.isEmpty(s1) && !Strings2.isEmpty(s2)) {
      return s2;
    }
    return s1 + '.' + s2;
  }

  public static Class<?>[] empty() {
    return new Class[0];
  }

  /**
   * クラスの宣言フィールドのリストです。SecurityExceptionによりフィールドにアクセスできない場合は空のリストを返します。
   *
   * @param aClass フィールドを取得するクラス
   * @return クラスとそのスーパークラスで宣言されたフィールドのリスト
   */
  public static List<Field> getAllDeclaredFieldsSafely(Class<?> aClass) {
    List<Field> results = newArrayList();

    for (Class<?> clazz = aClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
      List<Field> fields = getDeclaredFieldsSafely(clazz);
      results.addAll(fields);
    }

    return results;
  }

  /**
   * 与えられたクラスの宣言フィールドを安全に取得するために用いられます
   *
   * @param clazz 宣言フィールドを取得するためのクラス
   * @return クラスの宣言フィールドのリストです。SecurityExceptionによりフィールドにアクセスできない場合は空のリストを返します。
   */
  public static List<Field> getDeclaredFieldsSafely(Class<?> clazz) {
    try {
      return Arrays.asList(clazz.getDeclaredFields());
    } catch (SecurityException ignored) {
      return Collections.emptyList();
    }
  }

  private static final Map<Class<?>, Field[]> declaredFieldsCache =
      newHashMap();
  private static final Field[] NO_FIELDS = {};

  /**
   * 指定された名前を持つフィールドを指定されたクラスおよびそのスーパークラスから探します。
   *
   * @param clazz フィールドを検索するクラスです。
   * @param name 検索するフィールドの名前です。もしnullの場合は、どのフィールド名でもマッチします。
   * @return 指定された名前と型に一致するフィールド、またはそんなフィールドが見つからない場合はnullを返します。
   */
  public static Field findField(Class<?> clazz, String name) {
    return findField(clazz, name, null);

  }

  /**
   * 指定されたクラスとそのスーパークラスで、指定された名前と型のフィールドを検索します。
   *
   * @param clazz フィールドを検索するクラス。
   * @param name 検索するフィールドの名前。nullの場合、任意のフィールド名が一致します。
   * @param type 検索するフィールドの型。nullの場合、任意のフィールドタイプが一致します。
   * @return 名前とタイプが一致するフィールド、または一致するフィールドが見つからない場合はnull。
   */
  public static Field findField(Class<?> clazz, String name, Class<?> type) {
    Class<?> searchType = clazz;
    while (Object.class != searchType && searchType != null) {
      Field[] fields = getDeclaredFields(searchType);
      for (Field field : fields) {
        if ((name == null || name.equals(field.getName())) &&
            (type == null || type.equals(field.getType()))) {
          return field;
        }
      }
      searchType = searchType.getSuperclass();
    }
    return null;
  }

  /**
   * 指定されたクラスで宣言されているすべてのフィールドの配列を返します。
   *
   * @param clazz 宣言されたフィールドを見つけるクラス
   * @return クラスのすべての宣言されたフィールドを表すFieldオブジェクトの配列を返します。 宣言されたフィールドがない場合は、空の配列が返されます。
   */
  private static Field[] getDeclaredFields(Class<?> clazz) {
    Field[] result = declaredFieldsCache.get(clazz);
    if (result == null) {
      try {
        result = clazz.getDeclaredFields();
        declaredFieldsCache.put(clazz, (result.length == 0 ? NO_FIELDS : result));

      } catch (SecurityException ignored) {
        return Fields.EMPTY_ARRAY;
      }
    }
    return result;

  }

  /**
   * フィールドが公開（public）されていない場合や最終（final）化されている場合でも、そのフィールドをアクセス可能にします。
   * フィールドがまだアクセス可能ではない場合、そのアクセス可能フラグを true に設定します。
   *
   * @param field アクセス可能にするフィールド
   */
  public static void makeAccessible(Field field) {
    if ((!Modifier.isPublic(field.getModifiers()) ||
        !Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
        Modifier.isFinal(field.getModifiers()))) {
      field.setAccessible(true);
    }
  }

  /**
   * 指定したオブジェクト上のフィールドの値にアクセスします。
   *
   * @param field アクセスするフィールド。
   * @param target フィールドがアクセスされるべきオブジェクト。
   * @return オブジェクト上のフィールドの値。
   * @throws IllegalStateException 想定外のリフレクション例外が発生した場合。
   */
  public static Object getFieldValue(Field field, Object target) {
    try {
      return field.get(target);
    } catch (IllegalAccessException ex) {
      handleReflectionException(ex);
      throw new IllegalStateException(
          "Unexpected reflection exception - " + ex.getClass().getName() + ": " + ex.getMessage());
    }
  }

  /**
   * 受け取った例外のタイプに基づき、適切な例外をスローすることでリフレクション例外を処理します。
   *
   * @param ex 処理するリフレクション例外。
   * @throws IllegalStateException 例外がNoSuchMethodExceptionあるいはIllegalAccessExceptionの場合にスローされます。
   * @throws UndeclaredThrowableException 例外がRuntimeExceptionの場合にスローされます。
   */
  public static void handleReflectionException(Exception ex) {
    if (ex instanceof NoSuchMethodException) {
      throw new IllegalStateException("Method not found: " + ex.getMessage());
    }
    if (ex instanceof IllegalAccessException) {
      throw new IllegalStateException("Could not access method: " + ex.getMessage());
    }
    if (ex instanceof InvocationTargetException) {
      handleInvocationTargetException((InvocationTargetException) ex);
    }
    if (ex instanceof RuntimeException) {
      throw (RuntimeException) ex;
    }
    throw new UndeclaredThrowableException(ex);
  }

  /**
   * InvocationTargetExceptionを処理します。
   * <p>
   * これは通常、リフレクションを使用してメソッドを呼び出す際に、そのメソッドが例外をスローした場合に発生します
   *
   * @param ex InvocationTargetExceptionであり、メソッド内部でこの例外を処理します
   */
  private static void handleInvocationTargetException(InvocationTargetException ex) {
    rethrowRuntimeException(ex.getTargetException());
  }

  /**
   * ランタイム例外またはエラーを再スローします。与えられたスローアブルがRuntimeExceptionのインスタンスである場合、 それはそのままスローされます。
   * <p>
   * 与えられたスローアブルがErrorのインスタンスである場合、それもそのままスローされます。
   * それ以外の場合、与えられた例外を原因とするUndeclaredThrowableExceptionがスローされます。
   *
   * @param ex 再スローするスローアブル
   * @throws RuntimeException 与えられたスローアブルがRuntimeExceptionのインスタンスの場合
   * @throws Error 与えられたスローアブルがErrorのインスタンスの場合
   * @throws UndeclaredThrowableException 与えられたスローアブルがRuntimeExceptionでもErrorでもない場合
   */
  public static void rethrowRuntimeException(Throwable ex) {
    if (ex instanceof RuntimeException) {
      throw (RuntimeException) ex;
    }
    if (ex instanceof Error) {
      throw (Error) ex;
    }
    throw new UndeclaredThrowableException(ex);
  }

}
