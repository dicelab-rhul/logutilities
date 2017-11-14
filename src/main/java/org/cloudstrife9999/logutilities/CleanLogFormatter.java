package org.cloudstrife9999.logutilities;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * 
 * A sub-class of {@link SimpleFormatter} which formats the {@link LogRecord} by simply retrieving the message and appending a newline character.
 * 
 * @author cloudstrife9999
 *
 */
public class CleanLogFormatter extends SimpleFormatter {

    /**
     * 
     * Formats the <code>record</code> by simply retrieving the message and appending a newline character.
     * 
     * @param record the {@link LogRecord} to format.
     * 
     */
    @Override
    public String format(LogRecord record) {
	return record.getMessage() + "\n";
    }
}