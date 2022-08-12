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

import com.bank.entity.Cliente;
import com.bank.repository.ClienteRepository;

@RestController
public class ClienteController {
    @Autowired
    private ClienteRepository _clienteRepository;

    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public List<Cliente> Get() {
        return _clienteRepository.findAll();
    }

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(id);
        if(cliente.isPresent())
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cliente", method =  RequestMethod.POST)
    public ResponseEntity Post(@Valid @RequestBody Cliente cliente)
    {
        String fone = cliente.getFone();
        String nome = cliente.getNome();
        String cpf = cliente.getCpf();
        
        if (fone == null) {
        	
        	return new ResponseEntity<>("O campo fone não pode estar vazio", HttpStatus.UNAUTHORIZED);
        	
        }
        else if (nome == null) {
        	
        	return new ResponseEntity<>("O campo nome não pode estar vazio",HttpStatus.UNAUTHORIZED);
        	
        }
        else if (cpf.length() > 11) {
        	
        	return new ResponseEntity<>("O campo cpf não pode ter mais que 11 caracteres", HttpStatus.UNAUTHORIZED);
        	
        }
        else if (cpfValido(cpf) == false) {
        	
        	return new ResponseEntity<>("O campo cpf não é válido", HttpStatus.UNAUTHORIZED);
        	
        }
        else {
        	
        	return new ResponseEntity<Cliente> (_clienteRepository.save(cliente), HttpStatus.OK); 
        	
        }
        
    	
    }

    @RequestMapping(value = "/cliente/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Cliente> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Cliente newCliente)
    {
        Optional<Cliente> oldCliente = _clienteRepository.findById(id);
        if(oldCliente.isPresent()){
            Cliente cliente = oldCliente.get();
            cliente.setNome(newCliente.getNome());
            cliente.setCpf(newCliente.getCpf());
            cliente.setFone(newCliente.getFone());
            _clienteRepository.save(cliente);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(id);
        if(cliente.isPresent()){
            _clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    public boolean cpfValido(String cpf) {

        int[] cpfValido = new int[11];
        String letra;

        for (int i = 0; i < cpf.length(); i++) {
            letra = String.valueOf(cpf.charAt(i));
            cpfValido[i] = Integer.parseInt(letra);
        }

        int [] peso = {11,10,9,8,7,6,5,4,3,2};
        int primeirodigito = 0, segundodigito = 0;

        int resultado1 = cpfValido[0]*peso[1] + cpfValido[1]*peso[2] + cpfValido[2]*peso[3] +
                         cpfValido[3]*peso[4] + cpfValido[4]*peso[5] + cpfValido[5]*peso[6] +
                         cpfValido[6]*peso[7] + cpfValido[7]*peso[8] + cpfValido[8]*peso[9];

        int resultado2 = cpfValido[0]*peso[0] + cpfValido[1]*peso[1] + cpfValido[2]*peso[2] +
                         cpfValido[3]*peso[3] + cpfValido[4]*peso[4] + cpfValido[5]*peso[5] +
                         cpfValido[6]*peso[6] + cpfValido[7]*peso[7] + cpfValido[8]*peso[8] +
                         cpfValido[9]*peso[9];

        int resto1 = resultado1 % 11;
        int resto2 = resultado2 % 11;

        if (resto1 < 2){
            primeirodigito = 0;
            } else {
                primeirodigito = 11 - resto1;
            }

        if (resto2 < 2){
            segundodigito = 0;
            } else {
                segundodigito = 11 - resto2;
            }

        if (primeirodigito == cpfValido[9] && segundodigito == cpfValido[10]){
            return true;
        }
        else {
        	return false;
        }
    }
    
    
}


