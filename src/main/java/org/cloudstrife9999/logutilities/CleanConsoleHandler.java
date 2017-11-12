package org.cloudstrife9999.logutilities;

import java.util.logging.ConsoleHandler;

public class CleanConsoleHandler extends ConsoleHandler {

    public CleanConsoleHandler() {
	super();
	
	setOutputStream(System.out);
    }
}