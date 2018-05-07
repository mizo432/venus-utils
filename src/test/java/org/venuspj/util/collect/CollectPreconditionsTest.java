package org.venuspj.util.collect;

import org.junit.Test;

public class CollectPreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void checkEntryNotNull1() throws Exception {
        CollectPreconditions.checkEntryNotNull(null, null);

    }

    @Test(expected = NullPointerException.class)
    public void checkEntryNotNull2() throws Exception {
        CollectPreconditions.checkEntryNotNull("key", null);

    }

    @Test
    public void checkEntryNotNull3() throws Exception {
        CollectPreconditions.checkEntryNotNull("key", "value");

    }

    @Test
    public void checkNonNegative1() throws Exception {
        CollectPreconditions.checkNonNegative(1, "value");

    }

    @Test
    public void checkNonNegative2() throws Exception {
        CollectPreconditions.checkNonNegative(1L, "value");

    }

    @Test
    public void checkNonNegative3() throws Exception {
        CollectPreconditions.checkNonNegative(0, "value");

    }

    @Test
    public void checkNonNegative4() throws Exception {
        CollectPreconditions.checkNonNegative(0L, "value");

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNonNegative5() throws Exception {
        CollectPreconditions.checkNonNegative(-1, "value");

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNonNegative6() throws Exception {
        CollectPreconditions.checkNonNegative(-1L, "value");

    }

    @Test
    public void checkPositive1() throws Exception {
        CollectPreconditions.checkPositive(1, "value");

    }
    @Test(expected = IllegalArgumentException.class)
    public void checkPositive2() throws Exception {
        CollectPreconditions.checkPositive(0, "value");

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkPositive3() throws Exception {
        CollectPreconditions.checkPositive(-1, "value");

    }
    @Test
    public void checkRemove1() throws Exception {
        CollectPreconditions.checkRemove(true);

    }

    @Test(expected = IllegalStateException.class)
    public void checkRemove2() throws Exception {
        CollectPreconditions.checkRemove(false);

    }
}
