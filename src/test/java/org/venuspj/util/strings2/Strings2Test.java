package org.venuspj.util.strings2;

import org.assertj.core.api.Java6Assertions;
import org.junit.Test;

public class Strings2Test {
    public static class IsEmptyTest {
        @Test
        public void test01() throws Exception {
            String arg = "";
            boolean actual = Strings2.isEmpty(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();

        }

        @Test
        public void test02() throws Exception {
            String arg = null;
            boolean actual = Strings2.isEmpty(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();

        }

        @Test
        public void test03() throws Exception {
            String arg = "A";
            boolean actual = Strings2.isEmpty(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();

        }
    }

    public static class IsNotEmptyTest {
        @Test
        public void test01() throws Exception {
            String arg = "";
            boolean actual = Strings2.isNotEmpty(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();

        }

        @Test
        public void test02() throws Exception {
            String arg = "";
            boolean actual = Strings2.isNotEmpty(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();

        }

        @Test
        public void test03() throws Exception {
            String arg = "A";
            boolean actual = Strings2.isNotEmpty(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();

        }
    }


    public static class IsTestTest {
        @Test
        public void test01() throws Exception {
            String arg = "";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test02() throws Exception {
            String arg = "1";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test03() throws Exception {
            String arg = " 1";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test04() throws Exception {
            String arg = "3 ";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test05() throws Exception {
            String arg = String.valueOf(Integer.MIN_VALUE);
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test06() throws Exception {
            String arg = String.valueOf(Integer.MAX_VALUE);
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test07() throws Exception {
            String arg = "0x10";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test08() throws Exception {
            String arg = "0.1";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test09() throws Exception {
            String arg = "0xf";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test11() throws Exception {
            String arg = "0xff";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test12() throws Exception {
            String arg = "0xG";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test13() throws Exception {
            String arg = "0x ";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test14() throws Exception {
            String arg = "1e3";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test22() throws Exception {
            String arg = "1";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test23() throws Exception {
            String arg = "- 1";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test24() throws Exception {
            String arg = "-3 ";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test27() throws Exception {
            String arg = "-0x10";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test28() throws Exception {
            String arg = "-0.1";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test29() throws Exception {
            String arg = "-0xf";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test31() throws Exception {
            String arg = "-0xff";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

        @Test
        public void test32() throws Exception {
            String arg = "-0xG";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test33() throws Exception {
            String arg = "-0x ";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isFalse();
        }

        @Test
        public void test34() throws Exception {
            String arg = "-1e3";
            boolean actual = Strings2.isNumber(arg);
            Java6Assertions.assertThat(actual)
                    .isTrue();
        }

    }


    public static class RepeatTest {

        @Test
        public void repeat1() throws Exception {
            String actual = Strings2.repeat("1", 1);
            Java6Assertions.assertThat(actual).isNotNull().isEqualTo("1");

        }

        @Test
        public void repeat2() throws Exception {
            String actual = Strings2.repeat("12", 1);
            Java6Assertions.assertThat(actual).isNotNull().isEqualTo("12");

        }

        @Test
        public void repeat3() throws Exception {
            String actual = Strings2.repeat("12", 3);
            Java6Assertions.assertThat(actual).isNotNull().isEqualTo("121212");

        }

        @Test
        public void repeat11() throws Exception {
            String actual = Strings2.repeat("1", 1, ", ");
            Java6Assertions.assertThat(actual).isNotNull().isEqualTo("1");

        }

        @Test
        public void repeat12() throws Exception {
            String actual = Strings2.repeat("12", 1, ", ");
            Java6Assertions.assertThat(actual).isNotNull().isEqualTo("12");

        }

        @Test
        public void repeat13() throws Exception {
            String actual = Strings2.repeat("12", 3, ", ");
            Java6Assertions.assertThat(actual).isNotNull().isEqualTo("12, 12, 12");

        }

        @Test(expected = ArrayIndexOutOfBoundsException.class)
        public void repeat14() throws Exception {
            Strings2.repeat("111", Integer.MAX_VALUE);

        }
    }
}
