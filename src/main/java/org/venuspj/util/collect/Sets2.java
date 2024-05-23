package org.venuspj.util.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.venuspj.util.beans.BeanDesc;
import org.venuspj.util.beans.PropertyDesc;
import org.venuspj.util.beans.factory.BeanDescFactory;
import org.venuspj.util.primitives.Ints;

/**
 * The Sets2 class provides utility methods for creating HashSet objects.
 */
public final class Sets2 {

  /**
   * Creates a new, empty HashSet.
   *
   * @param <T> the type of elements in the set
   * @return a new, empty HashSet
   */
  public static <T> Set<T> newHashSet() {
    return new HashSet<>();

  }

  /**
   * Creates a new HashSet with the specified initial capacity.
   *
   * @param initialCapacity the initial capacity of the HashSet
   * @param <T> the type of elements in the set
   * @return a new HashSet with the specified initial capacity
   */
  public static <T> Set<T> newHashSet(int initialCapacity) {
    return new HashSet<>(initialCapacity);
  }

  @SafeVarargs
  public static <T> Set<T> newHashSet(T... objects) {
    Set<T> result = newHashSet(objects.length);
    result.addAll(Arrays.asList(objects));
    return result;
  }

  public static <T, P> Set<P> newHashSet(T[] objects, String propertyName) {
    Set<P> result = newHashSet();

    if (Arrays2.isEmpty(objects)) {
      return result;
    }
    return newHashSet(Arrays.asList(objects), propertyName);

  }

  /**
   * オブジェクトのコレクションから特定のプロパティ値を含む新しいSetを作成します。
   *
   * @param objects オブジェクトのコレクション
   * @param propertyName 値を抽出するプロパティの名前
   * @param <T> コレクション内のオブジェクトの型
   * @param <P> Setに配置されるプロパティ値の型
   * @return 特定のプロパティ値を含む新しいSet
   */
  public static <T, P> Set<P> newHashSet(Collection<T> objects, String propertyName) {
    Set<P> result = newHashSet();

    if (objects.isEmpty()) {
      return result;
    }

    BeanDesc beanDesc = BeanDescFactory.getBeanDesc(
        Collections3.firstItemOfIndex(objects).getClass());
    PropertyDesc propertyDesc = beanDesc.getPropertyDesc(propertyName);
    for (T object : objects) {
      result.add(propertyDesc.getValue(object));
    }
    return result;
  }

  /**
   * 指定された想定サイズで新しいHashSetを作成します。
   *
   * @param initialCapacity HashSetの想定サイズ
   * @param <E> セット内の要素の型
   * @return 指定された想定サイズの新しいHashSet
   */
  public static <E> Set<E> newHashSetWithExpectedSize(int initialCapacity) {
    return new HashSet<>(capacity(initialCapacity));
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

}
