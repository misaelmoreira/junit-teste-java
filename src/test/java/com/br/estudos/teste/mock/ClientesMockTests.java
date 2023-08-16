package com.br.estudos.teste.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.estudos.teste.models.Cliente;
import com.br.estudos.teste.repositorios.IClienteRepositorio;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientesMockTests {

    @Mock
    private IClienteRepositorio repo;

    @Test
    void buscarUsuarioPorNome(){
        
        when(repo.findByName("Tadeu")).thenReturn(new Cliente());

        var clienteDb = repo.findByName("Tadeu");
        assertNotNull(clienteDb);                
    }


    @Test
    void quantidadeMock(){        
        when(repo.count()).thenReturn((long)1);
        assertEquals(1, repo.count());                
    }
    
}
