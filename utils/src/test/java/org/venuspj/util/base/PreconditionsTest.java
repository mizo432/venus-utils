package org.venuspj.util.base;

import org.junit.Test;

public class PreconditionsTest {
    @Test(expected = NullPointerException.class)
    public void checkNotNull1() throws Exception {
        Preconditions.checkNotNull(null);
    }

    @Test
    public void checkNotNull2() throws Exception {
        Preconditions.checkNotNull(Integer.valueOf(1));
    }
}
