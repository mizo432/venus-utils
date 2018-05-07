package org.venuspj.util.primitives;

public class Booleans {
    public static int compare(boolean a, boolean b) {
        return (a == b) ? 0 : (a ? 1 : -1);
    }
}
