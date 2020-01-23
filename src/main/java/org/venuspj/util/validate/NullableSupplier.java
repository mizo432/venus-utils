package org.venuspj.util.validate;

public interface NullableSupplier {
    Object get();

    default boolean isNull() {
        return org.venuspj.util.objects2.Objects2.isNull(get());

    }
}
