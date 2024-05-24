package org.venuspj.util.exception;

import static org.venuspj.util.collect.Arrays2.asArray;

import java.io.Serial;
import java.lang.reflect.InvocationTargetException;


/**
 * {@link InvocationTargetException}をラップする例外です。
 */
public class InvocationTargetRuntimeException extends VRuntimeException {

  @Serial
  private static final long serialVersionUID = 7760491787158046906L;

  private final Class<?> targetClass;

  /**
   * {@link InvocationTargetRuntimeException}を作成します。
   *
   * @param targetClass ターゲットクラス
   * @param cause 原因となった例外
   */
  public InvocationTargetRuntimeException(final Class<?> targetClass,
      final InvocationTargetException cause) {
    super("EUTL0043", asArray(
        targetClass.getName(),
        cause.getTargetException()), cause.getTargetException());
    this.targetClass = targetClass;
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
