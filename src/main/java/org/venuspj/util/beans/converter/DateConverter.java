package org.venuspj.util.beans.converter;

import org.venuspj.util.convert.DateConversions;
import org.venuspj.util.beans.EmptyRuntimeException;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.strings2.Strings2;

import java.util.Date;

/**
 * 日付用のコンバータです。
 */
public class DateConverter implements Converter {

    /**
     * 日付のパターンです。
     */
    protected String pattern;

    /**
     * インスタンスを構築します。
     *
     * @param pattern 日付のパターン
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
        return DateConversions.toDate(value, pattern);
    }

    public String getAsString(Object value) {
        return StringConversions.toString((Date) value, pattern);
    }

    public boolean isTarget(Class clazz) {
        return clazz == Date.class;
    }
}
