package org.venuspj.util.base;

import org.venuspj.util.annotations.GwtCompatible;

/**
 * Legacy version of {@link java.util.function.Function java.util.function.Function}.
 *
 * <p>The {@link Functions} class provides common functions and related utilities.
 *
 * <p>As this interface extends {@code java.util.function.Function}, an instance of this type can be
 * used as a {@code java.util.function.Function} directly. To use a {@code
 * java.util.function.Function} in a context where a {@code com.google.common.base.Function} is
 * needed, use {@code function::apply}.
 *
 * <p>This interface is now a legacy type. Use {@code java.util.function.Function} (or the
 * appropriate primitive specialization such as {@code ToIntFunction}) instead whenever possible.
 * Otherwise, at least reduce <i>explicit</i> dependencies on this type by using lambda expressions
 * or method references instead of classes, leaving your code easier to migrate in the future.
 *
 * <p>See the Guava User Guide article on <a
 * href="https://github.com/google/guava/wiki/FunctionalExplained">the use of {@code Function}</a>.
 */
@GwtCompatible
@FunctionalInterface
public interface Function<F, T> extends java.util.function.Function<F, T> {
    @Override
    T apply(F input);

    /**
     * <i>May</i> return {@code true} if {@code object} is a {@code Function} that behaves identically
     * to this function.
     *
     * <p><b>Warning: do not depend</b> on the behavior of this method.
     *
     * <p>Historically, {@code Function} instances in this library have implemented this method to
     * recognize certain cases where distinct {@code Function} instances would in fact behave
     * identically. However, as code migrates to {@code java.util.function}, that behavior will
     * disappear. It is best not to depend on it.
     */
    @Override
    boolean equals(Object object);
}
