package org.venuspj.util.convert;

import org.venuspj.util.strings2.Strings2;
import org.venuspj.util.text.DecimalFormats;

import java.text.SimpleDateFormat;

/**
 * {@link Integer}用の変換ユーティリティです。
 */
public abstract class IntegerConversions {

    /**
     * {@link Integer}に変換します。
     *
     * @param o 変換元のオブジェクト
     * @return 変換された{@link Integer}
     */
    public static Integer toInteger(final Object o) {
        return toInteger(o, null);
    }

    /**
     * {@link Integer}に変換します。
     *
     * @param o       変換元のオブジェクト
     * @param pattern パターン文字列
     * @return 変換された{@link Integer}
     */
    public static Integer toInteger(final Object o, final String pattern) {
        if (o == null) {
            return null;
        } else if (o instanceof Integer) {
            return (Integer) o;
        } else if (o instanceof Number) {
            return ((Number) o).intValue();
        } else if (o instanceof String) {
            return toInteger((String) o);
        } else if (o instanceof java.util.Date) {
            if (pattern != null) {
                return Integer.valueOf(new SimpleDateFormat(pattern).format(o));
            }
            return (int) ((java.util.Date) o).getTime();
        } else if (o instanceof Boolean) {
            return (Boolean) o ? Integer.valueOf(1) : Integer
                    .valueOf(0);
        } else {
            return toInteger(o.toString());
        }
    }

    private static Integer toInteger(final String s) {
        if (Strings2.isEmpty(s)) {
            return null;
        }
        return Integer.valueOf(DecimalFormats.normalize(s));
    }

    /**
     * {@literal int}に変換します。
     *
     * @param o 変換元のオブジェクト
     * @return 変換された{@literal int}
     */
    public static int toPrimitiveInt(final Object o) {
        return toPrimitiveInt(o, null);
    }

    /**
     * {@literal int}に変換します。
     *
     * @param o       変換元のオブジェクト
     * @param pattern パターン文字列
     * @return 変換された{@literal int}
     */
    public static int toPrimitiveInt(final Object o, final String pattern) {
        if (o == null) {
            return 0;
        } else if (o instanceof Number) {
            return ((Number) o).intValue();
        } else if (o instanceof String) {
            return toPrimitiveInt((String) o);
        } else if (o instanceof java.util.Date) {
            if (pattern != null) {
                return Integer
                        .parseInt(new SimpleDateFormat(pattern).format(o));
            }
            return (int) ((java.util.Date) o).getTime();
        } else if (o instanceof Boolean) {
            return (Boolean) o ? 1 : 0;
        } else {
            return toPrimitiveInt(o.toString());
        }
    }

    private static int toPrimitiveInt(final String s) {
        if (Strings2.isEmpty(s)) {
            return 0;
        }
        return Integer.parseInt(DecimalFormats.normalize(s));
    }

}
