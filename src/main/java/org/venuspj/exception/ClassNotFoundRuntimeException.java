package org.venuspj.exception;


import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * クラスが見つからないときにスローされる例外です。
 */
public class ClassNotFoundRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = -9022468864937761059L;

    private final String className;

    /**
     * {@link ClassNotFoundRuntimeException}を作成します。
     *
     * @param cause 原因となった例外
     */
    public ClassNotFoundRuntimeException(final ClassNotFoundException cause) {
        this(null, cause);
    }

    /**
     * {@link ClassNotFoundRuntimeException}を作成します。
     *
     * @param className クラス名
     * @param cause     原因となった例外
     */
    public ClassNotFoundRuntimeException(final String className,
                                         final ClassNotFoundException cause) {
        super("EUTL0044", asArray(cause), cause);
        this.className = className;
    }

    /**
     * クラス名を返します。
     *
     * @return クラス名
     */
    public String getClassName() {
        return className;
    }

}
