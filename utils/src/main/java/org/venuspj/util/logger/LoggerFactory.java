package org.venuspj.util.logger;

import org.slf4j.Logger;

public class LoggerFactory {

    public static final Logger getLogger() {
        return org.slf4j.LoggerFactory.getLogger(new Throwable().getStackTrace()[1].getClassName());
    }
}
