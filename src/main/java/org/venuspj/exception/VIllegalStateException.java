package org.venuspj.exception;


/**
 * {@link IllegalStateException}をラップする例外です。
 */
public class VIllegalStateException extends IllegalStateException {

    private static final long serialVersionUID = -2154525994315946504L;

    /**
     * {@link VIllegalStateException}を作成します。
     */
    public VIllegalStateException() {
        super();
    }

    /**
     * {@link VIllegalStateException}を作成します。
     *
     * @param message メッセージ
     */
    public VIllegalStateException(final String message) {
        super(message);
    }

    /**
     * {@link VIllegalStateException}を作成します。
     *
     * @param message メッセージ
     * @param cause   元の例外
     */
    public VIllegalStateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * {@link VIllegalStateException}を作成します。
     *
     * @param cause 元の例外
     */
    public VIllegalStateException(final Throwable cause) {
        super(cause);
    }

}
