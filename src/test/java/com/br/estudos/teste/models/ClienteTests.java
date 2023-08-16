package com.br.estudos.teste.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteTests {

	@Test
	void validarPropriedades() {
		var cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("teste");
		cliente.setTelefone("(15) 5555-55555");
		cliente.setCpf("157.458.789-88");


		assertEquals(1, cliente.getId());
		assertEquals("teste", cliente.getNome());
		assertEquals("(15) 5555-55555", cliente.getTelefone());
		assertEquals("157.458.789-88", cliente.getCpf());
	}

}
