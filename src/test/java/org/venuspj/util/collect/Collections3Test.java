package org.venuspj.util.collect;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Test;

public class Collections3Test {

  @Test
  public void cast() throws Exception {
    List<Integer> arg = Lists2.newArrayList();
    Collection<Integer> actual = Collections3.cast(arg);
    Java6Assertions.assertThat(actual).isNotNull();
    Java6Assertions.assertThat(actual.isEmpty()).isTrue();

  }

  @Test
  public void sort() {
    List<Integer> unsortedList = newArrayList(Arrays.asList(3, 2, 1));

    List<Integer> sortedList = unsortedList.stream()
        .sorted(Comparator.naturalOrder())
        .collect(Collectors.toList());

    assertThat(sortedList).isNotNull();
    assertThat(sortedList.size()).isEqualTo(3);
    assertThat(sortedList.get(0)).isEqualTo(1);
    assertThat(sortedList.get(1)).isEqualTo(2);
    assertThat(sortedList.get(2)).isEqualTo(3);
  }
}
