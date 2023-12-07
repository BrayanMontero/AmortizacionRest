/**
 * 
 */
package com.soa.commons;

import org.apache.logging.log4j.ThreadContext;

/**
 * 
 */
public class LogConfiguration {

    /** Elemento fileName del contexto. */
    private static final String FILE_NAME = "filename";

    /** Nombre de la bitacora. */
    private static final String FILE_NAME_VALUE = "c:\\logs\\amortizacion.log";

    /** Elemento component del contexto. */
    private static final String COMPONENT = "component";

    /** Nombre del componente. */
    private static final String COMPONENT_VALUE = "ServicioAmortizacion";

    /** Elemento transactionId del contexto. */
    private static final String TRANSACTION_ID = "transactionId";

    /**
     * Inicializacion de log.
     * @param transactionId Id de la transaccion unico.
     */
    public static void initLog(String transactionId) {
        ThreadContext.put(FILE_NAME, FILE_NAME_VALUE);
        ThreadContext.put(COMPONENT, COMPONENT_VALUE);
        ThreadContext.put(TRANSACTION_ID, transactionId);
    }
}
