package org.venuspj.util.strings2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Strings2Test {

  /**
   * Method under test: {@link Strings2#defaultIfEmpty(String, String)}
   */
  @Test
  void defaultIfEmpty() {
    // Arrange, Act and Assert
    assertEquals("String", Strings2.defaultIfEmpty("String", "42"));
    assertEquals("foo", Strings2.defaultIfEmpty(null, "foo"));
    assertEquals("foo", Strings2.defaultIfEmpty(Strings2.EMPTY, "foo"));
  }

  /**
   * Method under test: {@link Strings2#defaultIfNull(String, String)}
   */
  @Test
  void defaultIfNull() {
    // Arrange, Act and Assert
    assertEquals("String", Strings2.defaultIfNull("String", "42"));
    assertEquals("foo", Strings2.defaultIfNull(null, "foo"));
  }

  /**
   * Method under test: {@link Strings2#trimTextAtStart(String)}
   */
  @Test
  void trimTextAtStart() {
    // Arrange, Act and Assert
    assertEquals("Input Text", Strings2.trimTextAtStart("Input Text"));
    assertNull(Strings2.trimTextAtStart(null));
    assertEquals(Strings2.EMPTY, Strings2.trimTextAtStart(Strings2.BLANK));
    assertEquals(Strings2.EMPTY, Strings2.trimTextAtStart(Strings2.EMPTY));
    assertEquals("Input Text", Strings2.trimTextAtStart("Input Text", "Trim String"));
    assertNull(Strings2.trimTextAtStart(null, null));
    assertEquals(Strings2.EMPTY, Strings2.trimTextAtStart(Strings2.BLANK, null));
  }

  /**
   * Method under test: {@link Strings2#trimTextAtEnd(String)}
   */
  @Test
  void trimTextAtEnd() {
    // Arrange, Act and Assert
    assertEquals("Input String", Strings2.trimTextAtEnd("Input String"));
    assertEquals(Strings2.EMPTY, Strings2.trimTextAtEnd(Strings2.BLANK));
    assertNull(Strings2.trimTextAtEnd(null));
  }

  /**
   * Method under test: {@link Strings2#splitWorker(String, String, int, boolean)}
   */
  @Test
  public void splitWorker() {
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
    public void test01() throws Exception {
      String arg = "";
      boolean actual = Strings2.isEmpty(arg);
      Java6Assertions.assertThat(actual).isTrue();

    }

    @Test
    public void test02() throws Exception {
      String arg = null;
      boolean actual = Strings2.isEmpty(arg);
      Java6Assertions.assertThat(actual).isTrue();

    }

    @Test
    public void test03() throws Exception {
      String arg = "A";
      boolean actual = Strings2.isEmpty(arg);
      Java6Assertions.assertThat(actual).isFalse();

    }
  }

  @Nested
  class IsNotEmptyTest {

    @Test
    public void test01() {
      String arg = "";
      boolean actual = Strings2.isNotEmpty(arg);
      Java6Assertions.assertThat(actual).isFalse();

    }

    @Test
    public void test02() {
      String arg = "";
      boolean actual = Strings2.isNotEmpty(arg);
      Java6Assertions.assertThat(actual).isFalse();

    }

    @Test
    public void test03() throws Exception {
      String arg = "A";
      boolean actual = Strings2.isNotEmpty(arg);
      Java6Assertions.assertThat(actual).isTrue();

    }
  }

  @Nested
  class IsNumberTest {

    @Test
    public void test01() {
      String arg = "";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test02() {
      String arg = "1";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test03() throws Exception {
      String arg = " 1";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test04() throws Exception {
      String arg = "3 ";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test05() throws Exception {
      String arg = String.valueOf(Integer.MIN_VALUE);
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test06() throws Exception {
      String arg = String.valueOf(Integer.MAX_VALUE);
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test07() throws Exception {
      String arg = "0x10";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test08() throws Exception {
      String arg = "0.1";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test09() throws Exception {
      String arg = "0xf";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test11() throws Exception {
      String arg = "0xff";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test12() throws Exception {
      String arg = "0xG";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test13() throws Exception {
      String arg = "0x ";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test14() throws Exception {
      String arg = "1e3";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test22() throws Exception {
      String arg = "1";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test23() throws Exception {
      String arg = "- 1";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test24() throws Exception {
      String arg = "-3 ";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test27() throws Exception {
      String arg = "-0x10";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test28() throws Exception {
      String arg = "-0.1";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test29() throws Exception {
      String arg = "-0xf";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test31() throws Exception {
      String arg = "-0xff";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void test32() throws Exception {
      String arg = "-0xG";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test33() throws Exception {
      String arg = "-0x ";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void test34() throws Exception {
      String arg = "-1e3";
      boolean actual = Strings2.isNumber(arg);
      Java6Assertions.assertThat(actual).isTrue();
    }

  }

  @Nested
  class RepeatTest {

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
    public void repeat3() {
      String actual = Strings2.repeat("12", 3);
      Java6Assertions.assertThat(actual).isNotNull().isEqualTo("121212");

    }

    @Test
    public void repeat11() {
      String actual = Strings2.repeat("1", 1, ", ");
      Java6Assertions.assertThat(actual).isNotNull().isEqualTo("1");

    }

    @Test
    public void repeat12() {
      String actual = Strings2.repeat("12", 1, ", ");
      Java6Assertions.assertThat(actual).isNotNull().isEqualTo("12");

    }

    @Test
    public void repeat13() {
      String actual = Strings2.repeat("12", 3, ", ");
      Java6Assertions.assertThat(actual).isNotNull().isEqualTo("12, 12, 12");

    }

    @Test
    public void repeat14() {
      Strings2.repeat("111", Integer.MAX_VALUE);

    }
  }

}
