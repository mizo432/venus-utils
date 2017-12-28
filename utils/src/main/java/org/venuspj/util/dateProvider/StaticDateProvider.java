package org.venuspj.util.dateProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicReference;

public class StaticDateProvider extends DateProvider {
    private static AtomicReference<LocalDate> localDate = new AtomicReference<>();

    public StaticDateProvider(LocalDate aLocalDate) {
        super();
        StaticDateProvider.localDate.set(aLocalDate);
    }

    public static void initialize(LocalDate aLocalDate) {
        StaticDateProvider instance = new StaticDateProvider(aLocalDate);
        new DateProvider(instance);
    }

    @Override
    protected LocalDateTime now() {
        return LocalDateTime.of(StaticDateProvider.localDate.get(), LocalTime.now());
    }

}
