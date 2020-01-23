package org.venuspj.util.convert;

import java.sql.Date;

/**
 * {@link Date}用の変換ユーティリティです。
 */
public class SqlDateConversions {

    /**
     * インスタンスを構築します。
     */
    protected SqlDateConversions() {
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
        java.util.Date date = DateConversions.toDate(o, pattern);
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }
}
