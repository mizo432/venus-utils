package org.venuspj.util.beans;

import org.venuspj.util.strings2.Strings2;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * 数値用のコンバータです。
*/
public class NumberConverter implements Converter {

    /**
     * 数値のパターンです。
     */
    protected String pattern;

    /**
     * インスタンスを構築します。
     *
     * @param pattern
     *            数値のパターン
     */
    public NumberConverter(String pattern) {
        if (Strings2.isEmpty(pattern)) {
            throw new EmptyRuntimeException("pattern");
        }
        this.pattern = pattern;
    }

    public Object getAsObject(String value) {
        if (Strings2.isEmpty(value)) {
            return null;
        }
        try {
            return new DecimalFormat(pattern).parse(value);
        } catch (ParseException e) {
            throw new ParseRuntimeException(e);
        }

    }

    public String getAsString(Object value) {
        return new DecimalFormat(pattern).format(value);
    }

    public boolean isTarget(Class clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

}

