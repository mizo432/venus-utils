package org.venuspj.util.beans.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Map;
import org.venuspj.util.precondition.Preconditions;
import org.venuspj.util.beans.BeanDesc;
import org.venuspj.util.beans.ConstructorDesc;
import org.venuspj.util.beans.ParameterizedClassDesc;
import org.venuspj.util.beans.factory.ParameterizedClassDescFactory;
import org.venuspj.util.exception.NullArgumentException;
import org.venuspj.util.lang.Constructors;

/**
 * {@link ConstructorDesc}の実装クラスです。
 */
public class ConstructorDescImpl implements ConstructorDesc {

  /**
   * このメソッドを所有するクラスの{@link BeanDesc}
   */
  protected final BeanDesc beanDesc;

  /**
   * コンストラクタ
   */
  protected final Constructor<?> constructor;

  /**
   * コンストラクタの引数型の配列
   */
  protected final Class<?>[] parameterTypes;

  /**
   * パラメータ化された引数型の情報
   */
  protected final ParameterizedClassDesc[] parameterizedClassDescs;

  /**
   * インスタンスを構築します。
   *
   * @param beanDesc このメソッドを所有するクラスの{@link BeanDesc}。{@literal null}であってはいけません
   * @param constructor コンストラクタ。{@literal null}であってはいけません
   */
  public ConstructorDescImpl(final BeanDesc beanDesc,
      final Constructor<?> constructor) {
    Preconditions.checkNotNull(beanDesc, () -> new NullArgumentException("beanDesc"));
    Preconditions.checkNotNull(constructor, () -> new NullArgumentException("constructor"));

    this.beanDesc = beanDesc;
    this.constructor = constructor;
    parameterTypes = constructor.getParameterTypes();
    parameterizedClassDescs =
        new ParameterizedClassDesc[parameterTypes.length];
    final Map<TypeVariable<?>, Type> typeVariables =
        beanDesc.getTypeVariables();
    for (int i = 0; i < parameterTypes.length; ++i) {
      parameterizedClassDescs[i] =
          ParameterizedClassDescFactory.createParameterizedClassDesc(
              constructor,
              i,
              typeVariables);
    }
  }

  @Override
  public BeanDesc getBeanDesc() {
    return beanDesc;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> Constructor<T> getConstructor() {
    return (Constructor<T>) constructor;
  }

  @Override
  public Class<?>[] getParameterTypes() {
    return parameterTypes;
  }

  @Override
  public boolean isPublic() {
    return Constructors.isPublic(constructor);
  }

  @Override
  public boolean isParameterized(final int index) {
    Preconditions.checkArgumentArrayIndex("index", index, parameterTypes.length);

    return parameterizedClassDescs[index].isParameterizedClass();
  }

  @Override
  public ParameterizedClassDesc[] getParameterizedClassDescs() {
    return parameterizedClassDescs;
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

  @SuppressWarnings("unchecked")
  @Override
  public <T> T newInstance(final Object... args) {
    return (T) Constructors.newInstance(constructor, args);
  }

}

