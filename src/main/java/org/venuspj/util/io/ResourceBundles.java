package org.venuspj.util.io;

import java.util.*;

import static org.venuspj.util.collect.Maps2.newHashMap;
import static org.venuspj.util.misc.Assertions.assertArgumentNotEmpty;
import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;

/**
 * {@link ResourceBundle}用のユーティリティクラスです。
 */
public abstract class ResourceBundles {

    /**
     * バンドルを返します。 見つからない場合は、<code>null</code>を返します。
     *
     * @param name リソースバンドの名前。{@literal null}や空文字列であってはいけません
     * @return {@link ResourceBundle}
     * @see ResourceBundle#getBundle(String)
     */
    public static final ResourceBundle getBundle(final String name) {
        assertArgumentNotEmpty("name", name);

        try {
            return ResourceBundle.getBundle(name);
        } catch (final MissingResourceException ignore) {
            return null;
        }
    }

    /**
     * バンドルを返します。 見つからない場合は、<code>null</code>を返します。
     *
     * @param name   リソースバンドの名前。{@literal null}や空文字列であってはいけません
     * @param locale ロケール
     * @return {@link ResourceBundle}
     * @see ResourceBundle#getBundle(String, Locale)
     */
    public static final ResourceBundle getBundle(final String name,
                                                 final Locale locale) {
        assertArgumentNotEmpty("name", name);

        try {
            return ResourceBundle.getBundle(name, getLocale(locale));
        } catch (final MissingResourceException ignore) {
            return null;
        }
    }

    /**
     * バンドルを返します。 見つからない場合は、<code>null</code>を返します。
     *
     * @param name        リソースバンドルの名前。{@literal null}や空文字列であってはいけません
     * @param locale      ロケール
     * @param classLoader クラスローダ。{@literal null}や空文字列であってはいけません
     * @return {@link ResourceBundle}
     * @see ResourceBundle#getBundle(String, Locale, ClassLoader)
     */
    public static final ResourceBundle getBundle(final String name,
                                                 final Locale locale, final ClassLoader classLoader) {
        assertArgumentNotNull("name", name);
        assertArgumentNotNull("classLoader", classLoader);

        try {
            return ResourceBundle.getBundle(
                    name,
                    getLocale(locale),
                    classLoader);
        } catch (final MissingResourceException ignore) {
            return null;
        }
    }

    /**
     * リソースバンドルから指定されたキーの文字列を返します。
     *
     * @param bundle リソースバンドル。{@literal null}や空文字列であってはいけません
     * @param key    キー
     * @return 指定されたキーの文字列。{@literal null}や空文字列であってはいけません
     * @see ResourceBundle#getString(String)
     */
    public static String getString(final ResourceBundle bundle, final String key) {
        assertArgumentNotNull("bundle", bundle);
        assertArgumentNotEmpty("key", key);

        try {
            return bundle.getString(key);
        } catch (final Throwable t) {
            return null;
        }
    }

    /**
     * リソースバンドルを{@link Map}に変換します。
     *
     * @param bundle リソースバンドル。{@literal null}であってはいけません
     * @return {@link Map}
     */
    public static final Map<String, String> convertMap(
            final ResourceBundle bundle) {
        assertArgumentNotNull("bundle", bundle);

        final Map<String, String> ret = newHashMap();
        for (final Enumeration<String> e = bundle.getKeys(); e
                .hasMoreElements(); ) {
            final String key = e.nextElement();
            final String value = bundle.getString(key);
            ret.put(key, value);
        }
        return ret;
    }

    /**
     * リソースバンドルを{@link Map}に変換して返します。
     *
     * @param name   リソースバンドルの名前。{@literal null}や空文字列であってはいけません
     * @param locale ロケール
     * @return {@link Map}
     */
    public static final Map<String, String> convertMap(final String name,
                                                       final Locale locale) {
        assertArgumentNotEmpty("name", name);

        final ResourceBundle bundle = getBundle(name, locale);
        return convertMap(bundle);
    }

    /**
     * {@literal locale}が{@literal null}でなければ{@literal locale}を、{@literal null}
     * ならデフォルトのロケールを返します。
     *
     * @param locale ロケール
     * @return {@literal locale}が{@literal null}でなければ{@literal locale}を、
     * {@literal null}ならデフォルトのロケールを返します。
     */
    protected static Locale getLocale(Locale locale) {
        if (locale != null) {
            return locale;
        }
        return Locale.getDefault();
    }

}
