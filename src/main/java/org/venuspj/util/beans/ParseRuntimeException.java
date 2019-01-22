package org.venuspj.util.beans;

import org.venuspj.exception.VRuntimeException;

import java.text.ParseException;

/**
 * {@link ParseException}をラップする例外です。
 */
public class ParseRuntimeException extends VRuntimeException {

    private static final long serialVersionUID = -5237329676597387063L;

    /**
     * {@link ParseRuntimeException}を作成します。
     *
     * @param cause
     */
    public ParseRuntimeException(ParseException cause) {
        super("ESSR0050", new Object[]{cause}, cause);
    }
}
