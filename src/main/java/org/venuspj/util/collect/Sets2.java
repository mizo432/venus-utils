package org.venuspj.util.collect;

import org.venuspj.util.beans.BeanDesc;
import org.venuspj.util.beans.PropertyDesc;
import org.venuspj.util.beans.factory.BeanDescFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class Sets2 {
    public static <T> Set<T> newHashSet() {
        return new HashSet<>();

    }

    @SafeVarargs
    public static <T> Set<T> newHashSet(T... objects) {
        Set<T> result = newHashSet();
        for (T object : objects) {
            result.add(object);
        }
        return result;
    }

    public static <T, P> Set<P> newHashSet(T[] objects, String aPropertyName) {
        Set<P> result = newHashSet();

        if (Arrays2.isEmpty(objects)) {
            return result;
        }
        return newHashSet(Arrays.asList(objects), aPropertyName);

    }

    public static <T, P> Set<P> newHashSet(Collection<T> objects, String aPropertyName) {
        Set<P> result = newHashSet();

        if (objects.isEmpty())
            return result;

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(Collections3.firstItemOfIndex(objects).getClass());
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc(aPropertyName);
        for (T object : objects) {
            result.add(propertyDesc.getValue(object));
        }
        return result;
    }

    /**
     * 新しいhash setを返却する.
     * <pre>
     *     初期サイズはexpectedSize
     * </pre>
     *
     * @param expectedSize the number of elements you expect to add to the returned set
     * @return a new, empty hash set with enough capacity to hold {@code expectedSize} elements
     * without resizing
     * @throws IllegalArgumentException if {@code expectedSize} is negative
     */
    public static <E> Set<E> newHashSetWithExpectedSize(int expectedSize) {
        return new HashSet<E>(Maps2.capacity(expectedSize));
    }
}
