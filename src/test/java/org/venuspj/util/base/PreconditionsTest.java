package org.venuspj.util.base;

import org.junit.Test;
import org.venuspj.util.functor.Command;

import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionsTest {
    @Test(expected = NullPointerException.class)
    public void checkNotNull1() throws Exception {
        Preconditions.checkNotNull(null);
    }

    @Test
    public void checkNotNull2() throws Exception {
        Preconditions.checkNotNull(Integer.valueOf(1));
    }

    @Test
    public void checkArgument1() {
        Preconditions.checkArgument(true, new RuntimeException("test"));
    }

    @Test(expected = RuntimeException.class)
    public void checkArgument2() {
        Preconditions.checkArgument(false, new RuntimeException("test"));

    }

    @Test
    public void checkArgument3() {
        DummyDto arg = new DummyDto(1);
        Preconditions.checkArgument(arg.getValue() == 0, () -> arg.setMessage("MSG"));
        assertThat(arg.getMessage())
                .isEqualTo("MSG");

    }

    private static class DummyDto {
        private final int value;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public DummyDto(int aValue) {
            value = aValue;
        }

        public int getValue() {
            return value;
        }

    }
}
