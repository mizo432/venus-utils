package org.venuspj.util.validate;

import static org.venuspj.util.objects2.Objects2.isNull;

/**
 *
 */
public final class Validate {
    public static void notNull(Object obj) {
        if (isNull(obj))
            throw new ValidateRuntimeException("obj is null");
    }
}
