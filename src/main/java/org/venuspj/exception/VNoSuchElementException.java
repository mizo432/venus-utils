package org.venuspj.exception;

import java.util.NoSuchElementException;

/**
 * {@link NoSuchElementException}をラップする例外です。
 */
public class VNoSuchElementException extends NoSuchElementException {

    private static final long serialVersionUID = 1632854460852262479L;

    /**
     * {@link VNoSuchElementException}を作成します。
     */
    public VNoSuchElementException() {
        super();
    }

    /**
     * {@link VNoSuchElementException}を作成します。
     *
     * @param message メッセージ
     */
    public VNoSuchElementException(final String message) {
        super(message);
    }

}
