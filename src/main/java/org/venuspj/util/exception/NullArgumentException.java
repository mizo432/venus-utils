package org.venuspj.util.exception;


import java.util.Arrays;

/**
 * 引数がnullだった場合にthrowする例外です。
 * <p>
 * {@link NullPointerException}をthrowする代わりに使うことを想定しています。
 */
public class NullArgumentException extends VIllegalArgumentException {

    /**
     * {@link NullArgumentException}を作成します。
     *
     * @param argName {@code null} である引数の名前
     */
    public NullArgumentException(final String argName) {
        super(argName, "EUTL0008", Arrays.asList(argName).toArray());
    }

}
