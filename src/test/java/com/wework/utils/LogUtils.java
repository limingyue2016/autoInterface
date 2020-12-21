package com.wework.utils;

import org.apache.log4j.Logger;

public class LogUtils {
    private volatile static LogUtils instance;
    private static Logger logger;
    public static final String TAG = "TEST";

    private LogUtils() {
        logger = getLogger();
    }

    public static LogUtils newInstance() {
        if (instance == null) {
            synchronized (LogUtils.class) {
                if (instance == null) {
                    instance = new LogUtils();
                }
            }
        }
        return instance;
    }

    private Logger getLogger() {
        return Logger.getLogger(TAG);
    }

    public void i(Object msg) {
        logger.info(msg);
    }

    public void d(Object msg) {
        logger.debug(msg);
    }

    public void w(Object msg) {
        logger.warn(msg);
    }

    public void e(Object msg) {
        logger.error(msg);
    }
}
