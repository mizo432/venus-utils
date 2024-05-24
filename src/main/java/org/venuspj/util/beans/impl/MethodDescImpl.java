package org.venuspj.util.beans.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.venuspj.util.precondition.Preconditions;
import org.venuspj.util.beans.BeanDesc;
import org.venuspj.util.beans.MethodDesc;
import org.venuspj.util.beans.ParameterizedClassDesc;
import org.venuspj.util.beans.factory.ParameterizedClassDescFactory;
import org.venuspj.util.exception.MethodNotStaticRuntimeException;
import org.venuspj.util.exception.NullArgumentException;
import org.venuspj.util.lang.Methods;

/**
 * {@link MethodDesc}の実装クラスです。
 */
public class MethodDescImpl implements MethodDesc {

  /**
   * このメソッドを所有するクラスの{@link BeanDesc}
   */
  protected final BeanDesc beanDesc;

  /**
   * メソッド
   */
  protected final Method method;

  /**
   * メソッド名
   */
  protected final String methodName;

  /**
   * メソッドの引数型の配列
   */
  protected final Class<?>[] parameterTypes;

  /**
   * メソッドの戻り値型
   */
  protected final Class<?> returnType;

  /**
   * パラメータ化された引数型の情報
   */
  protected final ParameterizedClassDesc[] parameterizedClassDescs;

  /**
   * パラメータ化された戻り値型の情報
   */
  protected final ParameterizedClassDesc parameterizedClassDesc;

  /**
   * インスタンスを構築します。
   *
   * @param beanDesc このメソッドを所有するクラスの{@link BeanDesc}。{@literal null}であってはいけません
   * @param method メソッド。{@literal null}であってはいけません
   */
  public MethodDescImpl(final BeanDesc beanDesc, final Method method) {
    Preconditions.checkNotNull(beanDesc, () -> new NullArgumentException("beanDesc"));
    Preconditions.checkNotNull(method, () -> new NullArgumentException("method"));

    this.beanDesc = beanDesc;
    this.method = method;
    methodName = method.getName();
    parameterTypes = method.getParameterTypes();
    returnType = method.getReturnType();
    parameterizedClassDescs =
        new ParameterizedClassDesc[parameterTypes.length];
    for (int i = 0; i < parameterTypes.length; ++i) {
      parameterizedClassDescs[i] =
          ParameterizedClassDescFactory.createParameterizedClassDesc(
              method,
              i,
              beanDesc.getTypeVariables());
    }
    parameterizedClassDesc =
        ParameterizedClassDescFactory.createParameterizedClassDesc(
            method,
            beanDesc.getTypeVariables());
  }

  @Override
  public BeanDesc getBeanDesc() {
    return beanDesc;
  }

  @Override
  public Method getMethod() {
    return method;
  }

  @Override
  public String getMethodName() {
    return methodName;
  }

  @Override
  public Class<?>[] getParameterTypes() {
    return parameterTypes;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> Class<T> getReturnType() {
    return (Class<T>) returnType;
  }

  @Override
  public boolean isPublic() {
    return Methods.isPublic(method);
  }

  @Override
  public boolean isStatic() {
    return Methods.isStatic(method);
  }

  @Override
  public boolean isFinal() {
    return Methods.isFinal(method);
  }

  @Override
  public boolean isAbstract() {
    return Methods.isAbstract(method);
  }

  @Override
  public boolean isParameterized(final int index) {
    Preconditions.checkArgumentArrayIndex("index", index, parameterTypes.length);

    return parameterizedClassDescs[index].isParameterizedClass();
  }

  @Override
  public boolean isParameterized() {
    return parameterizedClassDesc.isParameterizedClass();
  }

  @Override
  public ParameterizedClassDesc[] getParameterizedClassDescs() {
    return parameterizedClassDescs;
  }

  @Override
  public ParameterizedClassDesc getParameterizedClassDesc() {
    return parameterizedClassDesc;
  }

  @Override
  public Class<?> getElementClassOfCollection(final int index) {
    Preconditions.checkArgumentArrayIndex("index", index, parameterTypes.length);

    if (!Collection.class.isAssignableFrom(parameterTypes[index])
        || !isParameterized(index)) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDescs[index].getArguments()[0];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @Override
  public Class<?> getKeyClassOfMap(final int index) {
    Preconditions.checkArgumentArrayIndex("index", index, parameterTypes.length);

    if (!Map.class.isAssignableFrom(parameterTypes[index])
        || !isParameterized(index)) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDescs[index].getArguments()[0];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @Override
  public Class<?> getValueClassOfMap(final int index) {
    Preconditions.checkArgumentArrayIndex("index", index, parameterTypes.length);

    if (!Map.class.isAssignableFrom(parameterTypes[index])
        || !isParameterized(index)) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDescs[index].getArguments()[1];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @Override
  public Class<?> getElementClassOfCollection() {
    if (!Collection.class.isAssignableFrom(returnType)
        || !isParameterized()) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDesc.getArguments()[0];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @Override
  public Class<?> getKeyClassOfMap() {
    if (!Map.class.isAssignableFrom(returnType) || !isParameterized()) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDesc.getArguments()[0];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @Override
  public Class<?> getValueClassOfMap() {
    if (!Map.class.isAssignableFrom(returnType) || !isParameterized()) {
      return null;
    }
    final ParameterizedClassDesc pcd =
        parameterizedClassDesc.getArguments()[1];
    if (pcd == null) {
      return null;
    }
    return pcd.getRawClass();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T invoke(final Object target, final Object... args) {
    Preconditions.checkNotNull(target, () -> new NullArgumentException("target"));

    return (T) Methods.invoke(method, target, args);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T invokeStatic(final Object... args) {
    if (!isStatic()) {
      throw new MethodNotStaticRuntimeException(getBeanDesc()
          .getBeanClass(), methodName);
    }
    return (T) Methods.invokeStatic(method, args);
  }
}
