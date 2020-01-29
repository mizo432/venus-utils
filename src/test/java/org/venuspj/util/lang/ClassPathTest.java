package org.venuspj.util.lang;

import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ClassPathTest {
    @Test
    public void test() {
        Set<Class<?>> aaa = ClassPath.listRecursiveClasses(this.getClass());
        assertThat(aaa)
                .isNotEmpty();
    }
}
