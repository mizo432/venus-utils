package org.venuspj.util.math;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.venuspj.util.annotations.GwtCompatible;

import java.math.BigInteger;

/**
 * A collection of preconditions for math functions.
 */
@GwtCompatible
@CanIgnoreReturnValue
final class MathPreconditions {
    static int checkPositive(@Nullable String role, int x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    static long checkPositive(@Nullable String role, long x) {
        if (x <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    static BigInteger checkPositive(@Nullable String role, BigInteger x) {
        if (x.signum() <= 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
        }
        return x;
    }

    static int checkNonNegative(@Nullable String role, int x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    static long checkNonNegative(@Nullable String role, long x) {
        if (x < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    static BigInteger checkNonNegative(@Nullable String role, BigInteger x) {
        if (x.signum() < 0) {
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    static double checkNonNegative(@Nullable String role, double x) {
        if (!(x >= 0)) { // not x < 0, to work with NaN.
            throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
        }
        return x;
    }

    static void checkRoundingUnnecessary(boolean condition) {
        if (!condition) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    static void checkInRange(boolean condition) {
        if (!condition) {
            throw new ArithmeticException("not in range");
        }
    }

    static void checkNoOverflow(boolean condition, String methodName, int a, int b) {
        if (!condition) {
            throw new ArithmeticException("overflow: " + methodName + "(" + a + ", " + b + ")");
        }
    }

    static void checkNoOverflow(boolean condition, String methodName, long a, long b) {
        if (!condition) {
            throw new ArithmeticException("overflow: " + methodName + "(" + a + ", " + b + ")");
        }
    }

    private MathPreconditions() {
    }
}
