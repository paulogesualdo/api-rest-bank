package com.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.entity.Agencia;
import com.bank.repository.AgenciaRepository;

@SpringBootTest
public class AgenciaTest {
	
	@Autowired
	AgenciaRepository agenciaRepo;
	
	@Test
	void cadastraAgencia() {
		Agencia agencia = new Agencia();
		agencia.setNome("Teste");
		agencia.setEndereco("Rua Teste, número teste");
		agencia.setFone("(99) 99999-9999");
		agenciaRepo.save(agencia);
	}
	
	@Test
	public void consultaAgencia() {
		List<Agencia> list = agenciaRepo.findByNome("Teste");
		assertThat(list).size().isGreaterThan(0);
	}

}
