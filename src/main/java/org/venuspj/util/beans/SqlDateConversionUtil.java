package org.venuspj.util.beans;

import java.sql.Date;

/**
 * {@link Date}用の変換ユーティリティです。
 */
public class SqlDateConversionUtil {

    /**
     * インスタンスを構築します。
     */
    protected SqlDateConversionUtil() {
    }

    /**
     * {@link Date}に変換します。
     *
     * @param o
     * @return {@link Date}
     */
    public static Date toDate(Object o) {
        return toDate(o, null);
    }

    /**
     * {@link Date}に変換します。
     *
     * @param o
     * @param pattern
     * @return {@link Date}
     */
    public static Date toDate(Object o, String pattern) {
        if (o instanceof Date) {
            return (Date) o;
        }
        java.util.Date date = DateConversionUtil.toDate(o, pattern);
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }
}
