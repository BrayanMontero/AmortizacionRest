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

import com.soa.business.buroBusiness;
import com.soa.business.tablaBusiness;
import com.soa.business.tablaBusinessActualizar;
import com.soa.dto.Request;
import com.soa.dto.RequestTabla;
import com.soa.dto.RequestTablaActualizar;
import com.soa.dto.RespuestaTabla;
import com.soa.dto.RespuestaTablaActualizar;
import com.soa.dto.tablaDto;


/**
 * 
 */
@RestController
public class tablaActualizarRest {
    @Autowired
    private tablaBusinessActualizar business;

    @RequestMapping(method=RequestMethod.POST, path="/tablaActualizar")
    public ResponseEntity<RespuestaTablaActualizar> tabla(@RequestBody RequestTablaActualizar requestTablaActualizar) {
        ResponseEntity<RespuestaTablaActualizar> re = null;
     
        RespuestaTablaActualizar respuesta = business.generarPagos(requestTablaActualizar);
        re = new ResponseEntity<>(respuesta, HttpStatus.OK);
        return re;
    }
}
