package com.github.pixomia.util.exec;

public abstract class AbstractPixomiaUtilException extends Exception {
    private static final long serialVersionUID = -4378818480849782375L;

    protected AbstractPixomiaUtilException() {
	super(null, null, true, false);
    }

    protected AbstractPixomiaUtilException(final String message) {
	super(message, null, true, false);
    }

    protected AbstractPixomiaUtilException(final String message, final Throwable cause) {
	super(message, cause, true, false);
    }

    protected AbstractPixomiaUtilException(final Throwable cause) {
	super("", cause, true, false);
    }
}
