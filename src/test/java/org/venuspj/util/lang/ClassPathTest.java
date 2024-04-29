package org.venuspj.util.lang;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.venuspj.util.DummyClass;

public class ClassPathTest {

  @Test
  public void test1() {
    Set<Class<?>> aaa = ClassPath.listRecursiveClasses(DummyClass.class);
    assertThat(aaa)
        .isNotEmpty();
    System.out.println(aaa.size());
    for (Class<?> c : aaa) {
      System.out.println(c);

    }
  }

  @Test
  public void test2() {
    Set<Class<?>> aaa = ClassPath.listRecursiveClasses("org.venuspj.util");
    assertThat(aaa)
        .isNotEmpty();
    System.out.println(aaa.size());
    for (Class<?> c : aaa) {
      System.out.println(c);

    }
  }
}
