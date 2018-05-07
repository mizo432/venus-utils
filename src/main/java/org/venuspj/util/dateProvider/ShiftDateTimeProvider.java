package org.venuspj.util.dateProvider;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

public class ShiftDateTimeProvider extends DateProvider {
    private static AtomicReference<LocalDateTime> startDateTime = new AtomicReference<>();
    private static AtomicReference<LocalDateTime> localDateTime = new AtomicReference<>();

    public ShiftDateTimeProvider(LocalDateTime aLocalDateTime) {
        super();
        ShiftDateTimeProvider.localDateTime.set(aLocalDateTime);
        ShiftDateTimeProvider.startDateTime.set(LocalDateTime.now());
    }

    public static void initialize(LocalDateTime aLocalDateTime) {
        ShiftDateTimeProvider instance = new ShiftDateTimeProvider(aLocalDateTime);
        new DateProvider(instance);
    }

    @Override
    protected LocalDateTime now() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return ShiftDateTimeProvider
                .localDateTime.get()
                .plusNanos(Duration.between(ShiftDateTimeProvider.startDateTime.get(), currentDateTime).toNanos());
    }

}
