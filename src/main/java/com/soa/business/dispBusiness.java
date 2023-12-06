/**
 * 
 */
package com.soa.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soa.dao.dispDao;
import com.soa.dao.tablaDao;
import com.soa.dto.RespuestaDisp;

/**
 * 
 */
@Service
public class dispBusiness {
    @Autowired
    private dispDao disp;
    
    public RespuestaDisp add(Double saldo, String num_tarjeta) {
        RespuestaDisp respuesta = new RespuestaDisp();
        try {
            disp.insertar(saldo,num_tarjeta);
            respuesta.setMessage("Cresdito depositado");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setMessage("Cresdito no depositado");
        }
        return respuesta;
    }
    
}
