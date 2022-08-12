package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> { 
	
	@Query(
			value = "SELECT * FROM conta WHERE numero = ?1",
			nativeQuery = true)
    List<Conta> findByNumero(String numero);
	
	@Query(
			value = "DELETE FROM conta WHERE numero = ?1",
			nativeQuery = true)
    List<Conta> deleteByNumero(String numero);	
	
}
