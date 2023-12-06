/**
 * 
 */
package com.soa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soa.business.buroBusiness;
import com.soa.business.dispBusiness;
import com.soa.dto.Request;
import com.soa.dto.RespuestaBuro;
import com.soa.dto.RespuestaDisp;
import com.soa.dto.buroDto;


/**
 * 
 */
@RestController
public class dispersionRest {
    @Autowired
    private dispBusiness business;

    @GetMapping("/dispersion/{saldo}/{num_tarjeta}")
    public ResponseEntity<RespuestaDisp> dispersion(@PathVariable(name = "saldo") Double saldo, @PathVariable(name = "num_tarjeta") String num_tarjeta) {
        ResponseEntity<RespuestaDisp> re = null;
        System.out.println("saldo recibido: " + saldo);
        RespuestaDisp respuesta = business.add(saldo,num_tarjeta);
        re = new ResponseEntity<>(respuesta, HttpStatus.OK);
        return re;
    }
}
