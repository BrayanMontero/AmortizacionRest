/**
 * 
 */
package com.soa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soa.business.TablaAmortizacionBusiness;
import com.soa.dto.Request;
import com.soa.dto.IngresarTabla;
import com.soa.dto.RespondeTablaAmortizacion;
import com.soa.dto.TablaAmortizacionDto;


/**
 * 
 */
@RestController
public class TablaAmortizacionRest {
    @Autowired
    private TablaAmortizacionBusiness business;

    @RequestMapping(method=RequestMethod.GET, path="/tablaamortizacion")
    public ResponseEntity<RespondeTablaAmortizacion> tabla(@RequestBody IngresarTabla ingresaTabla) {
        ResponseEntity<RespondeTablaAmortizacion> re = null;
     
        RespondeTablaAmortizacion respuesta = business.generarPagos(ingresaTabla);
        re = new ResponseEntity<>(respuesta, HttpStatus.OK);
        return re;
    }
}
