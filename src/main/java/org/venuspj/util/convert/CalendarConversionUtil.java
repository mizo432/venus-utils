package org.venuspj.util.convert;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static org.venuspj.util.misc.Assertions.assertArgumentNotNull;

/**
 * {@link Calendar}用の変換ユーティリティです。
 */
public abstract class CalendarConversionUtil {

    /**
     * {@link Calendar}に変換します。
     *
     * @param o 変換元のオブジェクト
     * @return 変換された{@link Calendar}
     */
    public static Calendar toCalendar(final Object o) {
        return toCalendar(o, null);
    }

    /**
     * {@link Calendar}に変換します。
     *
     * @param o       変換元のオブジェクト
     * @param pattern パターン文字列
     * @return 変換された{@link Calendar}
     */
    public static Calendar toCalendar(final Object o, final String pattern) {
        if (o instanceof Calendar) {
            return (Calendar) o;
        }
        final java.util.Date date = DateConversionUtil.toDate(o, pattern);
        if (date != null) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        }
        return null;
    }

    /**
     * ローカルの{@link TimeZone}と{@link Locale}をもつ{@link Calendar}に変換します。
     *
     * @param calendar {@link Calendar}
     * @return 変換された{@link Calendar}
     */
    public static Calendar localize(final Calendar calendar) {
        assertArgumentNotNull("calendar", calendar);
        final Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTimeInMillis(calendar.getTimeInMillis());
        return localCalendar;
    }

}
