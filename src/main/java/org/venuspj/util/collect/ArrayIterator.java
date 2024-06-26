package org.venuspj.util.collect;

import java.util.Iterator;
import org.venuspj.util.precondition.Preconditions;
import org.venuspj.util.exception.NullArgumentException;
import org.venuspj.util.exception.VNoSuchElementException;
import org.venuspj.util.exception.VUnsupportedOperationException;

/**
 * 配列を{@link Iterator}にするAdaptorです。
 * <p>
 * 次のように使います．
 * </p>
 *
 * <pre>
 * import static org.venuspj.util.collection.ArrayIterator.*;
 *
 * String[] array = ...;
 * for (String element : iterable(array)) {
 *     ...
 * }
 * </pre>
 *
 * @param <T> 配列の要素の型
 */
public class ArrayIterator<T> implements Iterator<T> {

  /**
   * イテレートする要素の配列
   */
  protected final T[] items;

  /**
   * 現在参照している要素のインデックス
   */
  protected int index = 0;

  /**
   * for each構文で使用するために配列をラップした{@link Iterable}を返します。
   *
   * @param <T> 列挙する要素の型
   * @param items イテレートする要素の並び。{@literal null}であってはいけません
   * @return 配列をラップした{@link Iterable}
   */
  @SafeVarargs
  public static <T> Iterable<T> iterable(final T... items) {
    Preconditions.checkNotNull(items, () -> new NullArgumentException("items"));

    return new Iterable<T>() {
      @Override
      public Iterator<T> iterator() {
        return new ArrayIterator<T>(items);
      }
    };
  }

  /**
   * {@link ArrayIterator}を作成します。
   *
   * @param items イテレートする要素の並び。{@literal null}であってはいけません
   */
  @SafeVarargs
  public ArrayIterator(final T... items) {
    this.items = items;

  }

  @Override
  public boolean hasNext() {
    return index < items.length;

  }

  @Override
  public T next() {
    try {
      final T o = items[index];
      index++;
      return o;
    } catch (final IndexOutOfBoundsException e) {
      throw new VNoSuchElementException("index=" + index);
    }
  }

  @Override
  public void remove() {
    throw new VUnsupportedOperationException("remove");

  }

}
