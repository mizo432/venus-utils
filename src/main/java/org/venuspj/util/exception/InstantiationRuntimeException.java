package org.venuspj.util.exception;


import static org.venuspj.util.collect.Arrays2.asArray;

import java.io.Serial;

/**
 * {@link InstantiationException}をラップする例外です。
 */
public class InstantiationRuntimeException extends VRuntimeException {

  @Serial
  private static final long serialVersionUID = 5220902071756706607L;

  private final Class<?> targetClass;

  /**
   * {@link InstantiationRuntimeException}を作成します。
   *
   * @param targetClass ターゲットクラス
   * @param cause 原因となった例外
   */
  public InstantiationRuntimeException(final Class<?> targetClass,
      final InstantiationException cause) {
    super("EUTL0041", asArray(targetClass.getName(), cause), cause);
    this.targetClass = targetClass;
  }

  @Override
  public InstantiationRuntimeException initCause(final Throwable cause) {
    return (InstantiationRuntimeException) super.initCause(cause);
  }

  /**
   * ターゲットクラスを返します。
   *
   * @return ターゲットクラス
   */
  public Class<?> getTargetClass() {
    return targetClass;
  }

}
