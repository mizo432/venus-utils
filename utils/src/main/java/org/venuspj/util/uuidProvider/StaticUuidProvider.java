package org.venuspj.util.uuidProvider;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 */
public final class StaticUuidProvider extends UuidProvider {

    static AtomicReference<String> uuidValue = new AtomicReference<>();


    public static void initialise(String anUuidValue) {
        StaticUuidProvider.uuidValue.set(anUuidValue);
        StaticUuidProvider instance = new StaticUuidProvider();
        new UuidProvider(instance);

    }

    protected UUID uuid() {
        return UUID.fromString(uuidValue.get());
    }

}
