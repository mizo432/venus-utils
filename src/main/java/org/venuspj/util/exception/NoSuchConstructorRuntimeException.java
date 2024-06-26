package org.venuspj.util.exception;

import static org.venuspj.util.collect.Arrays2.asArray;

import java.io.Serial;
import java.lang.reflect.Constructor;
import org.venuspj.util.lang.Methods;

/**
 * {@link Constructor}が見つからない場合にスローされる{@link NoSuchMethodException}をラップする例外です。
 */
public class NoSuchConstructorRuntimeException extends VRuntimeException {

  @Serial
  private static final long serialVersionUID = 8688818589925114466L;

  private final Class<?> targetClass;

  private final Class<?>[] argTypes;

  /**
   * {@link NoSuchConstructorRuntimeException}を作成します。
   *
   * @param targetClass ターゲットクラス
   * @param argTypes 引数型の並び
   * @param cause 原因となった例外
   */
  public NoSuchConstructorRuntimeException(final Class<?> targetClass,
      final Class<?>[] argTypes, final Throwable cause) {
    super(
        "EUTL0064",
        asArray(
            targetClass.getName(),
            Methods.getSignature(targetClass.getSimpleName(), argTypes)),
        cause);
    this.targetClass = targetClass;
    this.argTypes = argTypes;
  }

  /**
   * ターゲットクラスを返します。
   *
   * @return ターゲットクラス
   */
  public Class<?> getTargetClass() {
    return targetClass;
  }

  /**
   * 引数型の並びを返します。
   *
   * @return 引数型の並び
   */
  public Class<?>[] getArgTypes() {
    return argTypes;
  }

}
