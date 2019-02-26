package org.venuspj.util.text;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

import static org.venuspj.util.collect.CollectionsUtil.newConcurrentHashMap;
import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;

/**
 * {@link DecimalFormatSymbols}用のユーティリティクラスです。
 */
public abstract class DecimalFormatSymbolsUtil {

    private static final Map<Locale, DecimalFormatSymbols> CACHE =
            newConcurrentHashMap();

    /**
     * {@link DecimalFormatSymbols}を返します。
     *
     * @return {@link DecimalFormatSymbols}
     */
    public static DecimalFormatSymbols getDecimalFormatSymbols() {
        return getDecimalFormatSymbols(Locale.getDefault());
    }

    /**
     * {@link DecimalFormatSymbols}を返します。
     *
     * @param locale ロケール。{@literal null}であってはいけません
     * @return {@link DecimalFormatSymbols}
     */
    public static DecimalFormatSymbols getDecimalFormatSymbols(
            final Locale locale) {
        assertArgumentNotNull("locale", locale);

        DecimalFormatSymbols symbols = CACHE.get(locale);
        if (symbols == null) {
            symbols = new DecimalFormatSymbols(locale);
            CACHE.put(locale, symbols);
        }
        return symbols;
    }

}
