package org.venuspj.util.primitives;

import static java.lang.Double.MAX_EXPONENT;
import static java.lang.Double.MIN_EXPONENT;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Double.doubleToRawLongBits;
import static java.lang.Double.isNaN;
import static java.lang.Double.longBitsToDouble;
import static java.lang.Math.getExponent;
import static org.venuspj.util.precondition.Preconditions.checkArgument;

import java.math.BigInteger;
import org.jetbrains.annotations.VisibleForTesting;

/**
 * Utilities for {@code double} primitives.
 */
public final class Doubles {

  private Doubles() {
  }

  static double nextDown(double d) {
    return -Math.nextUp(-d);
  }

  // The mask for the significand, according to the {@link
  // Double#doubleToRawLongBits(double)} spec.
  static final long SIGNIFICAND_MASK = 0x000fffffffffffffL;

  // The mask for the exponent, according to the {@link
  // Double#doubleToRawLongBits(double)} spec.
  static final long EXPONENT_MASK = 0x7ff0000000000000L;

  // The mask for the sign, according to the {@link
  // Double#doubleToRawLongBits(double)} spec.
  static final long SIGN_MASK = 0x8000000000000000L;

  public static final int SIGNIFICAND_BITS = 52;

  static final int EXPONENT_BIAS = 1023;

  /**
   * The implicit 1 bit that is omitted in significands of normal doubles.
   */
  public static final long IMPLICIT_BIT = SIGNIFICAND_MASK + 1;

  public static long getSignificand(double d) {
    checkArgument(isFinite(d), "not a normal value");
    int exponent = getExponent(d);
    long bits = doubleToRawLongBits(d);
    bits &= SIGNIFICAND_MASK;
    return (exponent == MIN_EXPONENT - 1) ? bits << 1 : bits | IMPLICIT_BIT;
  }

  public static boolean isFinite(double d) {
    return getExponent(d) <= MAX_EXPONENT;
  }

  public static boolean isNormal(double d) {
    return getExponent(d) >= MIN_EXPONENT;
  }

  /*
   * Returns x scaled by a power of 2 such that it is in the range [1, 2). Assumes x is positive,
   * normal, and finite.
   */
  public static double scaleNormalize(double x) {
    long significand = doubleToRawLongBits(x) & SIGNIFICAND_MASK;
    return longBitsToDouble(significand | ONE_BITS);
  }

  public static double bigToDouble(BigInteger x) {
    // This is an extremely fast implementation of BigInteger.doubleValue(). JDK patch pending.
    BigInteger absX = x.abs();
    int exponent = absX.bitLength() - 1;
    // exponent == floor(log2(abs(x)))
    if (exponent < Long.SIZE - 1) {
      return x.longValue();
    } else if (exponent > MAX_EXPONENT) {
      return x.signum() * POSITIVE_INFINITY;
    }

    /*
     * We need the top SIGNIFICAND_BITS + 1 bits, including the "implicit" one bit. To make rounding
     * easier, we pick out the top SIGNIFICAND_BITS + 2 bits, so we have one to help us round up or
     * down. twiceSignifFloor will contain the top SIGNIFICAND_BITS + 2 bits, and signifFloor the
     * top SIGNIFICAND_BITS + 1.
     *
     * It helps to consider the real number signif = absX * 2^(SIGNIFICAND_BITS - exponent).
     */
    int shift = exponent - SIGNIFICAND_BITS - 1;
    long twiceSignifFloor = absX.shiftRight(shift).longValue();
    long signifFloor = twiceSignifFloor >> 1;
    signifFloor &= SIGNIFICAND_MASK; // remove the implied bit

    /*
     * We round up if either the fractional part of signif is strictly greater than 0.5 (which is
     * true if the 0.5 bit is set and any lower bit is set), or if the fractional part of signif is
     * >= 0.5 and signifFloor is odd (which is true if both the 0.5 bit and the 1 bit are set).
     */
    boolean increment =
        (twiceSignifFloor & 1) != 0 && ((signifFloor & 1) != 0 || absX.getLowestSetBit() < shift);
    long signifRounded = increment ? signifFloor + 1 : signifFloor;
    long bits = (long) (exponent + EXPONENT_BIAS) << SIGNIFICAND_BITS;
    bits += signifRounded;
    /*
     * If signifRounded == 2^53, we'd need to set all of the significand bits to zero and add 1 to
     * the exponent. This is exactly the behavior we get from just adding signifRounded to bits
     * directly. If the exponent is MAX_DOUBLE_EXPONENT, we round up (correctly) to
     * Double.POSITIVE_INFINITY.
     */
    bits |= x.signum() & SIGN_MASK;
    return longBitsToDouble(bits);
  }

  /**
   * Returns its argument if it is non-negative, zero if it is negative.
   */
  public static double ensureNonNegative(double value) {
    checkArgument(!isNaN(value));
    if (value > 0.0) {
      return value;
    } else {
      return 0.0;
    }
  }

  @VisibleForTesting
  static final long ONE_BITS = 0x3ff0000000000000L;
}
