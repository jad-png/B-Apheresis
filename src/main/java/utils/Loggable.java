package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loggable {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected void logDebug(String msg, Object... args) {
        if (log.isDebugEnabled()) {
            log.debug(msg, args);
        }
    }

    protected void logInfo(String msg, Object... args) {
        log.info(msg, args);
    }

    protected void logWarn(String message, Object... args) {
        log.warn(message, args);
    }

    protected void logError(String message, Object... args) {
        log.error(message, args);
    }

    protected void logError(String message, Throwable throwable) {
        log.error(message, throwable);
    }

    protected void logMethodEntry(String methodName, Object... params) {
        LoggerUtil.logMethodEntry(log, methodName, params);
    }

    protected void logMethodExit(String methodName, Object returnValue) {
        LoggerUtil.logMethodExit(log, methodName, returnValue);
    }

    protected void logMethodExit(String methodName) {
        LoggerUtil.logMethodExit(log, methodName);
    }
}
