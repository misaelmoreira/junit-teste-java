package com.br.estudos.teste.mockMVC;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.br.estudos.teste.repositorios.IClienteRepositorio;
import com.br.estudos.teste.requestRest.lib.HttpTestCliente;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeMockMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testaHome() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
            .andExpect(MockMvcResultMatchers.status().isOk());
        
    }    
}
