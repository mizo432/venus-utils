package org.venuspj.util.text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;

/**
 * {@link DecimalFormat}用のユーティリティクラスです。
 */
public abstract class DecimalFormats {

    /**
     * 数値の文字列での表記を正規化します。
     *
     * @param s 数値を表す文字列
     * @return 正規化された文字列
     * @see #normalize(String, Locale)
     */
    public static String normalize(final String s) {
        return normalize(s, Locale.getDefault());
    }

    /**
     * 数値の文字列での表記をグルーピングセパレータを削除し、小数点を.であらわした標準形に正規化します。
     *
     * @param s      数値を表す文字列
     * @param locale ロケール。{@literal null}であってはいけません
     * @return 正規化された文字列
     */
    public static String normalize(final String s, final Locale locale) {
        assertArgumentNotNull("locale", locale);

        if (s == null) {
            return null;
        }
        final DecimalFormatSymbols symbols =
                DecimalFormatSymbolsUtil.getDecimalFormatSymbols(locale);
        final char decimalSep = symbols.getDecimalSeparator();
        final char groupingSep = symbols.getGroupingSeparator();
        final StringBuilder buf = new StringBuilder(20);
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == groupingSep) {
                continue;
            } else if (c == decimalSep) {
                c = '.';
            }
            buf.append(c);
        }
        return buf.toString();
    }

}
