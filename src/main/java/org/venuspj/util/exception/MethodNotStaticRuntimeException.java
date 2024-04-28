package org.venuspj.util.exception;

import static org.venuspj.util.collect.Arrays2.asArray;

import java.lang.reflect.Method;

/**
 * オブジェクトを指定せずに非{@literal static}な{@link Method}にアクセスした場合にスローされる例外です。
 */
public class MethodNotStaticRuntimeException extends VRuntimeException {

  private static final long serialVersionUID = 7186052234464152208L;

  private final Class<?> targetClass;

  private final String methodName;

  /**
   * {@link MethodNotStaticRuntimeException}を作成します。
   *
   * @param targetClass ターゲットクラス
   * @param methodName メソッド名
   */
  public MethodNotStaticRuntimeException(final Class<?> targetClass,
      final String methodName) {
    super("EUTL0100", asArray(targetClass.getName(), methodName));
    this.targetClass = targetClass;
    this.methodName = methodName;
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
   * メソッド名を返します。
   *
   * @return メソッド名
   */
  public String getMethodName() {
    return methodName;
  }

}
