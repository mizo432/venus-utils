package org.venuspj.util.exception;

import static org.venuspj.util.collect.Arrays2.asArray;

import java.lang.reflect.Field;

/**
 * オブジェクトを指定せずに非{@literal static}な{@link Field}にアクセスした場合にスローされる例外です。
 */
public class FieldNotStaticRuntimeException extends VRuntimeException {

  private static final long serialVersionUID = -7791347225750660981L;

  private final Class<?> targetClass;

  private final String fieldName;

  /**
   * {@link FieldNotStaticRuntimeException}を作成します。
   *
   * @param targetClass ターゲットクラス
   * @param fieldName フィールド名
   */
  public FieldNotStaticRuntimeException(final Class<?> targetClass,
      final String fieldName) {
    super("EUTL0099", asArray(targetClass.getName(), fieldName));
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
