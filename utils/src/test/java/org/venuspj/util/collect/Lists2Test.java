package org.venuspj.util.collect;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 */
public class Lists2Test {
// private static final Logger LOGGER = LoggerFactory.getLogger(Lists2Test.class);

    public static class GetPageTest {
        @Test
        public void test01() throws Exception {
            List<Object> actual = Lists2.getPage(Lists2.newArrayList(), 1, 1);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test02() throws Exception {
            List<Object> actual = Lists2.getPage(Lists2.newArrayList(), 1, 0);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void test03() throws Exception {
            List<Object> actual = Lists2.getPage(Lists2.newArrayList(), 0, 1);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(0);
        }

        @Test
        public void test04() throws Exception {
            List<Object> actual = Lists2.getPage(null, 1, 1);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(0);
        }

        @Test
        public void test11() throws Exception {
            List<Integer> sourceList = createDummyList(33);
            List<Integer> actual = Lists2.getPage(sourceList, 1, 10);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(10);
//            LOGGER.debug("actual:" + actual);
        }

        public List<Integer> createDummyList(int listCount) {
            List<Integer> sourceList = Lists2.newArrayListWithCapacity(listCount);
            for (int i = 0; i < listCount; i++) {
                sourceList.add(i);
            }
            return sourceList;
        }

        @Test
        public void test12() throws Exception {
            List<Integer> sourceList = createDummyList(33);
            List<Integer> actual = Lists2.getPage(sourceList, 3, 10);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(10);
        }

        @Test
        public void test13() throws Exception {
            List<Integer> sourceList = createDummyList(33);
            List<Integer> actual = Lists2.getPage(sourceList, 4, 10);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(3);
        }

        @Test
        public void test14() throws Exception {
            List<Integer> sourceList = createDummyList(30);
            List<Integer> actual = Lists2.getPage(sourceList, 1, 10);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(10);
        }

        @Test
        public void test15() throws Exception {
            List<Integer> sourceList = createDummyList(30);
            List<Integer> actual = Lists2.getPage(sourceList, 3, 10);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(10);
        }

        @Test
        public void test16() throws Exception {
            List<Integer> sourceList = createDummyList(30);
            List<Integer> actual = Lists2.getPage(sourceList, 4, 10);
            assertThat(actual)
                    .isNotNull();
            assertThat(actual.size())
                    .isEqualTo(0);
        }

    }
}