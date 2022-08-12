package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.entity.Extrato;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long> { 
	
	@Query(
			value = "SELECT * FROM extrato WHERE conta_id = ?1",
			nativeQuery = true)
    List<Extrato> findExtratoByContaId(long id);
	
	@Query(
			value = "SELECT SUM(valor) FROM extrato WHERE conta_id = ?1",
			nativeQuery = true)
    float recalcularSaldo(long id);
	
	@Query(
			value = "SELECT * FROM extrato WHERE valor = ?1",
			nativeQuery = true)
    List<Extrato> findByValor(float valor);
	
}
