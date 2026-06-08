package com.sarvatra.microservice.helper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogAdaptor {

    private static final int LEVEL_FATAL = 6;
    private static final int LEVEL_ERROR = 5;
    private static final int LEVEL_WARN = 4;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_TRACE = 1;

    private Logger logger;

    public LogAdaptor(String className) {
        logger = LogManager.getLogger(className);
    }

    public void trace(Object o) {
        logger.trace(o);
    }

    public void trace(Object o, Throwable e) {
        logger.trace(o, e);
    }

    public void debug(Object o) {
        logger.debug(o);
    }

    public void debug(Object o, Throwable e) {
        logger.debug(o, e);
    }

    public void debug(String message, Object... objects) {
        logger.debug(message, objects);
    }

    public void info(Object o) {
        logger.info(o);
    }

    public void info(Object o, Throwable e) {
        logger.info(o, e);
    }

    public void info(String message, Object... objects) {
        logger.info(message, objects);
    }

    public void war(String message, Object... objects) {
        logger.info(message, objects);
    }


    public void warn(Object o) {
        logger.warn(o);
    }

    public void warn(Object o, Throwable e) {
        logger.warn(o, e);
    }

    public void error(Object o) {
        logger.error(o);
    }

    public void error(Object o, Throwable e) {
        logger.error(o, e);
    }

    public void fatal(Object o) {
        logger.fatal(o);
    }

    public void fatal(Object o, Throwable e) {
        logger.fatal(o, e);
    }

    public void error(String o, String fileName) {
    }


    public void setLevel(int level) {
        switch (level) {
            case LEVEL_FATAL:
                logger.atLevel(Level.FATAL);
                break;
            case LEVEL_ERROR:
                logger.atLevel(Level.ERROR);
                break;
            case LEVEL_WARN:
                logger.atLevel(Level.WARN);
                break;
            case LEVEL_INFO:
                logger.atLevel(Level.INFO);
                break;
            case LEVEL_DEBUG:
                logger.atLevel(Level.DEBUG);
                break;
            case LEVEL_TRACE:
                logger.atLevel(Level.TRACE);
                break;
            default:
                logger.atLevel(Level.DEBUG);
                break;
        }
    }


}