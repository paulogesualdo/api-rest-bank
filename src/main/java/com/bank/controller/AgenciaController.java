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

import com.bank.entity.Agencia;
import com.bank.repository.AgenciaRepository;

@RestController
public class AgenciaController {
    @Autowired
    private AgenciaRepository _agenciaRepository;

    @RequestMapping(value = "/agencia", method = RequestMethod.GET)
    public List<Agencia> Get() {
        return _agenciaRepository.findAll();
    }

    @RequestMapping(value = "/agencia/{id}", method = RequestMethod.GET)
    public ResponseEntity<Agencia> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Agencia> agencia = _agenciaRepository.findById(id);
        if(agencia.isPresent())
            return new ResponseEntity<Agencia>(agencia.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/agencia", method =  RequestMethod.POST)
    public ResponseEntity Post(@Valid @RequestBody Agencia agencia)
    {
        String fone = agencia.getFone();
        String endereco = agencia.getEndereco();
        String nome = agencia.getNome();
        
        if (fone == null) {

        	return new ResponseEntity<>("O campo fone não pode estar vazio", HttpStatus.UNAUTHORIZED);
        	
        }
        else if (endereco == null) {
        	
        	return new ResponseEntity<>("O campo endereço não pode estar vazio", HttpStatus.UNAUTHORIZED);
        	
        }
        else if (nome == null) {
        	
        	return new ResponseEntity<>("O campo nome não pode estar vazio", HttpStatus.UNAUTHORIZED);
        	
        }
        else {
        	return new ResponseEntity<Agencia> (_agenciaRepository.save(agencia), HttpStatus.OK);
        }
    	
    	
    }

    @RequestMapping(value = "/agencia/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Agencia> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Agencia newAgencia)
    {
        Optional<Agencia> oldAgencia = _agenciaRepository.findById(id);
        if(oldAgencia.isPresent()){
            Agencia agencia = oldAgencia.get();
            agencia.setNome(newAgencia.getNome());
            agencia.setEndereco(newAgencia.getEndereco());
            agencia.setFone(newAgencia.getFone());
            _agenciaRepository.save(agencia);
            return new ResponseEntity<Agencia>(agencia, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/agencia/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Agencia> agencia = _agenciaRepository.findById(id);
        if(agencia.isPresent()){
            _agenciaRepository.delete(agencia.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

