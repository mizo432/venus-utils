package org.venuspj.util.base;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import org.venuspj.util.precondition.MapPreconditions;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapPreconditionsTest {

  @Test
  public void testCheckSizeWithNegativeSize() {
    Map<String, String> map = new HashMap<>();
    assertThrows(IllegalArgumentException.class,
        () -> MapPreconditions.checkSize(map, -1, RuntimeException::new));
  }

  @Test
  public void testCheckSizeWithSizeGreaterThanMapSize() {
    Map<String, String> map = new HashMap<>();
    map.put("test", "value");
    assertThrows(RuntimeException.class,
        () -> MapPreconditions.checkSize(map, 2, RuntimeException::new));
  }

  @Test
  public void testCheckSizeWithSizeEqualToMapSize() {
    Map<String, String> map = new HashMap<>();
    map.put("test", "value");
    map.put("anotherTest", "anotherValue");
    assertThrows(RuntimeException.class,
        () -> MapPreconditions.checkSize(map, 2, RuntimeException::new));
  }

  @Test
  public void testCheckSizeWithSizeLessThanMapSize() {
    Map<String, String> map = new HashMap<>();
    map.put("test", "value");
    map.put("anotherTest", "anotherValue");
    MapPreconditions.checkSize(map, 1, RuntimeException::new);  // Doesn't throw
  }
}