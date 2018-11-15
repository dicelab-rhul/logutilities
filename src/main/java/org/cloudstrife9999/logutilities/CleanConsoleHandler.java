package org.cloudstrife9999.logutilities;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.logging.ConsoleHandler;

/**
 * 
 * A {@link ConsoleHandler} sub-class which logs to stdout rather than stderr.
 * 
 * @author cloudstrife9999
 *
 */
public class CleanConsoleHandler extends ConsoleHandler {

    /**
     * 
     * Calls super() and sets stdout as output stream.
     * 
     */
    public CleanConsoleHandler(String fieldName) {
	super();
	
	init(fieldName);
    }

    private void init(String fieldName) {
	try { 
	    Field stream = Class.forName("java.lang.System").getDeclaredField(fieldName);
	    
	    setOutputStream((PrintStream) stream.get(stream));
	}
	catch(Exception e) {
	    LogUtils.fakeLog(e);
	}
    }
}