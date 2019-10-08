package org.venuspj.util.uuidProvider;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
public class UuidProvider {

    private static AtomicReference<UuidProvider> uuidProvider = new AtomicReference<>(new UuidProvider());

    UuidProvider() {

    }

    protected UuidProvider(UuidProvider uuidProvider) {
        setUuidProvider(uuidProvider);

    }

    void setUuidProvider(UuidProvider uuidProvider) {
        UuidProvider.uuidProvider.set(uuidProvider);

    }

    public static UUID randomUUID() {
        return UuidProvider.uuidProvider.get().uuid();

    }

    protected UUID uuid() {
        return UUID.randomUUID();

    }

}
