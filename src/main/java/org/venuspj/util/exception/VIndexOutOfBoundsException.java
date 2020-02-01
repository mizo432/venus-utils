package org.venuspj.util.exception;

/**
 * {@link IndexOutOfBoundsException}をラップする例外です。
 */
public class VIndexOutOfBoundsException extends IndexOutOfBoundsException {

    private static final long serialVersionUID = -824874776607593608L;

    /**
     * {@link VIndexOutOfBoundsException}を作成します。
     */
    public VIndexOutOfBoundsException() {
        super();
    }

    /**
     * {@link VIndexOutOfBoundsException}を作成します。
     *
     * @param message メッセージ
     */
    public VIndexOutOfBoundsException(final String message) {
        super(message);
    }

}
