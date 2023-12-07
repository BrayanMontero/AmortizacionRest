package com.soa.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.soa.dao.tablaActualizarDao;
import com.soa.dto.RequestTablaActualizar;
import com.soa.dto.RespuestaTabla;
import com.soa.dto.RespuestaTablaActualizar;
import com.soa.dto.tablaDto;
import com.soa.dto.usuarioDto;

@Component
public class tablaBusinessActualizar {
    @Autowired
    private tablaActualizarDao tablaActualizar;
    
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    


    public RespuestaTablaActualizar generarPagos(RequestTablaActualizar requestTablaActualizar) {
        RespuestaTablaActualizar respuestaTablaActualizar = new RespuestaTablaActualizar();
        
        
        try {
            List<tablaDto> listPeriodo = tablaActualizar.query(requestTablaActualizar);
            List<tablaDto> pagos = new ArrayList<>();
            tablaDto primerPeriodo = listPeriodo.get(0);
            
            List<usuarioDto> listInfo = tablaActualizar.queryinfo(requestTablaActualizar);
            List<usuarioDto> pagosInfo = new ArrayList<>();
            usuarioDto primerInfo = listInfo.get(0);
            
            double balance = 0;
            //double tasaInteresMensual = requestTabla.getInteres() / 1200.0; // Tasa de interés mensual
            double tasaInteres = 0; // Tasa de interés mensual
            //System.out.println(tasaInteresMensual );
            double totalInterest = 0;
            
            if (requestTablaActualizar.getMes()==1) {
                balance = primerInfo.getCantidad();
                tasaInteres = primerInfo.getInteres() / 100.0;
                totalInterest = 0;
            } else {
                balance = primerPeriodo.getBalance();
                tasaInteres = primerInfo.getInteres() / 100.0; // Tasa de interés mensual
                totalInterest = primerPeriodo.getTotalInterest();
            }
            
            String rfc = requestTablaActualizar.getRfc();
            
            System.out.println(balance);
            System.out.println(tasaInteres);
            System.out.println(totalInterest);
            System.out.println(rfc);

            for (int i = requestTablaActualizar.getMes(); i <= primerInfo.getMeses(); i++) {
                double interest = balance * tasaInteres/12;
                double payment = primerPeriodo.getPago();
                double nbalance=0;
                if (requestTablaActualizar.getMes()==i) {
                    nbalance=balance+balance*tasaInteres/12-payment-requestTablaActualizar.getPago();
                }else {
                    nbalance=balance+balance*tasaInteres/12-payment;
                }
                double capital = payment-interest;         

                totalInterest += interest;

                tablaDto pago = new tablaDto();
                pago.setPeriodo(i);
                pago.setPago(payment);
                pago.setInteres(interest);
                pago.setCapital(capital);
                pago.setBalance(nbalance);
                pago.setTotalInterest(totalInterest);
                pago.setRfc(rfc);
                pagos.add(pago);
                String sql = "UPDATE tabla SET " +
                        "pago = ?, " +
                        "interes = ?, " +
                        "capital = ?, " +
                        "balance = ?, " +
                        "totalInterest = ? " +
                        "WHERE periodo = ? AND rfc = ?";

                jdbcTemplate.update(
                        sql,
                        payment,
                        interest,
                        capital,
                        nbalance,
                        totalInterest,
                        i,
                        rfc
                );
                balance = nbalance;
            }

            
            respuestaTablaActualizar.setPagos(pagos);
            
            
            
            
//            respuesta.setMessage("OK");
//            respuesta.setUsuarios(list);
            
        } catch (Exception e) {
            e.printStackTrace();
//            respuesta.setMessage("Error en BD al consultar login: " 
//                    + usuarioDto.getLogin());
        }
        return respuestaTablaActualizar;
    }

}
