package org.venuspj.util.validate;

import org.venuspj.util.objects2.Objects2;

public interface NullableSupplier {
    Object get();

    default boolean isNull() {
        return Objects2.isNull(get());

    }
}
