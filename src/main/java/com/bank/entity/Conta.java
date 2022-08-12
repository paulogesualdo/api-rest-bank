package com.bank.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conta
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String numero;
    
    @Column(nullable = true)
    private float saldo;
    
	// Muitas contas para um cliente
    @ManyToOne
    private Cliente cliente;
    
    // Muitas contas para uma agência
    @ManyToOne
    private Agencia agencia;
    
    // Uma conta para muitas transacoes, então cada conta terá uma lista de extrato
    @OneToMany
    private List<Extrato> extrato;
    
    // Getters and setters

    public long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }
    
    public Agencia getAgencia() {
		return agencia;
	}
    
	public Cliente getCliente() {
		return cliente;
	}
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
    
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public List<Extrato> getExtrato() {
		return extrato;
	}

	public void setExtrato(List<Extrato> extrato) {
		this.extrato = extrato;
	}
	

}
