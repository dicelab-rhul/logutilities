package org.cloudstrife9999.logutilities;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class CleanLogFormatter extends SimpleFormatter {

    @Override
    public String format(LogRecord record) {
	return record.getMessage() + "\n";
    }
}