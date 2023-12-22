/**
 * 
 */
package com.soa.rest;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soa.dto.Request;
import com.soa.dto.RespuestaBuro;


/**
 * 
 */
@RestController
public class BuroCreditoRest {

    @GetMapping("/burocredito/{no_identificacion}")
    public ResponseEntity<Boolean> calificar(@PathVariable(name = "no_identificacion") String no_identificacion) {
        ResponseEntity<Boolean> re = null;
        int calificacion = new Random().nextInt(851);
        Boolean respuesta;

        if (!no_identificacion.isEmpty()) {
            if (calificacion >= 200) {
                respuesta = true;
            } else {
                respuesta = false;
            }

            re = new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            // Manejar el caso en el que la cadena RFC no está vacía
            // Puedes establecer una respuesta específica o devolver un error, según tus necesidades.
            re = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return re;
    }
}
