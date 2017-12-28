package org.venuspj.util.validate;

import org.venuspj.util.objects2.Objects2;

/**
 */
public final class Validate {
    public static void notNull(Object obj) {
        if (Objects2.isNull(obj))
            throw new ValidateRuntimeException("obj is null");
    }
}
