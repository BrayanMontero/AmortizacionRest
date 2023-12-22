package com.soa.dto;

import com.google.gson.Gson;

public class RespuestaMain {
    private String mensaje;
    private RespondeTablaAmortizacion respuestaTabla;
    private RespuestaDisp respuestaDisp;

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the respuestaTabla
     */
    public RespondeTablaAmortizacion getRespuestaTabla() {
        return respuestaTabla;
    }

    /**
     * @param respuestaTabla the respuestaTabla to set
     */
    public void setRespuestaTabla(RespondeTablaAmortizacion respuestaTabla) {
        this.respuestaTabla = respuestaTabla;
    }

    /**
     * @return the respuestaDisp
     */
    public RespuestaDisp getRespuestaDisp() {
        return respuestaDisp;
    }

    /**
     * @param respuestaDisp the respuestaDisp to set
     */
    public void setRespuestaDisp(RespuestaDisp respuestaDisp) {
        this.respuestaDisp = respuestaDisp;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
        }

}
