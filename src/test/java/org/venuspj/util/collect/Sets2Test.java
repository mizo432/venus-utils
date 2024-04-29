package org.venuspj.util.collect;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Sets2Test {

  private Set<String> testSet;

  @Test
  void newHashSetWithExpectedSize_smallExpectedSize() {
    // Arrange
    int expectedSize = 5;

    // Act
    testSet = Sets2.newHashSetWithExpectedSize(expectedSize);

    // Assert
    Assertions.assertEquals(new HashSet<>(), testSet);
  }

  @Test
  void newHashSetWithExpectedSize_largeExpectedSize() {
    // Arrange
    int expectedSize = 1000;

    // Act
    testSet = Sets2.newHashSetWithExpectedSize(expectedSize);

    // Assert
    Assertions.assertEquals(new HashSet<>(), testSet);
  }

  @Test
  void newHashSet_emptySet() {
    // Act
    Set<String> testSet = Sets2.newHashSet();

    // Assert
    Assertions.assertTrue(testSet.isEmpty());
  }

  @Test
  void newHashSet_initialCapacity() {
    // Arrange
    int initialCapacity = 5;

    // Act
    Set<String> testSet = Sets2.newHashSet(initialCapacity);

    // Assert
    Assertions.assertTrue(testSet.isEmpty());
  }

  @Test
  void newHashSet_elemsInSet() {
    // Arrange
    String[] arr = {"a", "b", "c"};

    // Act
    Set<String> testSet = Sets2.newHashSet(arr);

    // Assert
    Assertions.assertEquals(arr.length, testSet.size());
  }
  
}
