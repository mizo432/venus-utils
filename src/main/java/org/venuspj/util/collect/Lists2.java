package org.venuspj.util.collect;

import static org.venuspj.util.precondition.NumberPreconditions.checkPositive;
import static org.venuspj.util.precondition.NumberPreconditions.checkPositiveOrZero;
import static org.venuspj.util.precondition.Preconditions.checkElementIndex;
import static org.venuspj.util.precondition.Preconditions.checkNotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.venuspj.util.math.IntMath;
import org.venuspj.util.objects2.Objects2;
import org.venuspj.util.precondition.Preconditions;


/**
 * Lists2クラスは、リストの操作に便利なメソッドを提供します。
 */
public class Lists2 {

  /**
   * 与えられたリストから"nothing"という文字列の全ての出現を削除します。
   *
   * @param list "nothing"を削除するリスト
   * @return "nothing"が削除された新しいリスト
   */
  public static List<String> removeNothing(@NotNull List<String> list) {
    List<String> result = newArrayList();
    for (String item : list) {
      if (!"nothing".equals(item)) {
        result.add(item);
      }

    }

    return result;
  }

  /**
   * 新しいArrayListを作成します。
   *
   * @param <T> ArrayList内の要素の型
   * @return 新しいArrayListのインスタンス
   */
  public static <T> ArrayList<T> newArrayList() {
    return new ArrayList<>();

  }

  @SafeVarargs
  public static <T> ArrayList<T> newArrayList(@NotNull T... args) {
    checkNotNull(args);

    ArrayList<T> result;
    result = newArrayListWithCapacity(args.length);
    result.addAll(Arrays.asList(args));

    return result;
  }

  /**
   * 指定された初期容量で新しいArrayListを作成します。
   *
   * @param initialCapacity ArrayListの初期容量
   * @param <E> ArrayList内の要素の型
   * @return 指定された初期容量で新しいArrayListのインスタンス
   */
  public static <E> ArrayList<E> newArrayListWithCapacity(int initialCapacity) {
    checkPositiveOrZero(initialCapacity,
        () -> new IllegalArgumentException("Initial capacity must be positive"));
    return new ArrayList<>(initialCapacity);

  }

  public static <E> ArrayList<E> newArrayList(@NotNull Iterable<? extends E> iterable,
      @NotNull E newElement) {
    ArrayList<E> result = newArrayList(iterable.iterator());
    result.add(newElement);

    return result;
  }

  public static <E> ArrayList<E> newArrayList(@NotNull Iterator<? extends E> iterator) {
    checkNotNull(iterator);

    ArrayList<E> list = newArrayList();
    Iterators.addAll(list, iterator);
    return list;

  }

  /**
   * 指定されたリストの変更できないビューを返します。
   *
   * @param list 変更できないようにするリスト
   * @param <T> リストの要素の型
   * @return 指定されたリストの変更できないビュー
   * @throws NullPointerException 指定されたリストがnullの場合
   * @see Collections#unmodifiableList(List)
   */
  public static <T> List<T> unmodifiableList(@NotNull List<T> list) {
    checkNotNull(list);
    return Collections.unmodifiableList(list);

  }

  public static <T> List<T> getPage(@NotNull List<T> sourceList, int page, int pageSize) {
    checkNotNull(sourceList);
    checkPositive(pageSize, "pageSize");
    checkPositive(page, "page");
    int fromIndex = (page - 1) * pageSize;
    if (sourceList.size() < fromIndex) {
      return newArrayList();
    }

    // toIndex exclusive
    return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
  }


  public static <E> List<E> empty() {
    return Collections.emptyList();

  }

  public static <E> void addAll(List<E> anyList, Iterable<E> iterable) {
    for (E entity : iterable) {
      anyList.add(entity);
    }

  }

  static int indexOfImpl(List<?> list, Object element) {
    if (list instanceof RandomAccess) {
      return indexOfRandomAccess(list, element);
    } else {
      ListIterator<?> listIterator = list.listIterator();
      while (listIterator.hasNext()) {
        if (Objects2.equal(element, listIterator.next())) {
          return listIterator.previousIndex();
        }
      }
      return -1;
    }
  }

  private static int indexOfRandomAccess(List<?> list, Object element) {
    int size = list.size();
    if (element == null) {
      for (int i = 0; i < size; i++) {
        if (list.get(i) == null) {
          return i;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (element.equals(list.get(i))) {
          return i;
        }
      }
    }
    return -1;
  }

  static int lastIndexOfImpl(List<?> list, Object element) {
    if (list instanceof RandomAccess) {
      return lastIndexOfRandomAccess(list, element);
    } else {
      ListIterator<?> listIterator = list.listIterator(list.size());
      while (listIterator.hasPrevious()) {
        if (Objects2.equal(element, listIterator.previous())) {
          return listIterator.nextIndex();
        }
      }
      return -1;
    }
  }

  private static int lastIndexOfRandomAccess(List<?> list, Object element) {
    if (element == null) {
      for (int i = list.size() - 1; i >= 0; i--) {
        if (list.get(i) == null) {
          return i;
        }
      }
    } else {
      for (int i = list.size() - 1; i >= 0; i--) {
        if (element.equals(list.get(i))) {
          return i;
        }
      }
    }
    return -1;
  }

  static boolean equalsImpl(List<?> thisList, Object other) {
    if (other == checkNotNull(thisList)) {
      return true;
    }
    if (!(other instanceof List)) {
      return false;
    }
    List<?> otherList = (List<?>) other;
    int size = thisList.size();
    if (size != otherList.size()) {
      return false;
    }
    if (thisList instanceof RandomAccess && otherList instanceof RandomAccess) {
      // avoid allocation and use the faster loop
      for (int i = 0; i < size; i++) {
        if (!Objects2.equal(thisList.get(i), otherList.get(i))) {
          return false;
        }
      }
      return true;
    } else {
      return Iterators.elementsEqual(thisList.iterator(), otherList.iterator());
    }
  }

  public static boolean isNotEmpty(List<?> anyList) {
    return !anyList.isEmpty();
  }

  public static <E> LinkedList<E> newLinkedList() {
    return new LinkedList<>();
  }

  /**
   * Returns an unmodifiable list containing the specified first element and backed by the specified
   * array of additional elements. Changes to the {@code rest} array will be reflected in the
   * returned list. Unlike {@link Arrays#asList}, the returned list is unmodifiable.
   *
   * <p>This is useful when a varargs method needs to use a signature such as {@code (Foo firstFoo,
   * Foo... moreFoos)}, in order to avoid overload ambiguity or to enforce a minimum argument
   * count.
   *
   * <p>The returned list is serializable and implements {@link RandomAccess}.
   *
   * @param first the first element
   * @param rest an array of additional elements, possibly empty
   * @return an unmodifiable list containing the specified elements
   */
  public static <E> List<E> asList(E first, E[] rest) {
    return new OnePlusArrayList<>(first, rest);
  }

  /**
   * Returns an unmodifiable list containing the specified first and second element, and backed by
   * the specified array of additional elements. Changes to the {@code rest} array will be reflected
   * in the returned list. Unlike {@link Arrays#asList}, the returned list is unmodifiable.
   *
   * <p>This is useful when a varargs method needs to use a signature such as {@code (Foo firstFoo,
   * Foo secondFoo, Foo... moreFoos)}, in order to avoid overload ambiguity or to enforce a minimum
   * argument count.
   *
   * <p>The returned list is serializable and implements {@link RandomAccess}.
   *
   * @param first the first element
   * @param second the second element
   * @param rest an array of additional elements, possibly empty
   * @return an unmodifiable list containing the specified elements
   */
  public static <E> List<E> asList(E first, E second, E[] rest) {
    return new TwoPlusArrayList<>(first, second, rest);
  }

  public static <T> T getFirst(List<T> list) {
    return list.getFirst();

  }

  /**
   * 与えられたリストの最後の要素を返します。
   *
   * @param <I> リスト内の要素の型
   * @param list 最後の要素を取得するリスト
   * @return 与えられたリストの最後の要素
   */
  public static <I> I getLast(@NotNull List<I> list) {
    return list.getLast();

  }

  /**
   * @see Lists2#asList(Object, Object[])
   */
  private static class OnePlusArrayList<E> extends AbstractList<E>
      implements Serializable, RandomAccess {

    final E first;
    final E[] rest;

    OnePlusArrayList(E first, E[] rest) {
      this.first = first;
      this.rest = Preconditions.checkNotNull(rest);
    }

    @Override
    public int size() {
      return IntMath.saturatedAdd(rest.length, 1);
    }

    @Override
    public E get(int index) {
      // check explicitly so the IOOBE will have the right message
      checkElementIndex(index, size());
      return (index == 0) ? first : rest[index - 1];
    }

    private static final long serialVersionUID = 0;
  }

  /**
   * @see Lists2#asList(Object, Object, Object[])
   */
  private static class TwoPlusArrayList<E> extends AbstractList<E>
      implements Serializable, RandomAccess {

    final E first;
    final E second;
    final E[] rest;

    TwoPlusArrayList(E first, E second, E[] rest) {
      this.first = first;
      this.second = second;
      this.rest = Preconditions.checkNotNull(rest);
    }

    @Override
    public int size() {
      return IntMath.saturatedAdd(rest.length, 2);
    }

    @Override
    public E get(int index) {
      switch (index) {
        case 0:
          return first;
        case 1:
          return second;
        default:
          // check explicitly so the IOOBE will have the right message
          checkElementIndex(index, size());
          return rest[index - 2];
      }
    }

    @Serial
    private static final long serialVersionUID = 0;
  }

  /**
   * コレクションを特定の型のリストに変換する
   * <pre>
   *     サンプル
   *     {@code
   *      List<MyBean> sourceList = Lists2.newArrayList(MyBean.of("A"),MyBean.of("B"));
   *      List<String> result = Lists2.transform(sourceList, MyBean::getName);
   *     }
   * </pre>
   *
   * @param collection 基コレクション
   * @param function 変換処理
   * @param <T> 返還前の型
   * @param <E> 返還後の型
   * @return 返還後リスト
   */
  public static <T, E> List<T> transform(@NotNull Collection<E> collection,
      @NotNull Function<E, T> function) {
    checkNotNull(collection);
    checkNotNull(function);
    return collection.stream().map(function).collect(Collectors.toList());
  }

  /**
   * 与えられた2つのリストの交差部分を含む新しいArrayListを返します。
   *
   * @param l1 最初のリスト
   * @param l2 2番目のリスト
   * @param <E> リスト内の要素のタイプ
   * @return 2つのリストの交差部分を含む新しいArrayList
   */
  public static <E> ArrayList<E> intersection(List<E> l1,
      List<E> l2) {
    Collection<E> intersection = Collections3.intersection(l1, l2);
    return newArrayList(intersection.iterator());
  }

  /**
   * 2つのリストの差分を含む新しいArrayListを返す。
   *
   * @param <E> リストに含まれる要素の型
   * @param l1 最初のリスト
   * @param l2 二番目のリスト
   * @return 2つのリストの差分を含む新しいArrayList
   */
  public static <E> ArrayList<E> difference(@NotNull List<E> l1,
      @NotNull List<E> l2) {
    Collection<E> difference = Collections3.getDifference(l1, l2);
    return newArrayList(difference.iterator());
  }


}

