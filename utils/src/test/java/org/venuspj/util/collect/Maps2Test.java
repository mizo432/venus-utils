package org.venuspj.util.collect;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 */
public class Maps2Test {

    public static class NewHashMapTest {
        @Test
        public void test01() throws Exception {
            Map<Integer, Integer> target = Maps2.newHashMap();

            assertThat(target)
                    .isNotNull()
                    .isInstanceOf(HashMap.class);
        }

    }

    public static class NewHashMapWithExpectedSizeTest {
        @Test
        public void test01() throws Exception {
            Map<Integer, Integer> target = Maps2.newHashMapWithExpectedSize(1);

            assertThat(target)
                    .isNotNull()
                    .isInstanceOf(HashMap.class);
        }

    }

    public static class NewLinkedHashMapTest {
        @Test
        public void test01() throws Exception {
            Map<Integer, Integer> target = Maps2.newLinkedHashMap();

            assertThat(target)
                    .isNotNull()
                    .isInstanceOf(LinkedHashMap.class);
        }

    }
}