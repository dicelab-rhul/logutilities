package org.cloudstrife9999.logutilities;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * All-purposes utility class for logging messages and {@link Exception}s.
 * 
 * @author cloudstrife9999
 *
 */
public class LogUtils {
    private static final Logger LOGGER = initLogger(LogUtils.OUT);
    private static final Logger ERROR_LOGGER = initLogger(LogUtils.ERR);
    private static boolean verbose;
    private static final String OUT = "out";
    private static final String ERR = "err";
    
    private LogUtils() {}

    private static Logger initLogger(String printWriterName) {
	Logger logger = Logger.getGlobal();
	logger.setUseParentHandlers(false);
	CleanConsoleHandler handler = new CleanConsoleHandler(printWriterName);
	handler.setFormatter(new CleanLogFormatter());
	logger.addHandler(handler);
	enableVerbose();
	
	return logger;
    }

    /**
     * 
     * Makes this logger able to print incoming lines. Also reverses the effect of {@link #disableVerbose()}.
     * 
     */
    public static void enableVerbose() {
	LogUtils.verbose = true;
    }
    
    /**
     * 
     * Makes the logger suppress any incoming line (i.e., nothing will ever be printed until {@link #enableVerbose()} is called).
     * 
     */
    public static void disableVerbose() {
	LogUtils.verbose = false;
    }
    
    /**
     * 
     * Logs a message of type <code>T</code> to the INFO level.
     * 
     * @param message the message to log.
     * 
     */
    public static <T> void log(T message) {
	log(message.toString());
    }
    
    /**
     * 
     * Logs a {@link String} message to the INFO level.
     * 
     * @param message the message to log.
     * 
     */
    public static void log(String message) {
	log(Level.INFO, message);
    }

    /**
     * 
     * Logs an {@link Exception} to the SEVERE level.<br /><br />
     * The {@link Exception} will be always logged on the <code>SEVERE</code> level, and will be logged regardless of the level of verbosity.
     * 
     * @param e the {@link Exception} to log.
     * 
     */
    public static synchronized void log(Exception e) {
	ExceptionsStringBuilder2D builder = new ExceptionsStringBuilder2D(e);
	forceExceptionLog(Level.SEVERE, builder.toString());
    }

    /**
     * 
     * Logs an {@link Exception} to the SEVERE level. A relevant class is logged as well.
     * 
     * @param e the {@link Exception} to log.
     * @param className the relevant class {@link String} name to log.
     * 
     */
    public static void log(Exception e, String className) {
	if (e.getMessage() != null) {
	    log(className + ": " + e.getMessage(), e);
	}
	else {
	    log(className + ": " + e.getClass().getSimpleName(), e);
	}
    }

    /**
     * 
     * Eats the exception by not logging it.
     * 
     * @param e the {@link Exception} to ignore.
     * 
     */
    public static void fakeLog(Exception e) {
	// this exception does not need to be logged
    }

    /**
     * 
     * Logs an {@link Exception} with a {@link String} message to the SEVERE level.
     * 
     * @param message the {@link String} message to log alongside with the {@link Exception}.
     * @param e the {@link Exception} to log.
     * 
     */
    public static void log(String message, Exception e) {
	log(Level.SEVERE, e.getClass().getCanonicalName() + ": " + message, e);
    }

    /**
     * 
     * Logs a {@link String} message  to the specified level.
     * 
     * @param level the {@link Level} to log the message to.
     * @param message the message to log.
     * 
     */
    public static void log(Level level, String message) {
	if(LogUtils.verbose) {
	    forceLog(level, message);
	}
    }
    
    private static void forceLog(Level level, String message) {
	LOGGER.log(level, () -> message);
    }
    
    private static void forceExceptionLog(Level level, String message) {
	ERROR_LOGGER.log(level, () -> message);
    }

    /**
     * 
     * Logs an {@link Exception} with a {@link String} message to the specified level.
     * 
     * @param level the {@link Level} to log to.
     * @param message the {@link String} message to log alongside with the {@link Exception}.
     * @param e the {@link Exception} to log.
     * 
     */
    public static void log(Level level, String message, Exception e) {
	if(LogUtils.verbose) {
	    LOGGER.log(level, message, e);
	}
    }

    /**
     * 
     * Logs a {@link String} message and a relevant class to the INFO level.
     * 
     * @param source the relevant class {@link String} name to log.
     * @param message the {@link String} message to log.
     * 
     */
    public static void logWithClass(String source, String message) {
	log(source + ": " + message);
    }

    /**
     * 
     * Logs a particular {@link String} message to the INFO level with 2 newlines before and 2 newlines after.
     * 
     * @param s the {@link String} message to log.
     * 
     */
    public static void logState(String s) {
	log("\n\n" + s + "\n\n");
    }
}