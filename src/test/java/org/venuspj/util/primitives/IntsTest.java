package org.venuspj.util.primitives;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Ints}. It tests the `hashCode` method only.
 */
public class IntsTest {

  /**
   * Test for the `hashCode` method - it's correctness for several values.
   */
  @Test
  public void testHashCode() {
    assertEquals(0, Ints.hashCode(0));
    assertEquals(1, Ints.hashCode(1));
    assertEquals(-1, Ints.hashCode(-1));
    assertEquals(Integer.MAX_VALUE, Ints.hashCode(Integer.MAX_VALUE));
    assertEquals(Integer.MIN_VALUE, Ints.hashCode(Integer.MIN_VALUE));
  }

  /**
   * Test for the `hashCode` method - it should return the same hash for the same numbers.
   */
  @Test
  public void testHashCodeConsistency() {
    assertEquals(Ints.hashCode(42), Ints.hashCode(42));
  }

  /**
   * Test for the `hashCode` method - it should return different hash for different numbers.
   */
  @Test
  public void testHashCodeDifference() {
    assertNotEquals(Ints.hashCode(41), Ints.hashCode(42));
  }

  /**
   * Test for the `checkedCast` method - it should throw an exception for values that can not be
   * cast to an integer.
   */
  @Test
  public void testCheckedCastOutOfBounds() {
    assertThrows(IllegalArgumentException.class, () -> Ints.checkedCast(Long.MAX_VALUE));
    assertThrows(IllegalArgumentException.class, () -> Ints.checkedCast(Long.MIN_VALUE));
  }

  /**
   * Test for the `saturatedCast` method - it should return the maximum integer when input is larger
   * than the maximum integer value.
   */
  @Test
  public void testSaturatedCastOverFlow() {
    assertEquals(Integer.MAX_VALUE, Ints.saturatedCast((long) Integer.MAX_VALUE + 1));
  }

  /**
   * Test for the `saturatedCast` method - it should return the minimum integer when input is
   * smaller than the minimum integer value.
   */
  @Test
  public void testSaturatedCastUnderFlow() {
    assertEquals(Integer.MIN_VALUE, Ints.saturatedCast((long) Integer.MIN_VALUE - 1));
  }

  /**
   * Test for the `saturatedCast` method - it should return the number itself when input is within
   * the range of integer values.
   */
  @Test
  public void testSaturatedCastWithinBounds() {
    for (int i = -10; i <= 10; i++) {
      assertEquals(i, Ints.saturatedCast((long) i));
    }
  }

  /**
   * Test for the `checkedCast` method - it should return the same value when it can be exactly
   * represented as an int.
   */
  @Test
  public void testCheckedCastExactValues() {
    assertEquals(0, Ints.checkedCast(0L));
    assertEquals(1, Ints.checkedCast(1L));
    assertEquals(-1, Ints.checkedCast(-1L));
    assertEquals(Integer.MAX_VALUE, Ints.checkedCast((long) Integer.MAX_VALUE));
    assertEquals(Integer.MIN_VALUE, Ints.checkedCast((long) Integer.MIN_VALUE));
  }
}
