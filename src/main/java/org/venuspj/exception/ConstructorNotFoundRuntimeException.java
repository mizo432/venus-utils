package org.venuspj.exception;

import java.lang.reflect.Constructor;

import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * {@link Constructor}が見つからなかったときにスローされる例外Vです。
 */
public class ConstructorNotFoundRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = 8584662068396978822L;

    private final Class<?> targetClass;

    private final Object[] methodArgs;

    private final Class<?>[] paramTypes;

    /**
     * {@link ConstructorNotFoundRuntimeException}を作成します。
     *
     * @param targetClass
     *            ターゲットクラス
     * @param methodArgs
     *            引数の並び
     */
    public ConstructorNotFoundRuntimeException(final Class<?> targetClass,
                                               final Object[] methodArgs) {
        super("EUTL0048", asArray(
                targetClass.getName(),
                getSignature(methodArgs)));
        this.targetClass = targetClass;
        this.methodArgs = methodArgs;
        paramTypes = null;
    }

    /**
     * {@link ConstructorNotFoundRuntimeException}を作成します。
     *
     * @param targetClass
     *            ターゲットクラス
     * @param paramTypes
     *            引数型の並び
     */
    public ConstructorNotFoundRuntimeException(final Class<?> targetClass,
                                               final Class<?>[] paramTypes) {
        super("EUTL0048", asArray(
                targetClass.getName(),
                getSignature(paramTypes)));
        this.targetClass = targetClass;
        this.paramTypes = paramTypes;
        methodArgs = null;
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
     * 引数の並びを返します。
     *
     * @return 引数の並び
     */
    public Object[] getMethodArgs() {
        return methodArgs;
    }

    /**
     * 引数型の並びを返します。
     *
     * @return 引数型の並び
     */
    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    private static String getSignature(final Object... methodArgs) {
        if (methodArgs == null || methodArgs.length == 0) {
            return "";
        }
        final StringBuilder buf = new StringBuilder(100);
        for (final Object arg : methodArgs) {
            if (arg != null) {
                buf.append(arg.getClass().getName());
            } else {
                buf.append("null");
            }
            buf.append(", ");
        }
        buf.setLength(buf.length() - 2);
        return new String(buf);
    }

    private static String getSignature(final Class<?>... paramTypes) {
        if (paramTypes == null || paramTypes.length == 0) {
            return "";
        }
        final StringBuilder buf = new StringBuilder(100);
        for (final Class<?> type : paramTypes) {
            if (type != null) {
                buf.append(type.getName());
            } else {
                buf.append("null");
            }
            buf.append(", ");
        }
        buf.setLength(buf.length() - 2);
        return buf.toString();
    }

}

