package org.venuspj.util.lang;

import org.junit.Test;

import java.util.Set;

public class ClassPathTest {
    @Test
    public void test() {
        Set<Class<?>> aaa = ClassPath.listRecursiveClasses("org.venuspj.util");
    }
}
