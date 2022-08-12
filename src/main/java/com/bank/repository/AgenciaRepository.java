package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.entity.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> { 
	
	@Query(
			value = "SELECT * FROM agencia WHERE nome = ?1",
			nativeQuery = true)
    List<Agencia> findByNome(String nome);
	
	@Query(
			value = "DELETE FROM agencia WHERE nome = ?1",
			nativeQuery = true)
    List<Agencia> deleteByNome(String nome);
	
	
}
