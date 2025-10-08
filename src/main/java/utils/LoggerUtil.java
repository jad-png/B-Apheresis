package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private LoggerUtil() {
    }

    // get logger for the called class
    public static Logger getLogger() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[2].getClassName();
        return LoggerFactory.getLogger(className);
    }

    // get logger for specific class
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    // log method entry with params
    public static void logMethodEntry(Logger log, String method, Object... params) {
        if (log.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder("Entering.. ").append(method);
            if (params.length > 0) {
                sb.append(" with parameters: ");
                for (int i = 0; i < params.length; i++) {
                    sb.append("param").append(i + 1).append("=").append(params[i]);
                    if (i < params.length - 1) {
                        sb.append(", ");
                    }
                }
            }
            log.debug(sb.toString());
        }
    }

    /**
     * Log method exit with return value
     */
    public static void logMethodExit(Logger logger, String methodName, Object returnValue) {
        if (logger.isDebugEnabled()) {
            if (returnValue != null) {
                logger.debug("Exiting {} with return value: {}", methodName, returnValue);
            } else {
                logger.debug("Exiting {}", methodName);
            }
        }
    }

    // Log method exit without return value
    public static void logMethodExit(Logger logger, String methodName) {
        if (logger.isDebugEnabled()) {
            logger.debug("Exiting {}", methodName);
        }
    }

    // Log error with context information
    public static void logError(Logger logger, String operation, Throwable throwable) {
        logger.error("Error during {}: {}", operation, throwable.getMessage(), throwable);
    }

    //Log warning with context
    public static void logWarning(Logger logger, String operation, String warning) {
        logger.warn("Warning during {}: {}", operation, warning);
    }


    // Log informational message with context
    public static void logInfo(Logger logger, String operation, String message) {
        logger.info("{}: {}", operation, message);
    }
}
