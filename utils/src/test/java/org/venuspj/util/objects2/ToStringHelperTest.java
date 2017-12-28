package org.venuspj.util.objects2;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.venuspj.util.collect.Lists2.newArrayList;
import static org.venuspj.util.objects2.Objects2.toStringHelper;

public class ToStringHelperTest {

    @Test
    public void addAllFields() {
        TargetChildClass child = new TargetChildClass();
        TargetClass targetClass = new TargetClass(child);
        String actual = toStringHelper(targetClass).addAllFields().toString();
        assertThat(actual).isNotNull();
        System.out.println(actual);
    }

    @Test
    public void multiLine() {
        TargetChildClass child = new TargetChildClass();
        child.integerField = 1;
        child.stringField = "1";
        child.anyDateTime = new AnyDateTimeClass(LocalDateTime.now());
        TargetClass targetClass = new TargetClass(child);
        String actual = toStringHelper(targetClass).addAllDeclaredFields().multiLine().toString();
        assertThat(actual).isNotNull();
        System.out.println(actual);
    }

    @Test
    public void omitNullValues() {
        TargetChildClass child = new TargetChildClass();
        child.stringField = "1";
        child.anyDateTime = new AnyDateTimeClass(LocalDateTime.now());
        TargetClass targetClass = new TargetClass(child);
        String actual = toStringHelper(targetClass).addAllFields().omitNullValues().toString();
        assertThat(actual).isNotNull();
        System.out.println(actual);
    }

    @Test
    public void addAllDeclaredFields() {
        TargetChildClass child = new TargetChildClass();
        child.integerField = 1;
        child.stringField = "1";
        child.anyDateTime = new AnyDateTimeClass(LocalDateTime.now());
        TargetClass targetClass = new TargetClass(child);
        String actual = toStringHelper(targetClass).addAllDeclaredFields().toString();
        assertThat(actual).isNotNull();
        System.out.println(actual);
    }

    @Test
    public void listClass() {
        TargetChildClass child = new TargetChildClass();
        child.integerField = 1;
        child.stringField = "1";
        child.anyDateTime = new AnyDateTimeClass(LocalDateTime.now());
        ListClass targetClass = new ListClass();
        targetClass.list.addAll(newArrayList(child));
        String actual = toStringHelper(targetClass).addAllDeclaredFields().multiLine().toString();
        assertThat(actual).isNotNull();
        System.out.println(actual);
    }

    @Test
    public void helper() {
        TargetChildClass targetClass = new TargetChildClass();
        targetClass.integerField = 1;
        targetClass.stringField = "1";
        targetClass.anyDateTime = new AnyDateTimeClass(LocalDateTime.now());
        String actual = toStringHelper(targetClass)
                .add("integerField", targetClass.integerField)
                .add("stringField", targetClass.stringField)
                .add("anyDateTime", targetClass.anyDateTime)
                .multiLine().toString();
        assertThat(actual).isNotNull();
        System.out.println(actual);
    }

    private static abstract class AbstractTargetClass {
        private TargetChildClass child;

        protected AbstractTargetClass(TargetChildClass aChild) {
            child = aChild;
        }
    }

    private static class TargetClass extends AbstractTargetClass {
        private TargetChildClass child;

        TargetClass(TargetChildClass aChild) {
            super(aChild);
            child = aChild;
        }
    }

    private static class TargetChildClass {
        public String stringField;
        public Integer integerField;
        public int intField;
        public AnyDateTimeClass anyDateTime;

    }

    private static class ListClass {
        public List<TargetChildClass> list = newArrayList();

    }

    private static class AnyDateTimeClass {
        private LocalDateTime value;

        AnyDateTimeClass(LocalDateTime aValue) {
            value = LocalDateTime.now();

        }

    }

}