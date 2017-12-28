package org.venuspj.util.collect;

import org.venuspj.util.base.Preconditions;
import org.venuspj.util.objects2.Objects2;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.venuspj.util.base.Preconditions.checkArgument;
import static org.venuspj.util.base.Preconditions.checkNotNull;

public final class Iterators {
    public static <T> boolean addAll(Collection<T> addTo, Iterator<? extends T> iterator) {
        checkNotNull(addTo);
        checkNotNull(iterator);
        boolean wasModified = false;
        while (iterator.hasNext()) {
            wasModified |= addTo.add(iterator.next());
        }
        return wasModified;
    }

    @SafeVarargs
    private static <T> Iterator<T> consumingForArray(final T... elements) {
        return new UnmodifiableIterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < elements.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = elements[index];
                elements[index] = null;
                index++;
                return result;
            }
        };
    }
    @SafeVarargs
    public static <T> UnmodifiableIterator<T> forArray(final T... array) {
        return forArray(array, 0, array.length, 0);
    }

    static <T> UnmodifiableListIterator<T> forArray(
            final T[] array, final int offset, int length, int index) {
        checkArgument(length >= 0);
        int end = offset + length;

        Preconditions.checkPositionIndexes(offset, end, array.length);
        Preconditions.checkPositionIndex(index, length);
        if (length == 0) {
            return emptyListIterator();
        }
        return new ArrayItr<T>(array, offset, length, index);
    }
    public static boolean elementsEqual(Iterator<?> iterator1, Iterator<?> iterator2) {
        while (iterator1.hasNext()) {
            if (!iterator2.hasNext()) {
                return false;
            }
            Object o1 = iterator1.next();
            Object o2 = iterator2.next();
            if (!Objects2.equal(o1, o2)) {
                return false;
            }
        }
        return !iterator2.hasNext();
    }

    @SuppressWarnings("unchecked")
    static <T> UnmodifiableListIterator<T> emptyListIterator() {
        return (UnmodifiableListIterator<T>) ArrayItr.EMPTY;
    }

    private static final class ArrayItr<T> extends AbstractIndexedListIterator<T> {
        static final UnmodifiableListIterator<Object> EMPTY = new ArrayItr<>(new Object[0], 0, 0, 0);

        private final T[] array;
        private final int offset;

        ArrayItr(T[] array, int offset, int length, int index) {
            super(length, index);
            this.array = array;
            this.offset = offset;
        }

        @Override
        protected T get(int index) {
            return array[offset + index];
        }
    }
}
