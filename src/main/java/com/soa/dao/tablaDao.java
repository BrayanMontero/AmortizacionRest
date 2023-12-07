package com.soa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soa.dto.RequestTabla;


@Repository
public class tablaDao {
    
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public void inserta(RequestTabla requesTabla, double totalInterest) {
        this.jdbcTemplate.execute("INSERT INTO genamortizacion(num_tarjeta, interes, meses, cantidad, totalInterest) VALUES('" + 
                                    requesTabla.getNum_tarjeta()+ "', " + 
                                    requesTabla.getInteres()+ ", " + 
                                    requesTabla.getMeses()+ ", " + 
                                    requesTabla.getCantida()+ ", " +
                                    totalInterest + ");");
    }
   

}
