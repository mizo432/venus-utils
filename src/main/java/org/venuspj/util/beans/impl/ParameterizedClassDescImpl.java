package org.venuspj.util.beans.impl;

import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;

import org.venuspj.util.beans.ParameterizedClassDesc;

/**
 * {@link ParameterizedClassDesc}の実装クラスです。
 */
public class ParameterizedClassDescImpl implements ParameterizedClassDesc {

  /**
   * 原型となるクラス
   */
  protected Class<?> rawClass;

  /**
   * 型引数を表す{@link ParameterizedClassDesc}の配列
   */
  protected ParameterizedClassDesc[] arguments;

  /**
   * インスタンスを構築します。
   *
   * @param rawClass 原型となるクラス。{@literal null}であってはいけません
   */
  public ParameterizedClassDescImpl(final Class<?> rawClass) {
    assertArgumentNotNull("rawClass", rawClass);

    this.rawClass = rawClass;
  }

  /**
   * インスタンスを構築します。
   *
   * @param rawClass 原型となるクラス。{@literal null}であってはいけません
   * @param arguments 型引数を表す{@link ParameterizedClassDesc}の配列
   */
  public ParameterizedClassDescImpl(final Class<?> rawClass,
      final ParameterizedClassDesc[] arguments) {
    assertArgumentNotNull("rawClass", rawClass);

    this.rawClass = rawClass;
    this.arguments = arguments;
  }

  @Override
  public boolean isParameterizedClass() {
    return arguments != null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> Class<T> getRawClass() {
    return (Class<T>) rawClass;
  }

  /**
   * 原型となるクラスを設定します。
   *
   * @param rawClass 原型となるクラス。{@literal null}であってはいけません
   */
  public void setRawClass(final Class<?> rawClass) {
    assertArgumentNotNull("rawClass", rawClass);

    this.rawClass = rawClass;
  }

  @Override
  public ParameterizedClassDesc[] getArguments() {
    return arguments;
  }

  /**
   * 型引数を表す{@link ParameterizedClassDesc}の配列を設定します。
   *
   * @param arguments 型引数を表す{@link ParameterizedClassDesc}の配列
   */
  public void setArguments(final ParameterizedClassDesc[] arguments) {
    this.arguments = arguments;
  }

}
