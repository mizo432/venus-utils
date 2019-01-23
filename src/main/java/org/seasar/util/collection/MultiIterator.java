/*
 * Copyright 2004-2012 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.util.collection;

import org.venuspj.exception.VNoSuchElementException;
import org.venuspj.exception.VUnsupportedOperationException;

import java.util.Iterator;

import static org.seasar.util.misc.AssertionUtil.assertArgumentNotNull;

/**
 * 複数の{@link Iterator}を一つの{@link Iterator}のように反復するための{@link Iterator}です。
 * <p>
 * 次のように使います．
 * </p>
 * 
 * <pre>
 * import static org.seasar.util.collection.MultiIterator.*;
 * 
 * List<String> list = ...;
 * Set<String> set = ...;
 * Map<String, Object> map = ...;
 * for (String element : iterable(list, set, map.keySet())) {
 *     ...
 * }
 * </pre>
 * 
 * @author koichik
 * @param <E>
 *            要素の型
 */
public class MultiIterator<E> implements Iterator<E> {

    /** {@link Iterator}の配列 */
    protected final Iterator<E>[] iterators;

    /** 現在反復中の{@link Iterator}のインデックス */
    protected int index;

    /**
     * for each構文で使用するために{@link MultiIterator}をラップした{@link Iterable}を返します。
     * 
     * @param <E>
     *            要素の型
     * @param iterables
     *            {@link Iterable}の並び。{@literal null}であってはいけません
     * @return {@link MultiIterator}をラップした{@link Iterable}
     */
    @SafeVarargs
    public static <E> Iterable<E> iterable(final Iterable<E>... iterables) {
        assertArgumentNotNull("iterables", iterables);

        @SuppressWarnings("unchecked")
        final Iterator<E>[] iterators = new Iterator[iterables.length];
        for (int i = 0; i < iterables.length; ++i) {
            iterators[i] = iterables[i].iterator();
        }
        return iterable(iterators);
    }

    /**
     * for each構文で使用するために{@link MultiIterator}をラップした{@link Iterable}を返します。
     * 
     * @param <E>
     *            要素の型
     * @param iterators
     *            {@link Iterator}の並び。{@literal null}であってはいけません
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
     * @param iterators
     *            {@link Iterator}の並び。{@literal null}であってはいけません
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
