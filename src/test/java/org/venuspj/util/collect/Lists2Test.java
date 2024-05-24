package org.venuspj.util.collect;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.venuspj.util.collect.Lists2.newArrayList;

import java.util.List;
import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


/**
 * このクラスは、Lists2クラスのユニットテストを含んでいます。
 */
class Lists2Test {

  @Nested
  class GetPageTest {

    @Test
    void testGetPageReturnsEmptyWhenSourceListSizeLessThanFromIndex() {
      List<String> sourceList = newArrayList("one", "two", "three");
      List<String> actual = Lists2.getPage(sourceList, 5, 2);
      assertThat(actual).isEmpty();
    }

    @Test
    void testGetPageReturnsSubListFromGivenPageNumberAndPageSize() {
      List<String> sourceList = newArrayList("one", "two", "three", "four", "five", "six");
      List<String> actual = Lists2.getPage(sourceList, 2, 2);
      List<String> expected = newArrayList("three", "four");
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGetPageReturnsRemainingListItemsWhenPageSizeGreaterThanRemainingItems() {
      List<String> sourceList = newArrayList("one", "two", "three", "four", "five", "six");
      List<String> actual = Lists2.getPage(sourceList, 2, 5);
      List<String> expected = newArrayList("six");
      assertThat(actual).isEqualTo(expected);
    }
  }


  @Nested
  class NewArrayListTest {

    @Test
    void testRemovingNothingWhenListHasNothing() {
      List<String> sourceList = newArrayList("nothing", "nothing", "nothing");
      List<String> actual = Lists2.removeNothing(sourceList);
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(0);
    }


    @Test
    void testRemovingNothingWhenListHasSomething() {
      List<String> sourceList = newArrayList("nothing", "something", "nothing");
      List<String> actual = Lists2.removeNothing(sourceList);
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(1);
      assertThat(actual.getFirst())
          .isEqualTo("something");
    }

    @Test
    void testNewArrayListNoArgs() {
      List<Object> actual = newArrayList();
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(0);
    }

    @Test
    void testNewArrayListArgs() {
      List<Integer> actual = newArrayList(1, 2, 3);
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(3);
      assertThat(actual.get(0))
          .isEqualTo(1);
      assertThat(actual.get(1))
          .isEqualTo(2);
      Java6Assertions.assertThat(actual.get(2))
          .isEqualTo(3);
    }

    @Test
    void testNewArrayListWithCapacity() {
      List<Object> actual = Lists2.newArrayListWithCapacity(10);
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(0);
    }

  }

  @Test
  void testNewArrayListWithCapacityWithNegative() {
    boolean exceptionThrown = false;
    try {
      Lists2.newArrayListWithCapacity(-1);
    } catch (IllegalArgumentException e) {
      exceptionThrown = true;
    }
    assertThat(exceptionThrown)
        .isTrue();
  }

  @Nested
  class TransformTest {

    @Test
    void transformTestWithStrings() {
      List<String> inputList = newArrayList("a", "b", "c");
      List<String> actual = Lists2.transform(inputList, String::toUpperCase);
      List<String> expected = newArrayList("A", "B", "C");
      assertThat(actual)
          .isEqualTo(expected);
    }

    @Test
    void transformTestWithIntegers() {
      List<Integer> inputList = newArrayList(1, 2, 3);
      List<Integer> actual = Lists2.transform(inputList, number -> number * 2);
      List<Integer> expected = newArrayList(2, 4, 6);
      assertThat(actual)
          .isEqualTo(expected);
    }
  }

  @Test
  void unmodifiableListCreatesUnmodifiableList() {
    List<String> list = newArrayList("a", "b", "c");
    List<String> unmodifiableList = Lists2.unmodifiableList(list);

    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
        () -> unmodifiableList.add("d"));
  }

  @Test
  void unmodifiableListReflectsOriginalListChanges() {
    List<String> list = newArrayList("a", "b", "c");
    List<String> unmodifiableList = Lists2.unmodifiableList(list);

    list.add("d");

    assertThat(unmodifiableList.contains("d")).isTrue();
  }

  @Test
  void unmodifiableListDoesNotAllowNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(
        () -> Lists2.unmodifiableList(null));
  }

  @Nested
  class IntersectionTest {

    @Test
    void testIntersectionWithNoCommonElements() {
      List<String> list1 = newArrayList("one", "two", "three");
      List<String> list2 = newArrayList("four", "five", "six");
      List<String> actual = Lists2.intersection(list1, list2);
      assertThat(actual).isEmpty();
    }

    @Test
    void testIntersectionWithSomeCommonElements() {
      List<String> list1 = newArrayList("one", "two", "three");
      List<String> list2 = newArrayList("two", "three", "four");
      List<String> actual = Lists2.intersection(list1, list2);
      assertThat(actual).containsOnly("two", "three");
    }

    @Test
    void testIntersectionWithAllCommonElements() {
      List<String> list1 = newArrayList("one", "two", "three");
      List<String> list2 = newArrayList("one", "two", "three");
      List<String> actual = Lists2.intersection(list1, list2);
      assertThat(actual).containsOnly("one", "two", "three");
    }
  }

  @Nested
  class DifferenceTest {

    @Test
    void testDifferenceWithNoCommonElements() {
      List<String> list1 = newArrayList("one", "two", "three");
      List<String> list2 = newArrayList("four", "five", "six");
      List<String> actual = Lists2.difference(list1, list2);
      assertThat(actual).contains("one", "two", "three");
    }

    @Test
    void testDifferenceWithSomeCommonElements() {
      List<String> list1 = newArrayList("one", "two", "three");
      List<String> list2 = newArrayList("two", "three", "four");
      List<String> actual = Lists2.difference(list1, list2);
      assertThat(actual).contains("one", "four");
    }

    @Test
    void testDifferenceWithAllCommonElements() {
      List<String> list1 = newArrayList("one", "two", "three");
      List<String> list2 = newArrayList("one", "two", "three");
      List<String> actual = Lists2.difference(list1, list2);
      assertThat(actual).isEmpty();
    }
  }
}
