package com.soa.dto;

import java.util.List;

public class RespuestaTablaActualizar {

    private List<TablaAmortizacionDto> pagos;
    private String message;

    /**
     * @return the pagos
     */
    public List<TablaAmortizacionDto> getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(List<TablaAmortizacionDto> pagos) {
        this.pagos = pagos;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
