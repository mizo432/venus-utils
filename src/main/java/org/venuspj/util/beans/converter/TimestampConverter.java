package org.venuspj.util.beans.converter;

import org.venuspj.util.beans.EmptyRuntimeException;
import org.venuspj.util.convert.StringConversions;
import org.venuspj.util.convert.TimestampConversions;
import org.venuspj.util.strings2.Strings2;

import java.util.Date;

/**
 * 日時用のコンバータです。
 */
public class TimestampConverter implements Converter {

    /**
     * 日時のパターンです。
     */
    protected String pattern;

    /**
     * インスタンスを構築します。
     *
     * @param pattern 日時のパターン
     */
    public TimestampConverter(String pattern) {
        if (Strings2.isEmpty(pattern)) {
            throw new EmptyRuntimeException("pattern");
        }
        this.pattern = pattern;
    }

    public Object getAsObject(String value) {
        if (Strings2
                .isEmpty(value)) {
            return null;
        }
        return TimestampConversions.toTimestamp(value, pattern);
    }

    public String getAsString(Object value) {
        return StringConversions.toString((Date) value, pattern);
    }

    public boolean isTarget(Class clazz) {
        return clazz == java.sql.Timestamp.class;
    }

}
