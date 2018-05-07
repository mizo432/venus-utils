package org.venuspj.util.collect;

import java.util.Collection;

import static org.venuspj.util.collect.Lists2.newArrayList;

public class Iterables {
    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection<?>) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> type) {
        return toArray(iterable, ObjectArrays.newArray(type, 0));
    }

    static <T> T[] toArray(Iterable<? extends T> iterable, T[] array) {
        Collection<? extends T> collection = castOrCopyToCollection(iterable);
        return collection.toArray(array);
    }

    static Object[] toArray(Iterable<?> iterable) {
        return castOrCopyToCollection(iterable).toArray();
    }
    private static <E> Collection<E> castOrCopyToCollection(Iterable<E> iterable) {
        return (iterable instanceof Collection)
                ? (Collection<E>) iterable
                : newArrayList(iterable.iterator());
    }}
