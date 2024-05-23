package org.venuspj.util.strings2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class Strings2Test {

  /**
   * Method under test: {@link Strings2#defaultIfEmpty(String, String)}
   */
  @Test
  void defaultIfEmpty() {
    // Arrange, Act and Assert
    assertThat(Strings2.defaultIfEmpty("String", "42")).isEqualTo("String");
    assertThat(Strings2.defaultIfEmpty(null, "foo")).isEqualTo("foo");
    assertThat(Strings2.defaultIfEmpty(Strings2.EMPTY, "foo")).isEqualTo("foo");
  }

  /**
   * Method under test: {@link Strings2#defaultIfNull(String, String)}
   */
  @Test
  void defaultIfNull() {
    // Arrange, Act and Assert
    assertThat(Strings2.defaultIfNull("String", "42")).isEqualTo("String");
    assertThat(Strings2.defaultIfNull(null, "foo")).isEqualTo("foo");
  }

  /**
   * Method under test: {@link Strings2#trimTextAtStart(String)}
   */
  @Test
  void trimTextAtStart() {
    // Arrange, Act and Assert
    assertThat(Strings2.trimTextAtStart("Input Text")).isEqualTo("Input Text");
    assertThat(Strings2.trimTextAtStart(null)).isNull();
    assertThat(Strings2.trimTextAtStart(Strings2.BLANK)).isEqualTo(Strings2.EMPTY);
    assertThat(Strings2.trimTextAtStart(Strings2.EMPTY)).isEqualTo(Strings2.EMPTY);
    assertThat(Strings2.trimTextAtStart("Input Text", "Trim String")).isEqualTo("Input Text");
    assertThat(Strings2.trimTextAtStart(null, null)).isNull();
    assertThat(Strings2.trimTextAtStart(Strings2.BLANK, null)).isEqualTo(Strings2.EMPTY);
  }

  /**
   * Method under test: {@link Strings2#trimTextAtEnd(String)}
   */
  @Test
  void trimTextAtEnd() {
    // Arrange, Act and Assert
    assertThat(Strings2.trimTextAtEnd("Input String")).isEqualTo("Input String");
    assertThat(Strings2.trimTextAtEnd(Strings2.BLANK)).isEqualTo(Strings2.EMPTY);
    assertThat(Strings2.trimTextAtEnd(null)).isNull();
  }

  /**
   * Method under test: {@link Strings2#splitWorker(String, String, int, boolean)}
   */
  @Test
  void splitWorker() {
    // Arrange, Act and Assert
    assertThat(Strings2.splitWorker("Str", "Separator Chars", 3, true))
        .isEqualTo(new String[]{Strings2.EMPTY, Strings2.EMPTY, "r"});
    assertThat(Strings2.splitWorker("foo", null, 1, false)).isEqualTo(new String[]{"foo"});
    assertThat(Strings2.splitWorker("foo", "foo", 1, false).length).isEqualTo(0);
    assertThat(Strings2.splitWorker(null, "Separator Chars", 3, true).length).isEqualTo(0);
    assertThat(Strings2.splitWorker("42", "Separator Chars", 3, true)).isEqualTo(
        new String[]{"42"});
    assertThat(Strings2.splitWorker(Strings2.EMPTY, "Separator Chars", 3, true).length).isEqualTo(
        0);
    assertThat(Strings2.splitWorker("Str", "Separator Chars", 15, true))
        .isEqualTo(new String[]{Strings2.EMPTY, Strings2.EMPTY, Strings2.EMPTY, Strings2.EMPTY});
    assertThat(Strings2.splitWorker("foo", "Separator Chars", 1, false)).isEqualTo(
        new String[]{"foo"});
  }

  @Nested
  class IsEmptyTest {

    @Test
    void test01() {
      String arg = "";
      boolean actual = Strings2.isEmpty(arg);
      assertThat(actual).isTrue();

    }

    @Test
    void test02() {
      String arg = null;
      boolean actual = Strings2.isEmpty(arg);
      assertThat(actual).isTrue();

    }

    @Test
    void test03() {
      String arg = "A";
      boolean actual = Strings2.isEmpty(arg);
      assertThat(actual).isFalse();

    }
  }

  @Nested
  class IsNotEmptyTest {

    @Test
    void test01() {
      String arg = "";
      boolean actual = Strings2.isNotEmpty(arg);
      assertThat(actual).isFalse();

    }

    @Test
    void test02() {
      String arg = "";
      boolean actual = Strings2.isNotEmpty(arg);
      assertThat(actual).isFalse();

    }

    @Test
    void test03() {
      String arg = "A";
      boolean actual = Strings2.isNotEmpty(arg);
      assertThat(actual).isTrue();

    }
  }

  @Nested
  class IsNumberTest {

    @Test
    void test01() {
      String arg = "";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test02() {
      String arg = "1";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test03() {
      String arg = " 1";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test04() {
      String arg = "3 ";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test05() {
      String arg = String.valueOf(Integer.MIN_VALUE);
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test06() {
      String arg = String.valueOf(Integer.MAX_VALUE);
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test07() {
      String arg = "0x10";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test08() {
      String arg = "0.1";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test09() {
      String arg = "0xf";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test11() {
      String arg = "0xff";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test12() {
      String arg = "0xG";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test13() {
      String arg = "0x ";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test14() {
      String arg = "1e3";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test22() {
      String arg = "1";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test23() {
      String arg = "- 1";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test24() {
      String arg = "-3 ";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test27() {
      String arg = "-0x10";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test28() {
      String arg = "-0.1";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test29() {
      String arg = "-0xf";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test31() {
      String arg = "-0xff";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

    @Test
    void test32() {
      String arg = "-0xG";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test33() {
      String arg = "-0x ";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isFalse();
    }

    @Test
    void test34() {
      String arg = "-1e3";
      boolean actual = Strings2.isNumber(arg);
      assertThat(actual).isTrue();
    }

  }

  @Nested
  class RepeatTest {

    @Test
    void repeat1() {
      String actual = Strings2.repeat("1", 1);
      assertThat(actual).isNotNull().isEqualTo("1");

    }

    @Test
    void repeat2() {
      String actual = Strings2.repeat("12", 1);
      assertThat(actual).isNotNull().isEqualTo("12");

    }

    @Test
    void repeat3() {
      String actual = Strings2.repeat("12", 3);
      assertThat(actual).isNotNull().isEqualTo("121212");

    }

    @Test
    void repeat11() {
      String actual = Strings2.repeat("1", 1, ", ");
      assertThat(actual).isNotNull().isEqualTo("1");

    }

    @Test
    void repeat12() {
      String actual = Strings2.repeat("12", 1, ", ");
      assertThat(actual).isNotNull().isEqualTo("12");

    }

    @Test
    void repeat13() {
      String actual = Strings2.repeat("12", 3, ", ");
      assertThat(actual).isNotNull().isEqualTo("12, 12, 12");

    }

  }

}
