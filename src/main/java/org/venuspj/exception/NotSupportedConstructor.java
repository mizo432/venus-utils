package org.venuspj.exception;


/**
 * サポートされていないコンストラクターを呼び出した.
 * インスタンス化のプログラムミスの場合にthrowされます.
 */
public class NotSupportedConstructor extends RuntimeException {

    public NotSupportedConstructor(String message) {
        super(message);
    }

    public NotSupportedConstructor(String message, Throwable t) {
        super(message, t);
    }

    public NotSupportedConstructor(Throwable t) {
        super(t);
    }


}
