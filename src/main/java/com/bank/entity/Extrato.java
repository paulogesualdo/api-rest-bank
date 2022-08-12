package com.bank.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Extrato
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;
    
    @Column(nullable = false)
    private long operacao;
    
    @Column(nullable = false)
    private float valor;
    
    // Muitas transacoes para uma conta
    @ManyToOne
    private Conta conta;
        
    // Getters and setters

    public long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public long getOperacao() {
        return operacao;
    }
    
	public Conta getConta() {
		return conta;
	}
    
    public float getValor() {
        return valor;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public void setOperacao(long operacao) {
        this.operacao = operacao;
    }
    
    public void setValor(float valor) {
        this.valor = valor;
    }

	public void setConta(Conta conta) {
		this.conta = conta;
	}
    
    

}
