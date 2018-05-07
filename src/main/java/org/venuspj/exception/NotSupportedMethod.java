package org.venuspj.exception;

/**
 * サポートされていないメソッドを呼び出した.
 * しようすべきでないメソッドをしようした場合にthrowされます.
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
