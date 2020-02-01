package org.venuspj.util.exception;

/**
 * {@link UnsupportedOperationException}をラップする例外です。
 */
public class VUnsupportedOperationException extends
        UnsupportedOperationException {

    private static final long serialVersionUID = -6732367317955522602L;

    /**
     * {@link VUnsupportedOperationException}を作成します。
     */
    public VUnsupportedOperationException() {
    }

    /**
     * {@link VUnsupportedOperationException}を作成します。
     *
     * @param message メッセージ
     */
    public VUnsupportedOperationException(final String message) {
        super(message);
    }

    /**
     * {@link VUnsupportedOperationException}を作成します。
     *
     * @param message メッセージ
     * @param cause   元の例外
     */
    public VUnsupportedOperationException(final String message,
                                          final Throwable cause) {
        super(message, cause);
    }

    /**
     * {@link VUnsupportedOperationException}を作成します。
     *
     * @param cause 元の例外
     */
    public VUnsupportedOperationException(final Throwable cause) {
        super(cause);
    }

}
