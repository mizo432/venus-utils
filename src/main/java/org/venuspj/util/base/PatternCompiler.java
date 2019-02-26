package org.venuspj.util.base;


import org.venuspj.util.annotations.GwtIncompatible;

/**
 * Pluggable interface for compiling a regex pattern. By default this package uses the {@code
 * java.util.regex} library, but an alternate implementation can be supplied using the {@link
 * java.util.ServiceLoader} mechanism.
 */
@GwtIncompatible
interface PatternCompiler {
    /**
     * Compiles the given pattern.
     *
     * @throws IllegalArgumentException if the pattern is invalid
     */
    CommonPattern compile(String pattern);

    /**
     * Returns {@code true} if the regex implementation behaves like Perl -- notably, by supporting
     * possessive quantifiers but also being susceptible to catastrophic backtracking.
     */
    boolean isPcreLike();
}
