package org.venuspj.exception;

import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * プロパティの値の設定に失敗したときにスローされる例外です。
 */
public class IllegalPropertyRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = 3584516316082904020L;

    private final Class<?> targetClass;

    private final String propertyName;

    /**
     * {@link IllegalPropertyRuntimeException}を作成します。
     *
     * @param targetClass  ターゲットクラス
     * @param propertyName プロパティ名
     * @param cause        原因となった例外
     */
    public IllegalPropertyRuntimeException(final Class<?> targetClass,
                                           final String propertyName, final Throwable cause) {
        super(
                "EUTL0059",
                asArray(targetClass.getName(), propertyName, cause),
                cause);
        this.targetClass = targetClass;
        this.propertyName = propertyName;
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
     * プロパティ名を返します。
     *
     * @return プロパティ名
     */
    public String getPropertyName() {
        return propertyName;
    }

}
