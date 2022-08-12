package com.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Conta;
import com.bank.repository.ContaRepository;

@RestController
public class ContaController {
    @Autowired
    private ContaRepository _contaRepository;

    @RequestMapping(value = "/conta", method = RequestMethod.GET)
    public List<Conta> Get() {
        return _contaRepository.findAll();
    }

    @RequestMapping(value = "/conta/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conta> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Conta> conta = _contaRepository.findById(id);
        if(conta.isPresent())
            return new ResponseEntity<Conta>(conta.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/conta", method =  RequestMethod.POST)
    public ResponseEntity Post(@Valid @RequestBody Conta conta)
    {
        String numeroConta = conta.getNumero();
        
        if (numeroConta == null) {
        	
        	return new ResponseEntity<>("O campo número não pode estar vazio", HttpStatus.UNAUTHORIZED);
        
        }
        else {
        	
        	return new ResponseEntity<Conta> (_contaRepository.save(conta), HttpStatus.OK);
        	
        }
        
    }

    @RequestMapping(value = "/conta/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Conta> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Conta newConta)
    {
        Optional<Conta> oldConta = _contaRepository.findById(id);
        
        if(oldConta.isPresent()){
            
        	Conta conta = oldConta.get();
            conta.setNumero(newConta.getNumero());
            conta.setSaldo(newConta.getSaldo());
            conta.setCliente(newConta.getCliente());
            conta.setAgencia(newConta.getAgencia());
            _contaRepository.save(conta);
            return new ResponseEntity<Conta>(conta, HttpStatus.OK);
            
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/conta/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Conta> conta = _contaRepository.findById(id);
        if(conta.isPresent()){
            _contaRepository.delete(conta.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

