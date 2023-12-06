/**
 * Copyright 2020 HITSS. All rights reserved.
 */
package com.soa.commons;

import java.io.PrintWriter;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Excepción general.
 *
 * @author tlopez.
 *
 */
public class AppException extends RuntimeException {

    /**
     * Serial Id.
     */
    private static final long serialVersionUID = 8017396433760332670L;

    /** Código de excepción. */
    private String code;

    /** Status de excepción. */
    private String status;

    /**
     * Null constructor.
     */
    public AppException() {
        super();
    }

    /**
     * Constructor.
     *
     * <code>null</code> otherwise.
     *
     * @param message Exception description.
     */
    public AppException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param code Código de la excepción.
     * @param message Exception description.
     */
    public AppException(final String code, final String message) {
        super(message);
        this.code = code;
    }

    /**
     * Constructor.
     * @param code Código de la excepción.
     * @param message Exception description.
     * @param status Status.
     */
    public AppException(final String code, final String message, 
            final String status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    /**
     * Constructor.
     *
     * <code>null</code> otherwise.
     *
     * @param cause Original cause.
     */
    public AppException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructor.
     *
     * <code>null</code> otherwise.
     *
     * @param message Exception description.
     * @param cause Original cause.
     */
    public AppException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the code
     */
    public final String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public final void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the status
     */
    public final String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public final void setStatus(String status) {
        this.status = status;
    }

    /**
     * Printable representation error.
     *
     * @param aThrowable Exception.
     * @return Description exception.
     */
    public static final String getStackTrace(final Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
}
