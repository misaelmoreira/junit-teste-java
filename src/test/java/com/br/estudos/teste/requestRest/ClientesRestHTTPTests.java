package com.br.estudos.teste.requestRest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.estudos.teste.models.Cliente;
import com.br.estudos.teste.requestRest.lib.HttpTestCliente;

@SpringBootTest
public class ClientesRestHTTPTests {
    
    @Test
    public void buscaClientescontrollerHTTP() throws ClientProtocolException, IOException{
        var statusCode = HttpTestCliente.get("/clientes");

        assertEquals(200, statusCode);

    }

    @Test
    public void cadastraClientescontrollerHTTP() throws ClientProtocolException, IOException{
        var cliente = new Cliente();
        cliente.setCpf("642.236.630-20");
        cliente.setNome("Misael");
        var statusCode = HttpTestCliente.post("/clientes", cliente);
        
        assertEquals(201, statusCode);
    }          
}
