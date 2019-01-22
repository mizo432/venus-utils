package org.venuspj.util.beans;

import org.venuspj.util.strings2.Strings2;

import java.util.Date;

/**
 * 日付用のコンバータです。
 *
 * @author higa
 *
 */
public class DateConverter implements Converter {

    /**
     * 日付のパターンです。
     */
    protected String pattern;

    /**
     * インスタンスを構築します。
     *
     * @param pattern
     *            日付のパターン
     */
    public DateConverter(String pattern) {
        if (Strings2.isEmpty(pattern)) {
            throw new EmptyRuntimeException("pattern");
        }
        this.pattern = pattern;
    }

    public Object getAsObject(String value) {
        if (Strings2.isEmpty(value)) {
            return null;
        }
        return DateConversionUtil.toDate(value, pattern);
    }

    public String getAsString(Object value) {
        return StringConversionUtil.toString((Date) value, pattern);
    }

    public boolean isTarget(Class clazz) {
        return clazz == Date.class;
    }
}
