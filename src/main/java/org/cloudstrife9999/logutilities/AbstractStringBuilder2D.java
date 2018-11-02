package org.cloudstrife9999.logutilities;

public abstract class AbstractStringBuilder2D<T> implements StringBuilder2D<T> {
    private T originalObject;
    private StringBuilder builder;
    
    public AbstractStringBuilder2D(T object) {
	this.originalObject = object;
	this.builder = new StringBuilder();
    }
    
    @Override
    public T getOriginalObject() {
	return this.originalObject;
    }
    
    protected StringBuilder getBuilder() {
	return this.builder;
    }
}