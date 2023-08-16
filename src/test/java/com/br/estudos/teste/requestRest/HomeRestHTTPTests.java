package com.br.estudos.teste.requestRest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.estudos.teste.requestRest.lib.HttpTestCliente;

@SpringBootTest
public class HomeRestHTTPTests {
    @Test
    public void testaHome() throws ClientProtocolException, IOException{
        var statusCode = HttpTestCliente.get("/");

        assertEquals(200, statusCode);
    }    
}
