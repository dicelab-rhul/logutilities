package org.cloudstrife9999.logutilities;

public class ExceptionsStringBuilder2D extends AbstractStringBuilder2D<Exception> {
    private int length;
    private static final int DEFAULT_LENGTH = 50;
    
    public ExceptionsStringBuilder2D(Exception exception, int lineLength) {
	super(exception);
	
	this.length = lineLength;
	
	initBuilder();
    }
    
    public ExceptionsStringBuilder2D(Exception exception) {
	this(exception, ExceptionsStringBuilder2D.DEFAULT_LENGTH);
    }
    
    private void initBuilder() {
	appendHeader();
	appendMessage(getOriginalObject());
	appendStackTrace(getOriginalObject());
	appendCause(getOriginalObject());
	appendFooter();
    }

    private void appendCause(Throwable throwable) {
	appendLineOfCharacter('-');
	appendCharacter('\n', 1);
	getBuilder().append("Cause: ");
	appendVerboseCause(throwable);
    }
    
    private void appendLineOfCharacter(char c) {
	for(int i = 0; i < this.length; i++) {
	    getBuilder().append(c);
	}
	
	getBuilder().append('\n');
    }

    private void appendVerboseCause(Throwable throwable) {
	Throwable cause = throwable.getCause();
	
	if(cause != null) {
	    getBuilder().append(cause.getClass().getSimpleName());
	    appendMessage(cause);
	    appendStackTrace(cause);
	    appendCause(cause);
	}
	else {
	    getBuilder().append("___ no parent exception found ___\n\n");
	}
    }

    private void appendFooter() {
	appendLineOfCharacter('#');
	appendEndOfException();
	appendLineOfCharacter('#');
	appendCharacter('\n', 1);
    }

    private void appendHeader() {
	appendCharacter('\n', 2);
	appendLineOfCharacter('#');
	appendLineOfElements("EXCEPTION!!!", "-->", getOriginalObject().getClass().getSimpleName());
	appendLineOfCharacter('#');
    }
    
    private void appendCharacter(char c, int times) {
	int counter = times;
	
	while(counter > 0) {
	    getBuilder().append(c);
	    counter--;
	}
    }

    private void appendEndOfException() {
	String temp = "# END OF THE EXCEPTION!!!";
	int remainingWhites = this.length - temp.length() - 1;
	
	getBuilder().append(temp);
	appendCharacter(' ', remainingWhites);
	
	getBuilder().append("#\n");
    }

    private void appendMessage(Throwable throwable) {
	getBuilder().append("\nMessage: ");

	String message = retrieveMessage(throwable);
	
	getBuilder().append(message);
	getBuilder().append("\n\n");
    }

    private String retrieveMessage(Throwable throwable) {
	String temp = throwable.getMessage();
	
	if(temp == null) {
	    return "___ this exception has no message ___";
	}
	else if(getOriginalObject().getCause() == null) {
	    return temp;
	}
	else {
	    return parseMessage(temp, throwable);
	}
    }

    private String parseMessage(String candidate, Throwable throwable) {
	try {
	    Throwable cause = throwable.getCause();
	    String[] tokens = candidate.split(": ");
	    
	    if(tokens.length > 1 && Class.forName(tokens[0]).isAssignableFrom(cause.getClass())) {
		return "___ this exception has no message ___";
	    }
	    else {
		return candidate;
	    }
	}
	catch(Exception e) {
	    return candidate;
	}
    }

    private void appendStackTrace(Throwable throwable) {
	getBuilder().append("Stack trace (bottom-up):\n");
	
	for (StackTraceElement elm : throwable.getStackTrace()) {
	    getBuilder().append("at ");
	    getBuilder().append(elm.toString());
	    getBuilder().append("\n");
	}
	
	getBuilder().append("\n");
    }
    
    private void appendLineOfElements(String... elements) {
	int trailingWhiteSpaces = calculateTrailingWhiteSpaces(elements);
	
	getBuilder().append('#');
	
	for (String elm: elements) {
	    getBuilder().append(' ');
	    getBuilder().append(elm);
	}
	
	appendCharacter(' ', trailingWhiteSpaces);
	
	getBuilder().append("#\n");
    }

    private int calculateTrailingWhiteSpaces(String... elements) {
	int count = 2 + (elements == null ? 0 : elements.length); //The initial '#' + 1 white space before each element + the ending '#'.
	
	for (String elm: elements) {
	    count += elm.length();
	}
	
	return Math.max(this.length - count, 0);
    }

    @Override
    public String toString() {
        return getBuilder().toString();
    }
}