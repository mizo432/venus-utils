package org.venuspj.util.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 */
public final class Collections3 {

    private Collections3() {

    }

    public static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection<T>) iterable;
    }

    public static <T> List<T> sort(Collection<T> anyCollection, Comparator<T> comparator) {
        List<T> resultList = Lists2.newArrayList();
        resultList.addAll(anyCollection);
        Collections.sort(resultList, comparator);
        return resultList;
    }

}
