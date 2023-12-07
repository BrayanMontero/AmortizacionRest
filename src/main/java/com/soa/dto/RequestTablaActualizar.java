package com.soa.dto;

public class RequestTablaActualizar {
    private String rfc;
    private Integer mes;
    private double pago;
    /**
     * @return the num_tarjeta
     */
  
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
    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }
    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

}
