package com.br.estudos.teste.servicos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.estudos.teste.models.Cliente;

@SpringBootTest
public class ClienteServicoTests {
    @Test
    void cpfValido(){
        var cliente = new Cliente();
        cliente.setCpf("642.236.630-20");
        boolean verdadeiro = ClienteServico.validaCPF(cliente);
        assertEquals(true, verdadeiro);
    }

    @Test
    void cpfValidoSemFormatacao(){
        var cliente = new Cliente();
        cliente.setCpf("64223663020");
        boolean verdadeiro = ClienteServico.validaCPF(cliente);
        assertEquals(true, verdadeiro);
    }

    @Test
    void cpfInvalido(){
        var cliente = new Cliente();
        cliente.setCpf("642.236.630-22");
        boolean verdadeiro = ClienteServico.validaCPF(cliente);
        assertEquals(false, verdadeiro);
    }

    @Test
    void cpfInvalidoSemFormatacao(){
        var cliente = new Cliente();
        cliente.setCpf("64223663022");
        boolean verdadeiro = ClienteServico.validaCPF(cliente);
        assertEquals(false, verdadeiro);
    }

    @Test
    void cpfNull(){
        var cliente = new Cliente();
        cliente.setCpf(null);
        boolean falso = ClienteServico.validaCPF(cliente);
        assertEquals(false, falso);
    }

    @Test
    void cpfEmpty(){
        var cliente = new Cliente();
        cliente.setCpf("");
        boolean falso = ClienteServico.validaCPF(cliente);
        assertEquals(false, falso);
    }

    @Test
    void cpfComNome(){
        var cliente = new Cliente();
        cliente.setCpf("Joao");
        boolean falso = ClienteServico.validaCPF(cliente);
        assertEquals(false, falso);
    }


    @Test
    void cpfTudoUm(){
        var cliente = new Cliente();
        cliente.setCpf("11111111111");
        boolean falso = ClienteServico.validaCPF(cliente);
        assertEquals(false, falso);
    }
}
