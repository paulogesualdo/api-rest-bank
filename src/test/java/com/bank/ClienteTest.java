package com.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.entity.Cliente;
import com.bank.repository.ClienteRepository;

@SpringBootTest
public class ClienteTest {
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Test
	void cadastraCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste");
		cliente.setCpf("09710611658");
		cliente.setFone("(99) 99999-9999");
		clienteRepo.save(cliente);
	}
	
	@Test
	public void consultaCliente() {
		List<Cliente> list = clienteRepo.findByNome("Teste");
		assertThat(list).size().isGreaterThan(0);
	}

}
