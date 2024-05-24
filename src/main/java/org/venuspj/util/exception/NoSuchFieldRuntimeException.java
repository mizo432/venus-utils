package org.venuspj.util.exception;

import static org.venuspj.util.collect.Arrays2.asArray;

import java.io.Serial;

/**
 * {@link NoSuchFieldException}をラップする例外です。
 */
public class NoSuchFieldRuntimeException extends VRuntimeException {

  @Serial
  private static final long serialVersionUID = 6609175673610180338L;

  private final Class<?> targetClass;

  private final String fieldName;

  /**
   * {@link NoSuchFieldRuntimeException}を作成します。
   *
   * @param targetClass ターゲットクラス
   * @param fieldName フィールド名
   * @param cause 原因となった例外
   */
  public NoSuchFieldRuntimeException(final Class<?> targetClass,
      final String fieldName, final Throwable cause) {
    super("EUTL0070", asArray(targetClass.getName(), fieldName), cause);
    this.targetClass = targetClass;
    this.fieldName = fieldName;
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
   * フィールド名を返します。
   *
   * @return フィールド名
   */
  public String getFieldName() {
    return fieldName;
  }

}
