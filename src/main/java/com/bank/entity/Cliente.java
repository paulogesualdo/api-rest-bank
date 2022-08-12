package com.bank.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String cpf;
    
    @Column(nullable = false)
    private String fone;
    
    // Um cliente para muitas contas, então cada cliente terá uma lista de contas
    @OneToMany
    private List<Conta> conta;
    
    // Getters and setters

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public String getFone() {
        return fone;
    }

	public List<Conta> getConta() {
		return conta;
	}
    
    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setFone(String fone) {
        this.fone = fone;
    }

	public void setConta(List<Conta> conta) {
		this.conta = conta;
	}

}
