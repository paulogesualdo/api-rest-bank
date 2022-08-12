package com.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.entity.Agencia;
import com.bank.entity.Cliente;
import com.bank.entity.Conta;
import com.bank.entity.Extrato;
import com.bank.repository.AgenciaRepository;
import com.bank.repository.ClienteRepository;
import com.bank.repository.ContaRepository;
import com.bank.repository.ExtratoRepository;

@SpringBootTest
public class ExtratoTest {
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	AgenciaRepository agenciaRepo;
	
	@Autowired
	ContaRepository contaRepo;
	
	@Autowired
	ExtratoRepository extratoRepo;
	
	@Test
	void cadastraExtrato() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Teste");
		cliente.setCpf("999999999-99");
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
		
		Extrato extrato = new Extrato();
		extrato.setDataHora(LocalDateTime.now());
		extrato.setOperacao(1);
		extrato.setConta(conta);
		extrato.setValor(999999999);
		extratoRepo.save(extrato);
	}
	
	
	@Test
	public void consultaExtrato() {
		List<Extrato> list = extratoRepo.findByValor(999999999);
		assertThat(list).size().isGreaterThan(0);
	}	

}
