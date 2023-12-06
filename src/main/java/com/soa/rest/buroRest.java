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
import com.soa.dto.Request;
import com.soa.dto.RespuestaBuro;
import com.soa.dto.buroDto;


/**
 * 
 */
@RestController
public class buroRest {
    @Autowired
    private buroBusiness business;

    @GetMapping("/buro/{rfc}")
    public ResponseEntity<RespuestaBuro> calificar(@PathVariable(name = "rfc") String rfc) {
        ResponseEntity<RespuestaBuro> re = null;
        System.out.println("RFC recibido: " + rfc);
        RespuestaBuro respuesta = business.calificar(rfc);
        re = new ResponseEntity<>(respuesta, HttpStatus.OK);
        return re;
    }
}
