package com.soa.dto;

public class RequestTablaActualizar {
    private String num_tarjeta;
    private Integer mes;
    private double pago;
    /**
     * @return the num_tarjeta
     */
    public String getNum_tarjeta() {
        return num_tarjeta;
    }
    /**
     * @param num_tarjeta the num_tarjeta to set
     */
    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }
    /**
     * @return the mes
     */
    public Integer getMes() {
        return mes;
    }
    /**
     * @param mes the mes to set
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    /**
     * @return the pago
     */
    public double getPago() {
        return pago;
    }
    /**
     * @param pago the pago to set
     */
    public void setPago(double pago) {
        this.pago = pago;
    }

}
