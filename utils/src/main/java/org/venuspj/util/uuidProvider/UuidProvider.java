package org.venuspj.util.uuidProvider;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 */
public class UuidProvider {

    private static AtomicReference<UuidProvider> uuidProvider = new AtomicReference<>(new UuidProvider());

    UuidProvider() {

    }

    protected UuidProvider(UuidProvider aUuidProvider) {
        setUuidProvider(aUuidProvider);
    }

    void setUuidProvider(UuidProvider aUuidProvider) {
        UuidProvider.uuidProvider.set(aUuidProvider);
    }

    public static UUID randomUUID() {
        return UuidProvider.uuidProvider.get().uuid();
    }

    protected UUID uuid() {
        return UUID.randomUUID();
    }

}
