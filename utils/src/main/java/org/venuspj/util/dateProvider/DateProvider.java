package org.venuspj.util.dateProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.concurrent.atomic.AtomicReference;

/**
 */
public class DateProvider {

    private static AtomicReference<DateProvider> dateProvider = new AtomicReference<>(new DateProvider());

    DateProvider() {

    }

    protected DateProvider(DateProvider aDateProvider) {
        DateProvider.setDateProvider(aDateProvider);
    }

    public static void setDateProvider(DateProvider aDateProvider) {
        DateProvider.dateProvider.set(aDateProvider);
    }

    public static LocalDateTime currentDateTime() {
        return DateProvider.dateProvider.get().now();
    }

    public static LocalDate currentDate() {
        return currentDateTime().toLocalDate();
    }

    public static LocalTime currentTime() {
        return currentDateTime().toLocalTime();
    }

    public static YearMonth currentYearMonth() {
        LocalDateTime currentDateTime = currentDateTime();
        return YearMonth.of(currentDateTime.getYear(), currentDateTime.getMonth());
    }

    protected LocalDateTime now() {
        return LocalDateTime.now();
    }

}
