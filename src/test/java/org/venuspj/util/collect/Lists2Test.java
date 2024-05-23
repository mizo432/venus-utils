package org.venuspj.util.collect;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.venuspj.util.collect.Lists2.newArrayList;

import java.util.List;
import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


/**
 *
 */
public class Lists2Test {
// private static final Logger LOGGER = LoggerFactory.getLogger(Lists2Test.class);

  @Nested
  class GetPageTest {

    @Test
    public void testGetPageReturnsEmptyWhenSourceListSizeLessThanFromIndex() {
      List<String> sourceList = newArrayList("one", "two", "three");
      List<String> actual = Lists2.getPage(sourceList, 5, 2);
      assertThat(actual).isEmpty();
    }

    @Test
    public void testGetPageReturnsSubListFromGivenPageNumberAndPageSize() {
      List<String> sourceList = newArrayList("one", "two", "three", "four", "five", "six");
      List<String> actual = Lists2.getPage(sourceList, 2, 2);
      List<String> expected = newArrayList("three", "four");
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetPageReturnsRemainingListItemsWhenPageSizeGreaterThanRemainingItems() {
      List<String> sourceList = newArrayList("one", "two", "three", "four", "five", "six");
      List<String> actual = Lists2.getPage(sourceList, 2, 5);
      List<String> expected = newArrayList("six");
      assertThat(actual).isEqualTo(expected);
    }
  }


  @Nested
  class NewArrayListTest {

    @Test
    public void testRemovingNothingWhenListHasNothing() throws Exception {
      List<String> sourceList = newArrayList("nothing", "nothing", "nothing");
      List<String> actual = Lists2.removeNothing(sourceList);
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(0);
    }

    @Test
    public void testRemovingNothingWhenListHasSomething() throws Exception {
      List<String> sourceList = newArrayList("nothing", "something", "nothing");
      List<String> actual = Lists2.removeNothing(sourceList);
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(1);
      assertThat(actual.get(0))
          .isEqualTo("something");
    }

    @Test
    public void testNewArrayListNoArgs() throws Exception {
      List<Object> actual = newArrayList();
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(0);
    }

    @Test
    public void testNewArrayListArgs() throws Exception {
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
    public void testNewArrayListWithCapacity() throws Exception {
      List<Object> actual = Lists2.newArrayListWithCapacity(10);
      assertThat(actual)
          .isNotNull();
      assertThat(actual.size())
          .isEqualTo(0);
    }

  }

  @Test
  public void testNewArrayListWithCapacityWithNegative() {
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
    public void transformTestWithStrings() {
      List<String> inputList = newArrayList("a", "b", "c");
      List<String> actual = Lists2.transform(inputList, String::toUpperCase);
      List<String> expected = newArrayList("A", "B", "C");
      assertThat(actual)
          .isEqualTo(expected);
    }

    @Test
    public void transformTestWithIntegers() {
      List<Integer> inputList = newArrayList(1, 2, 3);
      List<Integer> actual = Lists2.transform(inputList, number -> number * 2);
      List<Integer> expected = newArrayList(2, 4, 6);
      assertThat(actual)
          .isEqualTo(expected);
    }
  }

  @Test
  public void unmodifiableListCreatesUnmodifiableList() {
    List<String> list = newArrayList("a", "b", "c");
    List<String> unmodifiableList = Lists2.unmodifiableList(list);

    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> {
      unmodifiableList.add("d");
    });
  }

  @Test
  public void unmodifiableListReflectsOriginalListChanges() {
    List<String> list = newArrayList("a", "b", "c");
    List<String> unmodifiableList = Lists2.unmodifiableList(list);

    list.add("d");

    assertThat(unmodifiableList.contains("d")).isTrue();
  }

  @Test
  public void unmodifiableListDoesNotAllowNull() {
    assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
      Lists2.unmodifiableList(null);
    });
  }
}
