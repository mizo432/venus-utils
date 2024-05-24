package org.venuspj.util.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.venuspj.util.base.Function;
import org.venuspj.util.primitives.Ints;

/**
 * Sets2クラスは、HashSetオブジェクトを作成するためのユーティリティメソッドを提供します。
 */
public final class Sets2 {

  /**
   * 新しい、空のHashSetを作成します。
   *
   * @param <T> セット内の要素の型
   * @return 新しい、空のHashSet
   */
  public static <T> Set<T> newHashSet() {
    return new HashSet<>();

  }

  /**
   * 指定された初期容量で新しいHashSetを作成します。
   *
   * @param initialCapacity HashSetの初期容量
   * @param <T> セット内の要素の型
   * @return 指定された初期容量を持つ新しいHashSet
   */
  public static <T> Set<T> newHashSet(int initialCapacity) {
    return new HashSet<>(initialCapacity);
  }

  /**
   * 新しいHashSetを作成し、指定したオブジェクトを追加します。
   *
   * @param objects HashSetに追加するオブジェクト。
   * @param <T> オブジェクトの型。
   * @return 指定したオブジェクトを含む新しいHashSet。
   */
  @SafeVarargs
  public static <T> HashSet<T> newHashSet(T... objects) {
    HashSet<T> result = newHashSetWithExpectedSize(objects.length);
    result.addAll(Arrays.asList(objects));
    return result;
  }

  /**
   * 与えられたコレクションから要素を取り出して新たなHashSetを作成します。
   *
   * @param <P> HashSetの要素の型
   * @param collection HashSetに追加される要素があるコレクション
   * @return 与えられたコレクションから要素を追加した新しいHashSet
   */
  public static <P> HashSet<P> newHashSet(Collection<? extends P> collection) {
    HashSet<P> result = newHashSetWithExpectedSize(collection.size());
    if (collection.isEmpty()) {
      return result;
    }
    result.addAll(collection);
    return result;

  }

  /**
   * 与えられたコレクションから要素を抽出し、提供された関数を使用して新たなHashSetを作成します。
   *
   * @param <T> 入力コレクションの要素の型。
   * @param <P> 結果のHashSetの要素の型。
   * @param collection 要素を抽出するコレクション。
   * @param elementFunction コレクションの各要素に適用する関数。
   * @return コレクションの各要素にelementFunctionを適用する結果の要素を含む新たなHashSet。
   */
  public static <T, P> HashSet<P> newHashSet(Collection<? extends T> collection,
      Function<? super T, ? extends P> elementFunction) {
    HashSet<P> result = newHashSetWithExpectedSize(collection.size());

    if (collection.isEmpty()) {
      return result;
    }
    return newHashSet(collection.stream().map(elementFunction).toList());

  }

  /**
   * 指定された想定サイズで新しいHashSetを作成します。
   *
   * @param initialCapacity HashSetの想定サイズ
   * @param <E> セット内の要素の型
   * @return 指定された想定サイズの新しいHashSet
   */
  public static <E> HashSet<E> newHashSetWithExpectedSize(int initialCapacity) {
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

  /**
   * Computes the intersection of two collections: one represented as a Set and the other as a
   * List.
   *
   * @param <E> the type of elements in the collections
   * @param s1 the first collection represented as a Set
   * @param s2 the second collection represented as a List
   * @return a new HashSet containing the elements that are in both collections
   */
  public static <E> HashSet<E> intersection(@NotNull Set<E> s1,
      @NotNull Set<E> s2) {
    Collection<E> intersection = Collections3.intersection(s1, s2);
    return newHashSet(intersection);
  }

  /**
   * 2つのリストの差分を含む新しいArrayListを返す。
   *
   * @param <E> リストに含まれる要素の型
   * @param l1 最初のリスト
   * @param l2 二番目のリスト
   * @return 2つのリストの差分を含む新しいArrayList
   */
  public static <E> HashSet<E> difference(@NotNull List<E> l1,
      @NotNull List<E> l2) {
    Collection<E> difference = Collections3.getDifference(l1, l2);
    return newHashSet(difference);
  }

}
