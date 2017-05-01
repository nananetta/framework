package com.konkow.framework.utils;

import org.apache.commons.lang.StringUtils;

public final class ExceptionUtils {
    
    private ExceptionUtils() {
    }

    /**
     * Wraps exception with {@link RuntimeException} if the given throwable is checked exception .
     *
     * @param throwable
     *            throwable
     *
     * @return unchecked exception;
     */
    public static RuntimeException wrap(Throwable throwable) {
        return wrap(null, throwable);
    }

    /**
     * Wraps exception with {@link RuntimeException} if the given throwable is checked exception.
     *
     * @param message
     *            message
     * @param throwable
     *            throwable
     *
     * @return unchecked exception;
     */
    public static RuntimeException wrap(String message, Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        } else if (StringUtils.isNotBlank(message)) {
            return new RuntimeException(message, throwable);
        } else {
            return new RuntimeException(throwable);
        }
    }
}
