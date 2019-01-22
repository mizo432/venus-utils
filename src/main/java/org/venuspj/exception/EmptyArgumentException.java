package org.venuspj.exception;

/**
 * 空の場合にスローされる例外です。
 *
 * @author higa
 */
public class EmptyArgumentException extends VIllegalArgumentException {


    /**
     * {@link EmptyArgumentException}を作成します。
     *
     * @param argName
     *            引数の名前
     * @param messageCode
     *            メッセージコード
     * @param args
     *            引数の配列
     */
    public EmptyArgumentException(final String argName,
                                  final String messageCode, final Object[] args) {
        this(argName, messageCode, args, null);
    }

    /**
     * {@link EmptyArgumentException}を作成します。
     *
     * @param argName
     *            引数の名前
     * @param messageCode
     *            メッセージコード
     * @param args
     *            引数の配列
     * @param cause
     *            原因となった例外
     */
    public EmptyArgumentException(final String argName,
                                  final String messageCode, final Object[] args, final Throwable cause) {
        super(argName, messageCode, args, cause);
    }

}
