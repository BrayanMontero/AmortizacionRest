package com.soa.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.soa.dto.Request;
import com.soa.dto.RequestTabla;
import com.soa.dto.RespuestaBuro;
import com.soa.dto.RespuestaDisp;
import com.soa.dto.RespuestaMain;
import com.soa.dto.RespuestaTabla;

@RestController
public class mainRest {
    
    
    @RequestMapping(method = RequestMethod.GET, path = "/amortizar")
    public ResponseEntity<RespuestaMain> amortizar(@RequestBody Request request) {
        System.out.println("Request: " + request);
        String mensaje = "";
        
        String url = "http://localhost:8080/buro/" + request.getRfc();
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("noControl", "19141197");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        // Realizar la solicitud HTTP a la URL del servicio buro
        ResponseEntity<RespuestaBuro> respuestaBuroResponseEntity = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity, RespuestaBuro.class);

        // Procesar la respuesta del servicio buro
        RespuestaBuro respuestaBuro = respuestaBuroResponseEntity.getBody();
        if (respuestaBuro != null && respuestaBuro.getAprovado() != null) {
            if (respuestaBuro.getAprovado()) {
                // Lógica si la respuesta es aprobada
                mensaje = "Aprobado";
                System.out.println("Aprobado");

                // Pass variables to tablaRest controller
                RequestTabla requestTabla = new RequestTabla();
                requestTabla.setNum_tarjeta(request.getNum_tarjeta());
                requestTabla.setInteres(request.getInteres());
                requestTabla.setMeses(request.getMeses());
                requestTabla.setCantida(request.getCantida());

                // Call tablaRest controller
                ResponseEntity<RespuestaTabla> tablaResponseEntity = restTemplate.postForEntity(
                        "http://localhost:8080/tabla", requestTabla, RespuestaTabla.class);

                // You can handle the response from tablaRest as needed
                RespuestaTabla respuestaTabla = tablaResponseEntity.getBody();

                // Construct the main response
                RespuestaMain respuestaMain = new RespuestaMain();
                respuestaMain.setMensaje(mensaje);
                respuestaMain.setRespuestaTabla(respuestaTabla);
                
                // Construct the dispersion URL
                String urldisp = "http://localhost:8080/dispersion/" + request.getCantida() + "/" + request.getNum_tarjeta();

                // Call dispersionRest controller
                ResponseEntity<RespuestaDisp> dispersionResponseEntity = restTemplate.getForEntity(
                        urldisp, RespuestaDisp.class);

                // You can handle the response from dispersionRest as needed
                RespuestaDisp respuestaDisp = dispersionResponseEntity.getBody();
                
                respuestaMain.setRespuestaDisp(respuestaDisp);
                return new ResponseEntity<>(respuestaMain, HttpStatus.OK);
               
                
            } else {
                // Lógica si la respuesta no es aprobada
                mensaje = "No aprobado";
                //System.out.println("No aprobado");
            }
        } else {
            // Manejar el caso en el que la respuesta del servicio buro no sea válida
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Construct the main response when not approved
        RespuestaMain respuestaMain = new RespuestaMain();
        respuestaMain.setMensaje(mensaje);
        return new ResponseEntity<>(respuestaMain, HttpStatus.OK);
    }

   
}
