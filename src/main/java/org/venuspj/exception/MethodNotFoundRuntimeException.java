package org.venuspj.exception;


import org.venuspj.util.lang.MethodUtil;

import java.lang.reflect.Method;

import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * {@link Method}が見つからなかったときにスローされる例外です。
 */
public class MethodNotFoundRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = -3508955801981550317L;

    private final Class<?> targetClass;

    private final String methodName;

    private final Class<?>[] methodArgClasses;

    /**
     * {@link MethodNotFoundRuntimeException}を作成します。
     *
     * @param targetClass
     *            ターゲットクラス
     * @param methodName
     *            メソッド名
     * @param methodArgs
     *            引数の並び
     */
    public MethodNotFoundRuntimeException(final Class<?> targetClass,
                                          final String methodName, final Object[] methodArgs) {
        this(targetClass, methodName, toClassArray(methodArgs));
    }

    /**
     * {@link MethodNotFoundRuntimeException}を作成します。
     *
     * @param targetClass
     *            ターゲットクラス
     * @param methodName
     *            メソッド名
     * @param methodArgClasses
     *            引数型の並び
     */
    public MethodNotFoundRuntimeException(final Class<?> targetClass,
                                          final String methodName, final Class<?>[] methodArgClasses) {
        super("EUTL0049", asArray(
                targetClass.getName(),
                MethodUtil.getSignature(methodName, methodArgClasses)));
        this.targetClass = targetClass;
        this.methodName = methodName;
        this.methodArgClasses = methodArgClasses;
    }

    /**
     * ターゲットの{@link Class}を返します。
     *
     * @return ターゲットの{@link Class}
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
     * メソッドの引数の{@link Class}の配列を返します。
     *
     * @return メソッドの引数の{@link Class}の配列
     */
    public Class<?>[] getMethodArgClasses() {
        return methodArgClasses;
    }

    private static Class<?>[] toClassArray(final Object... methodArgs) {
        if (methodArgs == null) {
            return null;
        }
        final Class<?>[] result = new Class[methodArgs.length];
        for (int i = 0; i < methodArgs.length; ++i) {
            if (methodArgs[i] != null) {
                result[i] = methodArgs[i].getClass();
            }
        }
        return result;
    }

}

