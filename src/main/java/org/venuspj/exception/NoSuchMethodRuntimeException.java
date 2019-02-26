package org.venuspj.exception;

import org.venuspj.util.lang.MethodUtil;

import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * {@link NoSuchMethodException}をラップする例外です。
 */
public class NoSuchMethodRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = -5673845060079098617L;

    private final Class<?> targetClass;

    private final String methodName;

    private final Class<?>[] argTypes;

    /**
     * {@link NoSuchMethodRuntimeException}を作成します。
     *
     * @param targetClass ターゲットクラス
     * @param methodName  メソッド名
     * @param argTypes    引数型の並び
     * @param cause       原因となった例外
     */
    public NoSuchMethodRuntimeException(final Class<?> targetClass,
                                        final String methodName, final Class<?>[] argTypes,
                                        final Throwable cause) {
        super("EUTL0057", asArray(
                targetClass.getName(),
                MethodUtil.getSignature(methodName, argTypes)), cause);
        this.targetClass = targetClass;
        this.methodName = methodName;
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
     * メソッド名を返します。
     *
     * @return メソッド名
     */
    public String getMethodName() {
        return methodName;
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
