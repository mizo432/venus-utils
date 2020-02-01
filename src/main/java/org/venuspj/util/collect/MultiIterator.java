package org.venuspj.util.collect;


import org.venuspj.util.exception.VNoSuchElementException;
import org.venuspj.util.exception.VUnsupportedOperationException;

import java.util.Iterator;

import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;


/**
 * 複数の{@link Iterator}を一つの{@link Iterator}のように反復するための{@link Iterator}です。
 * <p>
 * 次のように使います．
 * </p>
 *
 * <pre>
 * import static org.venuspj.util.collection.MultiIterator.*;
 *
 * List<String> list = ...;
 * Set<String> set = ...;
 * Map<String, Object> map = ...;
 * for (String element : iterable(list, set, map.keySet())) {
 *     ...
 * }
 * </pre>
 *
 * @param <E> 要素の型
 */
public class MultiIterator<E> implements Iterator<E> {

    /**
     * {@link Iterator}の配列
     */
    protected final Iterator<E>[] iterators;

    /**
     * 現在反復中の{@link Iterator}のインデックス
     */
    protected int index;

    /**
     * for each構文で使用するために{@link MultiIterator}をラップした{@link Iterable}を返します。
     *
     * @param <E>       要素の型
     * @param iterables {@link Iterable}の並び。{@literal null}であってはいけません
     * @return {@link MultiIterator}をラップした{@link Iterable}
     */
    @SafeVarargs
    public static <E> Iterable<E> iterable(final Iterable<E>... iterables) {
        assertArgumentNotNull("iterables", iterables);

        @SuppressWarnings("unchecked") final Iterator<E>[] iterators = new Iterator[iterables.length];
        for (int i = 0; i < iterables.length; ++i) {
            iterators[i] = iterables[i].iterator();
        }
        return iterable(iterators);
    }

    /**
     * for each構文で使用するために{@link MultiIterator}をラップした{@link Iterable}を返します。
     *
     * @param <E>       要素の型
     * @param iterators {@link Iterator}の並び。{@literal null}であってはいけません
     * @return {@link MultiIterator}をラップした{@link Iterable}
     */
    @SafeVarargs
    public static <E> Iterable<E> iterable(final Iterator<E>... iterators) {
        assertArgumentNotNull("iterators", iterators);

        return () -> new MultiIterator<>(iterators);

    }

    /**
     * インスタンスを構築します。
     *
     * @param iterators {@link Iterator}の並び。{@literal null}であってはいけません
     */
    @SafeVarargs
    public MultiIterator(final Iterator<E>... iterators) {
        assertArgumentNotNull("iterators", iterators);
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        for (; index < iterators.length; ++index) {
            if (iterators[index].hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new VNoSuchElementException();
        }
        return iterators[index].next();
    }

    @Override
    public void remove() {
        throw new VUnsupportedOperationException("remove");

    }

}
