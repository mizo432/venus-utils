package org.venuspj.exception;

import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * プロパティが見つからなかった場合にスローされる例外です。
 *
 */
public class PropertyNotFoundRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = -5177019197796206774L;

    private final Class<?> targetClass;

    private final String propertyName;

    /**
     * {@link PropertyNotFoundRuntimeException}を返します。
     *
     * @param targetClass
     *            ターゲットクラス
     * @param propertyName
     *            プロパティ名
     */
    public PropertyNotFoundRuntimeException(final Class<?> targetClass,
                                            final String propertyName) {
        super("EUTL0065", asArray(targetClass.getName(), propertyName));
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

