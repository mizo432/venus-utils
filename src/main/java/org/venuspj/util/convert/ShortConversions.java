package org.venuspj.util.convert;

import org.venuspj.util.strings2.Strings2;
import org.venuspj.util.text.DecimalFormats;

import java.text.SimpleDateFormat;

/**
 * {@link Short}用の変換ユーティリティです。
 */
public abstract class ShortConversions {

    /**
     * {@link Short}に変換します。
     *
     * @param o 変換元のオブジェクト
     * @return 変換された{@link Short}
     */
    public static Short toShort(final Object o) {
        return toShort(o, null);
    }

    /**
     * {@link Short}に変換します。
     *
     * @param o       変換元のオブジェクト
     * @param pattern パターン文字列
     * @return 変換された{@link Short}
     */
    public static Short toShort(final Object o, final String pattern) {
        if (o == null) {
            return null;
        } else if (o instanceof Short) {
            return (Short) o;
        } else if (o instanceof Number) {
            return ((Number) o).shortValue();
        } else if (o instanceof String) {
            return toShort((String) o);
        } else if (o instanceof java.util.Date) {
            if (pattern != null) {
                return Short.valueOf(new SimpleDateFormat(pattern).format(o));
            }
            return (short) ((java.util.Date) o).getTime();
        } else if (o instanceof Boolean) {
            return (Boolean) o ? Short.valueOf((short) 1)
                    : Short.valueOf((short) 0);
        } else {
            return toShort(o.toString());
        }
    }

    private static Short toShort(final String s) {
        if (Strings2.isEmpty(s)) {
            return null;
        }
        return Short.valueOf(DecimalFormats.normalize(s));
    }

    /**
     * {@literal short}に変換します。
     *
     * @param o 変換元のオブジェクト
     * @return 変換された{@literal short}
     */
    public static short toPrimitiveShort(final Object o) {
        return toPrimitiveShort(o, null);
    }

    /**
     * {@literal short}に変換します。
     *
     * @param o       変換元のオブジェクト
     * @param pattern パターン文字列
     * @return 変換された{@literal short}
     */
    public static short toPrimitiveShort(final Object o, final String pattern) {
        if (o == null) {
            return 0;
        } else if (o instanceof Number) {
            return ((Number) o).shortValue();
        } else if (o instanceof String) {
            return toPrimitiveShort((String) o);
        } else if (o instanceof java.util.Date) {
            if (pattern != null) {
                return Short
                        .parseShort(new SimpleDateFormat(pattern).format(o));
            }
            return (short) ((java.util.Date) o).getTime();
        } else if (o instanceof Boolean) {
            return (Boolean) o ? (short) 1 : (short) 0;
        } else {
            return toPrimitiveShort(o.toString());
        }
    }

    private static short toPrimitiveShort(final String s) {
        if (Strings2.isEmpty(s)) {
            return 0;
        }
        return Short.parseShort(DecimalFormats.normalize(s));
    }

}
