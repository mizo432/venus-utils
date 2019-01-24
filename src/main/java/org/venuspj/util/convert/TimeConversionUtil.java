package org.venuspj.util.convert;

import org.venuspj.exception.ParseRuntimeException;
import org.venuspj.util.collect.MultiIterator;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;
import static org.venuspj.util.strings2.Strings2.isEmpty;
import static org.venuspj.util.strings2.Strings2.isNotEmpty;

/**
 * タイム用の変換ユーティリティです。
 *
 */
public class TimeConversionUtil {

    /**
     * インスタンスを構築します。
     */
    protected TimeConversionUtil() {
    }

    /**
     * タイムに変換します。
     *
     * @param o
     *            変換したいオブジェクト
     * @return タイム
     */
    public static Time toTime(Object o) {
        return toTime(o, null);
    }

    /**
     * タイムに変換します。
     *
     * @param o
     *            変換したいオブジェクト
     * @param pattern
     *            パターン
     * @return タイム
     */
    public static Time toTime(Object o, String pattern) {
        if (o == null) {
            return null;
        } else if (o instanceof String) {
            return toTime((String) o, pattern);
        } else if (o instanceof Time) {
            return (Time) o;
        } else if (o instanceof Calendar) {
            return new Time(((Calendar) o).getTime().getTime());
        } else {
            return toTime(o.toString(), pattern);
        }
    }

    /**
     * タイムに変換します。
     *
     * @param s
     *            文字列で表現した値
     * @param pattern
     *            パターン
     * @return 変換した値
     */
    public static Time toTime(String s, String pattern) {
        return toTime(s, pattern, Locale.getDefault());
    }

    /**
     * タイムに変換します。
     *
     * @param s
     *            文字列で表現した値
     * @param pattern
     *            パターン
     * @param locale
     *            ロケール
     * @return 変換した値
     */
    public static Time toTime(String s, String pattern, Locale locale) {
        if (isEmpty(s)) {
            return null;
        }
        SimpleDateFormat sdf = getDateFormat(s, pattern, locale);
        try {
            return new Time(sdf.parse(s).getTime());
        } catch (ParseException ex) {
            throw new ParseRuntimeException(ex);
        }
    }

    /**
     * 日付フォーマットを返します。
     *
     * @param s
     *            文字列で表現した値
     * @param pattern
     *            パターン
     * @param locale
     *            ロケール
     * @return 日付フォーマット
     */
    public static SimpleDateFormat getDateFormat(String s, String pattern,
                                                 Locale locale) {
        if (pattern != null) {
            return new SimpleDateFormat(pattern);
        }
        return getDateFormat(s, locale);
    }

    /**
     * 日付フォーマットを返します。
     *
     * @param s
     *            文字列で表現した値
     * @param locale
     *            ロケール
     * @return 日付フォーマット
     */
    public static SimpleDateFormat getDateFormat(String s, Locale locale) {
        String pattern = getPattern(locale);
        if (s.length() == pattern.length()) {
            return new SimpleDateFormat(pattern);
        }
        String shortPattern = convertShortPattern(pattern);
        if (s.length() == shortPattern.length()) {
            return new SimpleDateFormat(shortPattern);
        }
        return new SimpleDateFormat(pattern);
    }

    /**
     * 日付パターンを返します。
     *
     * @param locale
     * @return 日付パターン
     */
    public static String getPattern(Locale locale) {
        return "HH:mm:ss";
    }

    /**
     * 短いパターンに変換します。
     *
     * @param pattern
     *            パターン
     * @return 短いパターン
     */
    public static String convertShortPattern(String pattern) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            if (c == 'h' || c == 'H' || c == 'm' || c == 's') {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    /**
     * オブジェクトを{@link Time}に変換します。
     *
     * @param src
     *            変換元のオブジェクト
     * @return 変換された{@link Time}
     */
    public static Time toSqlTime(final Object src) {
        return toSqlTime(src, null, Locale.getDefault());
    }
    /**
     * オブジェクトを{@link Time}に変換します。
     *
     * @param src
     *            変換元のオブジェクト
     * @param pattern
     *            パターン文字列
     * @param locale
     *            ロケール
     * @return 変換された{@link Time}
     */
    protected static Time toSqlTime(final Object src, final String pattern,
                                    final Locale locale) {
        if (src == null) {
            return null;
        }
        if (src.getClass() == Time.class) {
            return (Time) src;
        }
        if (src instanceof Date) {
            return new Time(((Date) src).getTime());
        }
        if (src instanceof Calendar) {
            return new Time(((Calendar) src).getTimeInMillis());
        }
        final String str = src.toString();
        if (isEmpty(str)) {
            return null;
        }
        if (isNotEmpty(pattern)) {
            final SimpleDateFormat format =
                    new SimpleDateFormat(pattern, locale);
            final Date date = toDate(str, format);
            if (date != null) {
                return new Time(date.getTime());
            }
        }
        final Date date = toDate(str, locale);
        if (date != null) {
            return new Time(date.getTime());
        }
        final Time time = toSqlTimeJdbcEscape(str);
        if (time != null) {
            return time;
        }
        throw new ParseRuntimeException(str);
    }
    /**
     * 文字列を{@link Time}に変換します。
     *
     * @param str
     *            文字列
     * @return 変換された{@link Time}
     */
    protected static Time toSqlTimeJdbcEscape(final String str) {
        try {
            return Time.valueOf(str);
        } catch (final IllegalArgumentException ex) {
            return null;
        }
    }
    /**
     * オブジェクトを{@link Time}に変換します。
     *
     * @param src
     *            変換元のオブジェクト
     * @param pattern
     *            パターン文字列
     * @return 変換された{@link Time}
     */
    public static Time toSqlTime(final Object src, final String pattern) {
        return toSqlTime(src, pattern, Locale.getDefault());
    }

    /**
     * 文字列を{@link Date}に変換します。
     *
     * @param str
     *            文字列
     * @param locale
     *            ロケール
     * @return 変換された{@link Date}
     */
    @SuppressWarnings("unchecked")
    protected static Date toDate(final String str, final Locale locale) {
        for (final DateFormat format : MultiIterator.iterable(
                new DateConversionUtil.DateFormatIterator(locale),
                new DateConversionUtil.PlainDateFormatIterator(str, locale))) {
            if (format == null) {
                continue;
            }
            final Date date = toDate(str, format);
            if (date != null) {
                return date;
            }
        }
        return null;
    }
    /**
     * 文字列を{@link Date}に変換します。
     *
     * @param str
     *            文字列
     * @param format
     *            {@link DateFormat}
     * @return 変換された{@link Date}
     */
    protected static Date toDate(final String str, final DateFormat format) {
        final ParsePosition pos = new ParsePosition(0);
        final Date date = format.parse(str, pos);
        if (date == null) {
            return null;
        }
        final int index = pos.getIndex();
        if (index == 0) {
            return null;
        }
        if (index < str.length()) {
            return null;
        }
        return date;
    }

    /**
     * オブジェクトを{@link Date}に変換します。
     *
     * @param src
     *            変換元のオブジェクト
     * @return 変換された{@link Date}
     */
    public static Date toDate(final Object src) {
        return toDate(src, null, Locale.getDefault());
    }
    /**
     * オブジェクトを{@link Date}に変換します。
     *
     * @param src
     *            変換元のオブジェクト
     * @param pattern
     *            パターン文字列
     * @return 変換された{@link Date}
     */
    public static Date toDate(final Object src, final String pattern) {
        return toDate(src, pattern, Locale.getDefault());
    }
    /**
     * オブジェクトを{@link Date}に変換します。
     *
     * @param src
     *            変換元のオブジェクト
     * @param locale
     *            ロケール。{@literal null}であってはいけません
     * @return 変換された{@link Date}
     */
    public static Date toDate(final Object src, final Locale locale) {
        assertArgumentNotNull("locale", locale);
        return toDate(src, null, locale);
    }
    /**
     * オブジェクトを{@link Date}に変換します。
     *
     * @param src
     *            変換元のオブジェクト
     * @param pattern
     *            パターン文字列
     * @param locale
     *            ロケール
     * @return 変換された{@link Date}
     */
    protected static Date toDate(final Object src, final String pattern,
                                 final Locale locale) {
        if (src == null) {
            return null;
        }
        if (src.getClass() == Date.class) {
            return (Date) src;
        }
        if (src instanceof Date) {
            return new Date(((Date) src).getTime());
        }
        if (src instanceof Calendar) {
            return new Date(((Calendar) src).getTimeInMillis());
        }
        final String str = src.toString();
        if (isEmpty(str)) {
            return null;
        }
        if (isNotEmpty(pattern)) {
            final SimpleDateFormat format =
                    new SimpleDateFormat(pattern, locale);
            final Date date = toDate(str, format);
            if (date != null) {
                return date;
            }
        }
        final Date date = toDate(str, locale);
        if (date != null) {
            return date;
        }
        final Time time = toSqlTimeJdbcEscape(str);
        if (time != null) {
            return new Date(time.getTime());
        }
        throw new ParseRuntimeException(str);
    }
}
