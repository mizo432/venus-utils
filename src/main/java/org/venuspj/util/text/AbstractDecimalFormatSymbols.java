package org.venuspj.util.text;

import static org.venuspj.util.collect.Collections3.newConcurrentHashMap;

import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.venuspj.util.base.Preconditions;
import org.venuspj.util.exception.NullArgumentException;

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
      @NotNull final Locale locale) {
    Preconditions.checkNotNull(locale, () -> new NullArgumentException("locale"));

    java.text.DecimalFormatSymbols symbols = CACHE.get(locale);
    if (symbols == null) {
      symbols = new java.text.DecimalFormatSymbols(locale);
      CACHE.put(locale, symbols);
    }
    return symbols;
  }
  
}
