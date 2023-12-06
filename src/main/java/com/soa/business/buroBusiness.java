/**
 * 
 */
package com.soa.business;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.soa.dao.tablaDao;
import com.soa.dto.RespuestaBuro;

/**
 * 
 */
@Service
public class buroBusiness {
    @Autowired
    private tablaDao tabla;

    public RespuestaBuro calificar(String rfc) {
      
        RespuestaBuro respuestaBuro = new RespuestaBuro();
        int score = new Random().nextInt(851);
        if(score >= 200){
            
            respuestaBuro.setAprovado(true);
        }
        else {
            
            respuestaBuro.setAprovado(false);
        }

        return respuestaBuro;

    }

}
