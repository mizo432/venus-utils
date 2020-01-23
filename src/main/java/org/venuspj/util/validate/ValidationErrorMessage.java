package org.venuspj.util.validate;

import java.util.function.Supplier;

public interface ValidationErrorMessage {

    /**
     *
     * @param errorMessage
     * @return
     */
    Validation errorMessage(Supplier<String> errorMessage);

}
