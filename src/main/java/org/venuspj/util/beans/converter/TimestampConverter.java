package org.venuspj.util.beans.converter;

import org.venuspj.util.beans.Converter;
import org.venuspj.util.beans.EmptyRuntimeException;
import org.venuspj.util.convert.StringConversionUtil;
import org.venuspj.util.convert.TimestampConversionUtil;
import org.venuspj.util.strings2.Strings2;

import java.util.Date;

/**
 * 日時用のコンバータです。
 *
 * @author higa
 *
 */
public class TimestampConverter implements Converter {

    /**
     * 日時のパターンです。
     */
    protected String pattern;

    /**
     * インスタンスを構築します。
     *
     * @param pattern
     *            日時のパターン
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
        return TimestampConversionUtil.toTimestamp(value, pattern);
    }

    public String getAsString(Object value) {
        return StringConversionUtil.toString((Date) value, pattern);
    }

    public boolean isTarget(Class clazz) {
        return clazz == java.sql.Timestamp.class;
    }

}
