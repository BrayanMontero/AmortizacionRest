/**
 * 
 */
package com.soa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * 
 */
@Service
public class dispDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Agrega un usuario a la BD.
     * @param usuario Usuario a insertar.
     */
    public void insertar(Double saldo, String num_tarjeta) {
        this.jdbcTemplate.execute("update genamortizacion set saldo = saldo + "+ saldo + " where num_tarjeta = '" + num_tarjeta + "'");
    }



}
