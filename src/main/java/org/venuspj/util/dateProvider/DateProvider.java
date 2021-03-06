package org.venuspj.util.dateProvider;

import java.time.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
public class DateProvider {

    private static AtomicReference<DateProvider> dateProvider = new AtomicReference<>(new DateProvider());

    DateProvider() {

    }

    protected DateProvider(DateProvider dateProvider) {
        DateProvider.setDateProvider(dateProvider);
    }

    public static void setDateProvider(DateProvider aDateProvider) {
        DateProvider.dateProvider.set(aDateProvider);
    }

    public static void initialize(){
        DateProvider.dateProvider.set(new DateProvider());

    }

    public static LocalDateTime currentLocalDateTime() {
        return DateProvider.dateProvider.get().now();
    }

    public static Date currentDate(){
        ZonedDateTime zdt = currentLocalDateTime().atZone(ZoneId.systemDefault());
        return  Date.from(zdt.toInstant());

    }

    public static LocalDate currentLocalDate() {
        return currentLocalDateTime().toLocalDate();
    }

    public static LocalTime currentLocalTime() {
        return currentLocalDateTime().toLocalTime();
    }

    public static YearMonth currentYearMonth() {
        LocalDateTime currentDateTime = currentLocalDateTime();
        return YearMonth.of(currentDateTime.getYear(), currentDateTime.getMonth());
    }

    protected LocalDateTime now() {
        return LocalDateTime.now();
    }

}
