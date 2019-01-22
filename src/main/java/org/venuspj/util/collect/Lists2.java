package org.venuspj.util.collect;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.venuspj.util.objects2.Objects2;

import java.io.Serializable;
import java.util.*;

import static com.google.common.base.Preconditions.checkElementIndex;
import static org.venuspj.util.base.Preconditions.checkNotNull;


/**
 */
public class Lists2 {

    public static List<String> removeNothing(List<String> aList) {
        List<String> result = newArrayList();
        for (String item : aList) {
            if (!"nothing".equals(item)) {
                result.add(item);
            }

        }

        return result;
    }

    public static <T> ArrayList<T> newArrayList() {
        ArrayList<T> result = new ArrayList<>();
        return result;
    }

    @SafeVarargs
    public static <T> List<T> newArrayList(T... args) {
        List<T> result = new ArrayList<>();
        for (T arg : args)
            result.add(arg);
        return result;
    }

    public static <E> List<E> newArrayListWithCapacity(int initialArraySize) {
        return new ArrayList<E>(initialArraySize);
    }

    public static <E> List<E> newArrayList(Iterable<? extends E> elements) {
        checkNotNull(elements);
        return (elements instanceof Collection)
                ? new ArrayList<E>(Collections3.cast(elements))
                : newArrayList(elements.iterator());
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> list = newArrayList();
        Iterators.addAll(list, elements);
        return list;
    }

    public static <T> List<T> unmodifiableList(List<T> list) {
        return Collections.unmodifiableList(list);
    }

    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException(String.format("invalid page:%s page size:%s", pageSize, page));
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() < fromIndex) {
            return newArrayList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }


    public static <E> List<E> empty() {
        return Collections.emptyList();
    }

    public static <E> void addAll(List<E> anyList, Iterable<E> iterable) {
        for (E entity : iterable)
            anyList.add(entity);
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
     * Foo... moreFoos)}, in order to avoid overload ambiguity or to enforce a minimum argument count.
     *
     * <p>The returned list is serializable and implements {@link RandomAccess}.
     *
     * @param first the first element
     * @param rest an array of additional elements, possibly empty
     * @return an unmodifiable list containing the specified elements
     */
    public static <E> List<E> asList(@Nullable E first, E[] rest) {
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
    public static <E> List<E> asList(@Nullable E first, @Nullable E second, E[] rest) {
        return new TwoPlusArrayList<>(first, second, rest);
    }

    /** @see Lists2#asList(Object, Object[]) */
    private static class OnePlusArrayList<E> extends AbstractList<E>
            implements Serializable, RandomAccess {
        final @Nullable E first;
        final E[] rest;

        OnePlusArrayList(@Nullable E first, E[] rest) {
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

    /** @see Lists2#asList(Object, Object, Object[]) */
    private static class TwoPlusArrayList<E> extends AbstractList<E>
            implements Serializable, RandomAccess {
        final @Nullable E first;
        final @Nullable E second;
        final E[] rest;

        TwoPlusArrayList(@Nullable E first, @Nullable E second, E[] rest) {
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

        private static final long serialVersionUID = 0;
    }

}

