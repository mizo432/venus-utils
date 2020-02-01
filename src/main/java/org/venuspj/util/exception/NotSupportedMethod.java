package org.venuspj.util.exception;

/**
 * サポートされていないメソッドを呼び出した.
 * 使用すべきでないメソッドを使用した場合にthrowされます.
 */
public class NotSupportedMethod extends RuntimeException {

    public NotSupportedMethod(String message) {
        super(message);
    }

    public NotSupportedMethod(String message, Throwable t) {
        super(message, t);
    }

    public NotSupportedMethod(Throwable t) {
        super(t);
    }


}
