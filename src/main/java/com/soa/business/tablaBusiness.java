package com.soa.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.soa.dao.tablaDao;
import com.soa.dto.RequestTabla;
import com.soa.dto.RespuestaTabla;
import com.soa.dto.tablaDto;

@Service
public class tablaBusiness {
    @Autowired
    private tablaDao tabla;
    
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    
// taza, meses, prestamo, pago mensual, pago total, num tarjeta

    public static double calcularPagoMensual(double tasaInteresAnual, int numeroPagos, double montoPrestamo) {
        double tasaInteresMensual = tasaInteresAnual / 12;
        double denominador = Math.pow((1 + tasaInteresMensual), numeroPagos) - 1;
        double factor = tasaInteresMensual + (tasaInteresMensual / denominador);
        return montoPrestamo * factor;
    }
    
    public RespuestaTabla generarPagos(RequestTabla requestTabla) {
        List<tablaDto> pagos = new ArrayList<>();
        
        double balance = requestTabla.getCantida();
        //double tasaInteresMensual = requestTabla.getInteres() / 1200.0; // Tasa de interés mensual
        double tasaInteres = requestTabla.getInteres() / 100.0; // Tasa de interés mensual
        //System.out.println(tasaInteresMensual );
        double totalInterest = 0;

        for (int i = 1; i <= requestTabla.getMeses(); i++) {
            double interest = balance * tasaInteres/12;
            double payment = calcularPagoMensual(tasaInteres, requestTabla.getMeses(), requestTabla.getCantida());
            double nbalance=balance+balance*tasaInteres/12-payment;
            double capital = payment-interest;         

            totalInterest += interest;

            tablaDto pago = new tablaDto();
            pago.setPeriodo(i);
            pago.setPago(payment);
            pago.setInteres(interest);
            pago.setCapital(capital);
            pago.setBalance(nbalance);
            pago.setTotalInterest(totalInterest);
            pagos.add(pago);
            String sql = "INSERT INTO tabla (periodo, pago, interes, capital, balance, totalInterest, rfc) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(
                    sql,
                    pago.getPeriodo(),
                    pago.getPago(),
                    pago.getInteres(),
                    pago.getCapital(),
                    pago.getBalance(),
                    pago.getTotalInterest(),
                    requestTabla.getRfc()
            );
            balance = nbalance;
        }

        RespuestaTabla respuestaTabla = new RespuestaTabla();
        respuestaTabla.setPagos(pagos);
        
        try {
            tabla.inserta(requestTabla,totalInterest);
            respuestaTabla.setMessage("Datos guardados");
        } catch (Exception e) {
            e.printStackTrace();
            respuestaTabla.setMessage("Error");
        }

        return respuestaTabla;
    }
}
