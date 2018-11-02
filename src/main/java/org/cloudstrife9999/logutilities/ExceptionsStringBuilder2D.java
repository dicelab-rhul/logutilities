package org.cloudstrife9999.logutilities;

public class ExceptionsStringBuilder2D extends AbstractStringBuilder2D<Exception> {
    private int length;
    
    public ExceptionsStringBuilder2D(Exception exception) {
	super(exception);
	
	this.length = 50;
	
	initBuilder();
    }
    
    private void initBuilder() {
	appendHeader();
	appendMessage();
	appendStackTrace();
	appendFooter();
    }

    private void appendFooter() {
	appendLineOfSharps();
	appendEndOfException();
	appendLineOfSharps();
	appendCharacter('\n', 1);
    }

    private void appendHeader() {
	appendCharacter('\n', 2);
	appendLineOfSharps();
	appendLineOfElements("EXCEPTION!!!", "-->", getOriginalObject().getClass().getSimpleName());
	appendLineOfSharps();
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

    private void appendMessage() {
	getBuilder().append("\nMessage: ");
	String message = getOriginalObject().getMessage();
	
	if(message == null) {
	    message = "___ this exception has no message ___";
	}
	
	getBuilder().append(message);
	getBuilder().append("\n\n");
    }

    private void appendStackTrace() {
	getBuilder().append("Stack trace:\n");
	
	for (StackTraceElement elm : getOriginalObject().getStackTrace()) {
	    getBuilder().append(elm.toString());
	    getBuilder().append("\n\n");
	}
    }
    
    private void appendLineOfSharps() {
	for(int i = 0; i < this.length; i++) {
	    getBuilder().append("#");
	}
	
	getBuilder().append('\n');
    }
    
    private void appendLineOfElements(String... elements) {
	int count = 0;
	
	getBuilder().append('#');
	count++;
	
	for (String elm: elements) {
	    getBuilder().append(' ');
	    getBuilder().append(elm);
	    
	    count += elm.length() + 1;
	}
	
	int remainingWhites = this.length - count - 1;
	appendCharacter(' ', remainingWhites);
	
	getBuilder().append("#\n");
    }

    @Override
    public String toString() {
        return getBuilder().toString();
    }
}