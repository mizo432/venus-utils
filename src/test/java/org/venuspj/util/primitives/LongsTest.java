package org.venuspj.util.primitives;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * LongsTest is used to test methods of the Longs utility class. The main functionality tested in
 * this class is the indexOf method.
 */
public class LongsTest {

  /**
   * Test for the indexOf method of Longs with an array containing the target.
   */
  @Test
  public void testIndexOfPresent() {
    long[] array = new long[]{1, 2, 3, 4, 5};
    long target = 3;
    int expectedIndex = 2;

    assertEquals(expectedIndex, Longs.indexOf(array, target),
        "Index should match expected value when target is present.");
  }

  /**
   * Test for the indexOf method of Longs with an array not containing the target.
   */
  @Test
  public void testIndexOfAbsent() {
    long[] array = new long[]{1, 2, 3, 4, 5};
    long target = 6;
    int expectedIndex = -1;

    assertEquals(expectedIndex, Longs.indexOf(array, target),
        "Index should be -1 when target is absent.");
  }

  /**
   * Test for the indexOf method of Longs with an empty array.
   */
  @Test
  public void testIndexOfEmptyArray() {
    long[] array = new long[]{};
    long target = 1;
    int expectedIndex = -1;

    assertEquals(expectedIndex, Longs.indexOf(array, target),
        "Index should be -1 when array is empty.");
  }

  /**
   * Test for the indexOf method of Longs with a target that appears multiple times in array.
   */
  @Test
  public void testIndexOfMultipleAppearances() {
    long[] array = new long[]{1, 2, 3, 3, 3, 4, 5};
    long target = 3;
    int expectedIndex = 2;

    assertEquals(expectedIndex, Longs.indexOf(array, target),
        "Index should match the first occurrence when target appears multiple times.");
  }

} 