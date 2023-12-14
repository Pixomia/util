package com.github.pixomia.util.exec;

public class NonRecoverTechnicException extends AbstractTecException {
    private static final long serialVersionUID = -2997295048283433131L;

    public NonRecoverTechnicException(final String message) {

	super(message);
    }

    public NonRecoverTechnicException(final String message, final Throwable cause) {

	super(message, cause);
    }

    public NonRecoverTechnicException(final Throwable e) {
	super(e);
    }
}
