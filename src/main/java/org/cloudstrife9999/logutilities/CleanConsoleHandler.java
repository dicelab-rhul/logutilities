package org.cloudstrife9999.logutilities;

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
    public CleanConsoleHandler() {
	super();
	
	setOutputStream(System.out);
    }
}