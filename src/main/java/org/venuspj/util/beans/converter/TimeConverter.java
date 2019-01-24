package org.venuspj.util.beans.converter;

import org.venuspj.util.beans.Converter;
import org.venuspj.util.beans.EmptyRuntimeException;
import org.venuspj.util.convert.StringConversionUtil;
import org.venuspj.util.convert.TimeConversionUtil;
import org.venuspj.util.strings2.Strings2;

import java.sql.Time;
import java.util.Date;

/**
 * 時間用のコンバータです。
 *
 */
public class TimeConverter implements Converter {

    /**
     * 時間のパターンです。
     */
    protected String pattern;

    /**
     * インスタンスを構築します。
     *
     * @param pattern
     *            時間のパターン
     */
    public TimeConverter(String pattern) {
        if (Strings2.isEmpty(pattern)) {
            throw new EmptyRuntimeException("pattern");
        }
        this.pattern = pattern;
    }

    public Object getAsObject(String value) {
        if (Strings2.isEmpty(value)) {
            return null;
        }
        return TimeConversionUtil.toTime(value, pattern);
    }

    public String getAsString(Object value) {
        return StringConversionUtil.toString((Date) value, pattern);
    }

    public boolean isTarget(Class clazz) {
        return clazz == Time.class;
    }

}
