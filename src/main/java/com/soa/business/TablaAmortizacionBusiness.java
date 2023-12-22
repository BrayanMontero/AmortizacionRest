package com.soa.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.soa.commons.Env;
import com.soa.dao.TablaAmortizacionDao;
import com.soa.dto.IngresarTabla;
import com.soa.dto.RespondeTablaAmortizacion;
import com.soa.dto.TablaAmortizacionDto;

@Service
public class TablaAmortizacionBusiness {
    @Autowired
    private TablaAmortizacionDao tabla;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    // Método para obtener los datos de la base de datos por RFC
    private RespondeTablaAmortizacion obtenerDatosGuardados(String rfc) {
        List<TablaAmortizacionDto> datosGuardados = tabla.obtenerDatosPorRFC(rfc);

        RespondeTablaAmortizacion respuestaTabla = new RespondeTablaAmortizacion();
        respuestaTabla.setPagos(datosGuardados);
        respuestaTabla.setMessage("Datos obtenidos de la base de datos");

        return respuestaTabla;
    }

    public RespondeTablaAmortizacion generarPagos(IngresarTabla ingresaTabla) {
        List<TablaAmortizacionDto> tabla = new ArrayList<>();

        double balance = ingresaTabla.getCantida();
        String porcentaje = Env.getProperty("porcentaje.interes");
        double porcentajeEnv = Double.parseDouble(porcentaje);
        double tasaInteres = porcentajeEnv / 100.0;
        double totalInterest = 0;

        for (int i = 1; i <= ingresaTabla.getMeses(); i++) {
            double interest = balance * tasaInteres / 12;
            double tasaInteresMensual = tasaInteres / 12;
            double denominador = Math.pow((1 + tasaInteresMensual), ingresaTabla.getMeses()) - 1;
            double factor = tasaInteresMensual + (tasaInteresMensual / denominador);
            double payment = ingresaTabla.getCantida() * factor;
            double nbalance = balance + balance * tasaInteres / 12 - payment;
            double capital = payment - interest;

            totalInterest += interest;

            TablaAmortizacionDto fila = new TablaAmortizacionDto();
            fila.setPeriodo(i);
            fila.setPago(payment);
            fila.setInteres(interest);
            fila.setCapital(capital);
            fila.setBalance(nbalance);
            fila.setTotalInterest(totalInterest);
            tabla.add(fila);

            String sql = "INSERT INTO tabla (periodo, pago, interes, capital, balance, totalInterest, rfc) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, fila.getPeriodo(), fila.getPago(), fila.getInteres(), fila.getCapital(),
                    fila.getBalance(), fila.getTotalInterest(), ingresaTabla.getRfc());
            balance = nbalance;
        }

        // Después de insertar los datos en la base de datos, obtenerlos
        RespondeTablaAmortizacion datosObtenidos = obtenerDatosGuardados(ingresaTabla.getRfc());

        return datosObtenidos;
    }

   
}
