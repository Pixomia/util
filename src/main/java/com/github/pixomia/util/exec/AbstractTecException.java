package com.github.pixomia.util.exec;

public class AbstractTecException extends AbstractPixomiaUtilException {
    private static final long serialVersionUID = 3199218485962096923L;

    public AbstractTecException() {
    }

    public AbstractTecException(final String message) {
	super(message);
    }

    protected AbstractTecException(final String message, final Throwable cause) {
	super(message, cause);
    }

}
