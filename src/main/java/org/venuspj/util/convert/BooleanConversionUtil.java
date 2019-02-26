package org.venuspj.util.convert;

/**
 * {@link Boolean}用の変換ユーティリティです。
 */
public abstract class BooleanConversionUtil {

    /**
     * {@link Boolean}に変換します。
     *
     * @param o 変換元のオブジェクト
     * @return 変換された{@link Boolean}
     */
    public static Boolean toBoolean(final Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Boolean) {
            return (Boolean) o;
        } else if (o instanceof Number) {
            final int num = ((Number) o).intValue();
            return Boolean.valueOf(num != 0);
        } else if (o instanceof String) {
            final String s = (String) o;
            if ("true".equalsIgnoreCase(s)) {
                return Boolean.TRUE;
            } else if ("false".equalsIgnoreCase(s)) {
                return Boolean.FALSE;
            } else if (s.equals("0")) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * {@literal boolean}に変換します。
     *
     * @param o 変換元のオブジェクト
     * @return 変換された{@literal boolean}
     */
    public static boolean toPrimitiveBoolean(final Object o) {
        final Boolean b = toBoolean(o);
        if (b != null) {
            return b.booleanValue();
        }
        return false;
    }

}
