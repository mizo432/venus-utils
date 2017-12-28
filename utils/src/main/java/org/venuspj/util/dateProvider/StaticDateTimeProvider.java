package org.venuspj.util.dateProvider;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

/**
 */
public class StaticDateTimeProvider extends DateProvider {
    private static AtomicReference<LocalDateTime> localDateTime = new AtomicReference<LocalDateTime>();

    private StaticDateTimeProvider() {

    }

    public static void initialize(LocalDateTime aLocalDateTime) {
        StaticDateTimeProvider instance = new StaticDateTimeProvider();
        instance.setLocalDateTime(aLocalDateTime);
        DateProvider.setDateProvider(instance);


    }

    private void setLocalDateTime(LocalDateTime aLocalDateTime) {
        StaticDateTimeProvider.localDateTime.set(aLocalDateTime);
    }

    @Override
    protected LocalDateTime now() {
        return localDateTime.get();
    }

}
