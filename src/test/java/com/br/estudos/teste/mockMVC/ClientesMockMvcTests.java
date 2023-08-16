package com.br.estudos.teste.mockMVC;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.br.estudos.teste.modelViews.Erro;
import com.br.estudos.teste.models.Cliente;
import com.br.estudos.teste.repositorios.IClienteRepositorio;
import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientesMockMvcTests {
    @Autowired
    private IClienteRepositorio repo;

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void buscaClientescontrollerMock() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void cadastraClientescontrollerMock() throws Exception{
        var cliente = getCliente();

        String jsonFile = new Gson().toJson(cliente);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
            .content(jsonFile)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }  

    @Test
    public void alterarClientescontrollerMock() throws Exception{
        var cliente = getCliente();
        repo.save(cliente);

        cliente.setNome("joao");
        String jsonFile = new Gson().toJson(cliente);

        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/" + cliente.getId() )
            .content(jsonFile)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

        var clienteDb = repo.findById(cliente.getId()).get();
        assertEquals("joao", clienteDb.getNome());
    }

    @Test
    public void alterarComRetornoClientescontrollerMock() throws Exception{
        var cliente = getCliente();
        repo.save(cliente);

        cliente.setNome("joao");
        String jsonFile = new Gson().toJson(cliente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/clientes/" + cliente.getId() )
            .content(jsonFile)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        Cliente clienteRest = new Gson().fromJson(jsonString, Cliente.class);
        assertEquals("joao", clienteRest.getNome());
    }

    @Test
    public void alterar404ComRetornoClientescontrollerMock() throws Exception{
        String jsonFile = new Gson().toJson(getCliente());

        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/1000")
            .content(jsonFile)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void alterar400ComRetornoClientescontrollerMock() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/1000")
            .content("")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deleteClientescontrollerMock() throws Exception {
        var cliente = getCliente();
        repo.save(cliente);

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/" + cliente.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent());

        var clienteDb = repo.findById(cliente.getId());
        assertEquals(true, clienteDb.isEmpty());
    }

    @Test
    public void delete404ClientescontrollerMock() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/1000")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void cadastrarCpfInvalidoClientescontrollerMock() throws Exception{
        var cliente = getClienteCpfInvalido();

        String jsonFile = new Gson().toJson(cliente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
            .content(jsonFile)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        Erro erro = new Gson().fromJson(jsonString, Erro.class);
        assertEquals("O CPF passado foi invalido", erro.getMensagem());
    }  

    @Test
    public void alterarCpfInvalidoClientescontrollerMock() throws Exception{
        var cliente = getClienteCpfInvalido();
        repo.save(cliente);

        cliente.setNome("joao");
        String jsonFile = new Gson().toJson(cliente);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/clientes/" + cliente.getId())
            .content(jsonFile)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        Erro erro = new Gson().fromJson(jsonString, Erro.class);
        assertEquals("O CPF passado foi invalido", erro.getMensagem());
    }


    private Cliente getCliente() {
        var cliente = new Cliente();
        cliente.setCpf("642.236.630-20");
        cliente.setTelefone("(11) 11111-1111");
        cliente.setNome("Misael");
        return cliente;
    }

    private Cliente getClienteCpfInvalido() {
        var cliente = new Cliente();
        cliente.setCpf("642.236.630-22");
        cliente.setTelefone("(11) 11111-1111");
        cliente.setNome("Invalido");
        return cliente;
    }
}
