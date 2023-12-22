package com.soa.dto;

public class TablaAmortizacionDto {
    private int periodo;
    private double pago;
    private double interes;
    private double capital;
    private double balance;
    private double totalInterest;
    private String rfc;
    /**
     * @return the periodo
     */
    public int getPeriodo() {
        return periodo;
    }
    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
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
     * @return the interes
     */
    public double getInteres() {
        return interes;
    }
    /**
     * @param interes the interes to set
     */
    public void setInteres(double interes) {
        this.interes = interes;
    }
    /**
     * @return the capital
     */
    public double getCapital() {
        return capital;
    }
    /**
     * @param capital the capital to set
     */
    public void setCapital(double capital) {
        this.capital = capital;
    }
    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }
    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    /**
     * @return the totalInterest
     */
    public double getTotalInterest() {
        return totalInterest;
    }
    /**
     * @param totalInterest the totalInterest to set
     */
    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
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
