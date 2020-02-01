package org.venuspj.util.exception;


import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * {@link IllegalAccessException}をラップする例外です。
 */
public class IllegalAccessRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = -3649900343028907465L;

    private final Class<?> targetClass;

    /**
     * {@link IllegalAccessRuntimeException}を作成します。
     *
     * @param targetClass ターゲットクラス
     * @param cause       原因となった例外
     */
    public IllegalAccessRuntimeException(final Class<?> targetClass,
                                         final IllegalAccessException cause) {
        super("EUTL0042", asArray(targetClass.getName(), cause), cause);
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
