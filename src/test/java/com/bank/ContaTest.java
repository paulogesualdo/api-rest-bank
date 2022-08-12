package com.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.entity.Agencia;
import com.bank.entity.Cliente;
import com.bank.entity.Conta;
import com.bank.repository.AgenciaRepository;
import com.bank.repository.ClienteRepository;
import com.bank.repository.ContaRepository;

@SpringBootTest
public class ContaTest {
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	AgenciaRepository agenciaRepo;
	
	@Autowired
	ContaRepository contaRepo;
	
	@Test
	void cadastraConta() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Teste");
		cliente.setCpf("09710611658");
		cliente.setFone("(99) 99999-9999");
		clienteRepo.save(cliente);
		
		Agencia agencia = new Agencia();
		agencia.setNome("Teste");
		agencia.setEndereco("Rua Teste, número teste");
		agencia.setFone("(99) 99999-9999");
		agenciaRepo.save(agencia);
		
		Conta conta = new Conta();
		conta.setNumero("99999999-9");
		conta.setSaldo(0);
		conta.setCliente(cliente);
		conta.setAgencia(agencia);
		contaRepo.save(conta);
	}
	
	
	@Test
	public void consultaConta() {
		List<Conta> list = contaRepo.findByNumero("99999999-9");
		assertThat(list).size().isGreaterThan(0);
	}
	
	

}
