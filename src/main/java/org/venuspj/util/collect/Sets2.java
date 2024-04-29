package org.venuspj.util.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.venuspj.util.beans.BeanDesc;
import org.venuspj.util.beans.PropertyDesc;
import org.venuspj.util.beans.factory.BeanDescFactory;

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

  public static <T, P> Set<P> newHashSet(T[] objects, String aPropertyName) {
    Set<P> result = newHashSet();

    if (Arrays2.isEmpty(objects)) {
      return result;
    }
    return newHashSet(Arrays.asList(objects), aPropertyName);

  }

  public static <T, P> Set<P> newHashSet(Collection<T> objects, String aPropertyName) {
    Set<P> result = newHashSet();

    if (objects.isEmpty()) {
      return result;
    }

    BeanDesc beanDesc = BeanDescFactory.getBeanDesc(
        Collections3.firstItemOfIndex(objects).getClass());
    PropertyDesc propertyDesc = beanDesc.getPropertyDesc(aPropertyName);
    for (T object : objects) {
      result.add(propertyDesc.getValue(object));
    }
    return result;
  }

  /**
   * Creates a new HashSet with the expected size.
   *
   * @param expectedSize the expected size of the HashSet
   * @param <E> the type of elements in the set
   * @return a new HashSet with the expected size
   */
  public static <E> Set<E> newHashSetWithExpectedSize(int expectedSize) {
    return new HashSet<>(Maps2.capacity(expectedSize));
  }
}
