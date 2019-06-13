package org.venuspj.util.logger;


public class LoggerFactory {

    public static final org.slf4j.Logger getLogger() {
        return org.slf4j.LoggerFactory.getLogger(new Throwable().getStackTrace()[1].getClassName());
    }
}
