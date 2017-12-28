package org.venuspj.util.collect;

import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class Collections3Test {

    @Test
    public void cast() throws Exception {
        List<Integer> arg = Lists2.newArrayList();
        Collection<Integer> actual = Collections3.cast(arg);
        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isTrue();

    }

    @Test
    public void sort() throws Exception {
        List<Integer> arg = Lists2.newArrayListWithCapacity(3);
        arg.add(3);
        arg.add(2);
        arg.add(1);
        List<Integer> actual = Collections3.sort(arg, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(3);
        assertThat(actual.get(0)).isEqualTo(1);
        assertThat(actual.get(1)).isEqualTo(2);
        assertThat(actual.get(2)).isEqualTo(3);

    }

}
