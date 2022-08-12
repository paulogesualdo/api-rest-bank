package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { 
	
	@Query(
			value = "SELECT * FROM cliente WHERE nome = ?1",
			nativeQuery = true)
    List<Cliente> findByNome(String nome);
	
	@Query(
			value = "DELETE FROM cliente WHERE nome = ?1",
			nativeQuery = true)
    List<Cliente> deleteByNome(String nome);
	
}
