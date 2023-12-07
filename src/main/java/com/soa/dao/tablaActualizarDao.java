/**
 * 
 */
package com.soa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.soa.dto.RequestTablaActualizar;
import com.soa.dto.tablaDto;
import com.soa.dto.usuarioDto;

/**
 * 
 */
@Service
public class tablaActualizarDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<tablaDto> query(RequestTablaActualizar requestTablaActualizar) {
        List<tablaDto> listPeriodo = jdbcTemplate.query(
                "select pago, interes, capital, balance, totalInterest, rfc"
                + " from tabla where periodo= "
                + (requestTablaActualizar.getMes()-1)+ " AND rfc = '" + requestTablaActualizar.getRfc()+"'", 
                new BeanPropertyRowMapper<tablaDto>(tablaDto.class));
        return listPeriodo;
    }
    
    public List<usuarioDto> queryinfo(RequestTablaActualizar requestTablaActualizar) {
        List<usuarioDto> listInfo = jdbcTemplate.query(
                "select interes, meses, cantidad, totalInterest, saldo "
                + "from genamortizacion where rfc = '" + requestTablaActualizar.getRfc()+"'", 
                new BeanPropertyRowMapper<usuarioDto>(usuarioDto.class));
        return listInfo;
    }
    
    public void queryactualiza(double totalInterest, String rfc) {
            jdbcTemplate.execute("update genamortizacion set totalInterest =" 
                    + totalInterest + " where rfc = '" 
                    + rfc + "'");
        }
}
