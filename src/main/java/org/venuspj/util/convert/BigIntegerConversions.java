package org.venuspj.util.convert;

import java.math.BigInteger;

/**
 * {@link BigInteger}用の変換ユーティリティです。
 */
public abstract class BigIntegerConversions {

    /**
     * {@link BigInteger}に変換します。
     *
     * @param o 変換元のオブジェクト
     * @return 変換された{@link BigInteger}
     */
    public static BigInteger toBigInteger(final Object o) {
        return toBigInteger(o, null);
    }

    /**
     * {@link BigInteger}に変換します。
     *
     * @param o       変換元のオブジェクト
     * @param pattern パターン文字列
     * @return 変換された{@link BigInteger}
     */
    public static BigInteger toBigInteger(final Object o, final String pattern) {
        if (o == null) {
            return null;
        } else if (o instanceof BigInteger) {
            return (BigInteger) o;
        } else {
            final Long l = LongConversions.toLong(o, pattern);
            if (l == null) {
                return null;
            }
            return BigInteger.valueOf(l.longValue());
        }
    }

}
