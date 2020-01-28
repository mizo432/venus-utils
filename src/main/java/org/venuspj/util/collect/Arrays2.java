package org.venuspj.util.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.venuspj.util.collect.Lists2.newArrayList;

public class Arrays2<T> {
    private List<T> list = newArrayList();

    private Arrays2(Collection<T> argValue) {
        list.addAll(argValue);
    }

    @SafeVarargs
    public static <T> Arrays2 of(T... argValues) {
        Collection<T> collection = newArrayList();
        collection.addAll(Arrays.asList(argValues));

        return new Arrays2<T>(collection);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * {@literal Object}の配列を返します。
     *
     * @param elements 配列の要素
     * @return 配列
     */
    public static Object[] asArray(final Object... elements) {
        return elements;

    }

    @SuppressWarnings("unchecked")
    public static <T> T firstElement(T... elements) {
        if (isEmpty(elements))
            throw new IndexOutOfBoundsException();

        return elements[0];
    }

    @SuppressWarnings("unchecked")
    public static <T> boolean isEmpty(T... elements) {
        return elements.length == 0;

    }

}
