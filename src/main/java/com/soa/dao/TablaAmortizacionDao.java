package com.soa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soa.dto.IngresarTabla;
import com.soa.dto.TablaAmortizacionDto;


@Repository
public class TablaAmortizacionDao {
    
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public void inserta(IngresarTabla requesTabla, double totalInterest) {
        this.jdbcTemplate.execute("INSERT INTO genamortizacion(num_tarjeta, interes, meses, cantidad, totalInterest,rfc) VALUES('" + 
                                    requesTabla.getNum_tarjeta()+ "', " + 
                                    requesTabla.getInteres()+ ", " + 
                                    requesTabla.getMeses()+ ", " + 
                                    requesTabla.getCantida()+ ", " +
                                    totalInterest+ ", '" +
                                    requesTabla.getRfc() + "');");
    }
    public List<TablaAmortizacionDto> obtenerDatosPorRFC(String rfc) {
        String sql = "SELECT * FROM tabla WHERE rfc = ?";
        return jdbcTemplate.query(sql, new Object[]{rfc}, (resultSet, i) -> {
            TablaAmortizacionDto pago = new TablaAmortizacionDto();
            pago.setPeriodo(resultSet.getInt("periodo"));
            pago.setPago(resultSet.getDouble("pago"));
            pago.setInteres(resultSet.getDouble("interes"));
            pago.setCapital(resultSet.getDouble("capital"));
            pago.setBalance(resultSet.getDouble("balance"));
            pago.setTotalInterest(resultSet.getDouble("totalInterest"));
            return pago;
        });
    }
   

}
