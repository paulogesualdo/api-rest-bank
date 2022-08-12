package com.bank.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Agencia
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private String fone;
    
    // Uma agência para muitas contas, então cada agência terá uma lista de contas
    @OneToMany
    private List<Conta> conta;
    
    // Getters e setters

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public String getEndereco() {
        return endereco;
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
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void setFone(String fone) {
        this.fone = fone;
    }

	public void setConta(List<Conta> conta) {
		this.conta = conta;
	}

}
