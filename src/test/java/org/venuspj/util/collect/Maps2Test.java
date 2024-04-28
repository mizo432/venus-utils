package org.venuspj.util.collect;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


/**
 *
 */
public class Maps2Test {

  @Nested
  class NewHashMapTest {

    @Test
    public void test01() {
      Map<Integer, Integer> target = Maps2.newHashMap();

      assertThat(target)
          .isNotNull()
          .isInstanceOf(HashMap.class);
    }

  }

  @Nested
  class NewHashMapWithExpectedSizeTest {

    @Test
    public void test01() {
      Map<Integer, Integer> target = Maps2.newHashMapWithExpectedSize(1);

      assertThat(target)
          .isNotNull()
          .isInstanceOf(HashMap.class);
    }

  }

  @Nested
  class NewLinkedHashMapTest {

    @Test
    public void test01() {
      Map<Integer, Integer> target = Maps2.newLinkedHashMap();

      assertThat(target)
          .isNotNull()
          .isInstanceOf(LinkedHashMap.class);
    }

  }
}