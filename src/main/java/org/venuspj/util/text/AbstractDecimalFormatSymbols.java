package org.venuspj.util.text;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

import static org.venuspj.util.collect.Collections3.newConcurrentHashMap;
import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;

/**
 * {@link java.text.DecimalFormatSymbols}用のユーティリティクラスです。
 */
public abstract class AbstractDecimalFormatSymbols {

    private static final Map<Locale, java.text.DecimalFormatSymbols> CACHE =
            newConcurrentHashMap();

    /**
     * {@link java.text.DecimalFormatSymbols}を返します。
     *
     * @return {@link java.text.DecimalFormatSymbols}
     */
    public static java.text.DecimalFormatSymbols getDecimalFormatSymbols() {
        return getDecimalFormatSymbols(Locale.getDefault());
    }

    /**
     * {@link java.text.DecimalFormatSymbols}を返します。
     *
     * @param locale ロケール。{@literal null}であってはいけません
     * @return {@link java.text.DecimalFormatSymbols}
     */
    public static java.text.DecimalFormatSymbols getDecimalFormatSymbols(
            final Locale locale) {
        assertArgumentNotNull("locale", locale);

        java.text.DecimalFormatSymbols symbols = CACHE.get(locale);
        if (symbols == null) {
            symbols = new java.text.DecimalFormatSymbols(locale);
            CACHE.put(locale, symbols);
        }
        return symbols;
    }
/**
 * システム 開発費
 *         技術的夫妻
 */
}
