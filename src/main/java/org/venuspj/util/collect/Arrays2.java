package org.venuspj.util.collect;

import org.venuspj.util.strings2.Strings2;

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
    public static <T> Arrays2 of(T... argValue) {
        return new Arrays2(Arrays.asList(argValue));
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * {@literal Object}の配列を返します。
     *
     * @param elements
     *            配列の要素
     * @return 配列
     */
    public static Object[] asArray(final Object... elements) {
        return elements;
    }
}
