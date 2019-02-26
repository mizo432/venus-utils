package org.venuspj.util.base;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.venuspj.util.annotations.GwtCompatible;

/**
 * Exception thrown upon the failure of a <a
 * href="https://github.com/google/guava/wiki/ConditionalFailuresExplained">verification check</a>,
 * including those performed by the convenience methods of the {@link Verify} class.
 *
 * @since 17.0
 */
@GwtCompatible
public class VerifyException extends RuntimeException {
    /**
     * Constructs a {@code VerifyException} with no message.
     */
    public VerifyException() {
    }

    /**
     * Constructs a {@code VerifyException} with the message {@code message}.
     */
    public VerifyException(@Nullable String message) {
        super(message);
    }

    /**
     * Constructs a {@code VerifyException} with the cause {@code cause} and a message that is {@code
     * null} if {@code cause} is null, and {@code cause.toString()} otherwise.
     *
     * @since 19.0
     */
    public VerifyException(@Nullable Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code VerifyException} with the message {@code message} and the cause {@code
     * cause}.
     *
     * @since 19.0
     */
    public VerifyException(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}
