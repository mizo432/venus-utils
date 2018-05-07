package org.venuspj.util.collect;

import org.assertj.core.api.Java6Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 */
public class Maps2Test {

    public static class NewHashMapTest {
        @Test
        public void test01() throws Exception {
            Map<Integer, Integer> target = Maps2.newHashMap();

            Java6Assertions.assertThat(target)
                    .isNotNull()
                    .isInstanceOf(HashMap.class);
        }

    }

    public static class NewHashMapWithExpectedSizeTest {
        @Test
        public void test01() throws Exception {
            Map<Integer, Integer> target = Maps2.newHashMapWithExpectedSize(1);

            Java6Assertions.assertThat(target)
                    .isNotNull()
                    .isInstanceOf(HashMap.class);
        }

    }

    public static class NewLinkedHashMapTest {
        @Test
        public void test01() throws Exception {
            Map<Integer, Integer> target = Maps2.newLinkedHashMap();

            Java6Assertions.assertThat(target)
                    .isNotNull()
                    .isInstanceOf(LinkedHashMap.class);
        }

    }
}