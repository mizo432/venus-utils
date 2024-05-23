package org.venuspj.util.collect;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.venuspj.util.primitives.Ints;

class Sets2Test {

  private Set<String> testSet;

  @Test
  void newHashSetWithExpectedSize_smallExpectedSize() {
    // Arrange
    int expectedSize = 5;

    // Act
    testSet = Sets2.newHashSetWithExpectedSize(expectedSize);

    // Assert
    assertThat(testSet).isEqualTo(new HashSet<>());
  }

  @Test
  void newHashSetWithExpectedSize_largeExpectedSize() {
    // Arrange
    int expectedSize = 1000;

    // Act
    testSet = Sets2.newHashSetWithExpectedSize(expectedSize);

    // Assert
    assertThat(testSet).isEqualTo(new HashSet<>());
  }

  @Test
  void newHashSet_emptySet() {
    // Act
    Set<String> testSet = Sets2.newHashSet();

    // Assert
    assertThat(testSet.isEmpty()).isTrue();
  }

  @Test
  void newHashSet_initialCapacity() {
    // Arrange
    int initialCapacity = 5;

    // Act
    Set<String> testSet = Sets2.newHashSet(initialCapacity);

    // Assert
    assertThat(testSet.isEmpty()).isTrue();
  }

  @Test
  void newHashSet_elemsInSet() {
    // Arrange
    String[] arr = {"a", "b", "c"};

    // Act
    Set<String> testSet = Sets2.newHashSet(arr);

    // Assert
    assertThat(testSet.size()).isEqualTo(arr.length);
  }

  @Test
  void newHashSetWithExpectedSize_capacityLessThanThree() {
    // Arrange
    int expectedSize = 2;

    // Act
    testSet = Sets2.newHashSetWithExpectedSize(expectedSize);

    // Assert
    assertThat(testSet).isEqualTo(new HashSet<>());
    assertThat(Sets2.capacity(expectedSize)).isEqualTo(expectedSize + 1);
  }

  @Test
  void newHashSetWithExpectedSize_capacityLessThanMaxPowerOfTwo() {
    // Arrange
    int expectedSize = 4;

    // Act
    testSet = Sets2.newHashSetWithExpectedSize(expectedSize);

    // Assert
    assertThat(testSet).isEqualTo(new HashSet<>());
    assertThat(Sets2.capacity(expectedSize)).isEqualTo((int) ((float) expectedSize / 0.75F + 1.0F));
  }

  @Test
  void newHashSetWithExpectedSize_capacityGreaterThanMaxPowerOfTwo() {
    // Arrange
    int expectedSize = Ints.MAX_POWER_OF_TWO + 1;

    // Act
    testSet = Sets2.newHashSetWithExpectedSize(expectedSize);

    // Assert
    assertThat(testSet).isEqualTo(new HashSet<>());
    assertThat(Sets2.capacity(expectedSize)).isEqualTo(Integer.MAX_VALUE);
  }

  @Test
  void capacity_ExpectedSizeLessThanThree() {
    // Arrange
    int expectedSize = 2;

    // Act
    int result = Sets2.capacity(expectedSize);

    // Assert
    assertThat(result).isEqualTo(expectedSize + 1);
  }

  @Test
  void capacity_ExpectedSizeLessThanMaxPowerOfTwo() {
    // Arrange
    int expectedSize = 4;

    // Act
    int result = Sets2.capacity(expectedSize);

    // Assert
    assertThat(result).isEqualTo((int) ((float) expectedSize / 0.75F + 1.0F));
  }

  @Test
  void capacity_ExpectedSizeGreaterThanMaxPowerOfTwo() {
    // Arrange
    int expectedSize = Ints.MAX_POWER_OF_TWO + 1;

    // Act
    int result = Sets2.capacity(expectedSize);

    // Assert
    assertThat(result).isEqualTo(Integer.MAX_VALUE);
  }
}
