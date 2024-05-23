package org.venuspj.util.collect;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.venuspj.util.primitives.Ints;

/**
 * Maps2クラスは、HashMapおよびLinkedHashMapの作成と操作のためのユーティリティメソッドを提供します。
 */
public final class Maps2 {

  public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
    return new HashMap<>(map);
  }

  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
    return new LinkedHashMap<>();
  }

  static int capacity(int expectedSize) {
    if (expectedSize < 3) {
      CollectPreconditions.checkNonNegative(expectedSize, "expectedSize");
      return expectedSize + 1;
    }
    if (expectedSize < Ints.MAX_POWER_OF_TWO) {
      return (int) ((float) expectedSize / 0.75F + 1.0F);
    }
    return Integer.MAX_VALUE; // any large value
  }

  public static <K, V> Map<K, V> newHashMap() {
    return new HashMap<K, V>();
  }

  public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
    return new HashMap<K, V>(capacity(expectedSize));
  }
}
