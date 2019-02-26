package org.venuspj.exception;

import java.text.ParseException;

import static org.venuspj.util.collect.Arrays2.asArray;

/**
 * 解析できなかった場合にスローされる例外です。
 */
public class ParseRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = -5237329676597387063L;

    /**
     * {@link ParseRuntimeException}を作成します。
     *
     * @param cause 原因となった例外
     */
    public ParseRuntimeException(final ParseException cause) {
        super("EUTL0050", asArray(cause), cause);
    }

    /**
     * {@link ParseRuntimeException}を作成します。
     *
     * @param s 解析できなかった文字列
     */
    public ParseRuntimeException(final String s) {
        super("EUTL0050", asArray(s));
    }

}
