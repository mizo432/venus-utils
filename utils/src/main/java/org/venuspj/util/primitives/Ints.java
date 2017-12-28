package org.venuspj.util.primitives;

public final class Ints {
    public static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);

    public static int compare(int a, int b) {
        return (a < b) ? -1 : ((a > b) ? 1 : 0);
    }
}
