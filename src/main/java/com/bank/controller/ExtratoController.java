package com.bank.controller;

import java.time.LocalDateTime;
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
import com.bank.entity.Extrato;
import com.bank.repository.ContaRepository;
import com.bank.repository.ExtratoRepository;

@RestController
public class ExtratoController {
    
	@Autowired
    private ExtratoRepository _extratoRepository;
	
    @Autowired
    private ContaRepository _contaRepository;

    @RequestMapping(value = "/extrato", method = RequestMethod.GET)
    public List<Extrato> Get() {
        return _extratoRepository.findAll();
    }

    @RequestMapping(value = "/extrato/{id}", method = RequestMethod.GET)
    public ResponseEntity<Extrato> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Extrato> extrato = _extratoRepository.findById(id);
        if(extrato.isPresent())
            return new ResponseEntity<Extrato>(extrato.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
        
    // Saques e Depósitos
    
    @RequestMapping(value = "/extrato", method =  RequestMethod.POST)
    public Extrato Post(@Valid @RequestBody Extrato extrato)
    {
        // Saque
    	if (extrato.getOperacao() == com.bank.EnumOpcoesMenu.SAQUE.getValor()) {
            
    		Optional<Conta> oldConta = _contaRepository.findById(extrato.getConta().getId());
            Conta conta = oldConta.get();
            conta.setSaldo(conta.getSaldo() - extrato.getValor());
            _contaRepository.save(conta);
            extrato.setValor((extrato.getValor() * -1));
        	extrato.setDataHora(LocalDateTime.now());
    		System.out.println("Foi realizado um saque");
        	return _extratoRepository.save(extrato);
    	}
    	
        // Depósito
    	if (extrato.getOperacao() == com.bank.EnumOpcoesMenu.DEPOSITO.getValor()) {
            
    		Optional<Conta> oldConta = _contaRepository.findById(extrato.getConta().getId());
            Conta conta = oldConta.get();
            conta.setSaldo(conta.getSaldo() + extrato.getValor());
            _contaRepository.save(conta);
        	extrato.setDataHora(LocalDateTime.now());
    		System.out.println("Foi realizado um depósito");
        	return _extratoRepository.save(extrato);
    	}
    	    	
    	extrato.setDataHora(LocalDateTime.now());
    	return _extratoRepository.save(extrato);
    }
    
    // Exibir o extrato de uma conta
        
    @RequestMapping(value = "/extrato/conta/{id}", method = RequestMethod.GET)
    public List<Extrato> extratoConta(@PathVariable(value = "id") long id)
    {
        System.out.println("Foi realizado um extrato por conta_id");
    	return _extratoRepository.findExtratoByContaId(id);
    	
    }
    
    // Recalcular saldo de uma conta
    
    @RequestMapping(value = "/conta/recalcularsaldo/{id}", method = RequestMethod.PUT)
    public Conta recalcularSaldo(@PathVariable(value = "id") long id)
    {
        
		Optional<Conta> oldConta = _contaRepository.findById(id);
        Conta conta = oldConta.get();
        float newSaldo = 0;
        newSaldo = _extratoRepository.recalcularSaldo(id);
        conta.setSaldo(newSaldo);
        ;
    	
    	System.out.println("Foi realizado um recálculo de saldo");       
    	return _contaRepository.save(conta);
    	
    }
    
    // Transferências
    @RequestMapping(value = "/extrato/{id}", method =  RequestMethod.POST)
    public ResponseEntity Post(@Valid @RequestBody Extrato extratoOrigem, @PathVariable(value = "id") long id)
    {

    	Optional<Conta> oldContaDestino = _contaRepository.findById(id);
		
        if(oldContaDestino.isPresent()){

        	
        	Optional<Conta> oldContaOrigem = _contaRepository.findById(extratoOrigem.getConta().getId());
    		
            Conta contaOrigem = oldContaOrigem.get();
            Conta contaDestino = oldContaDestino.get();
            
            contaOrigem.setSaldo(contaOrigem.getSaldo() - extratoOrigem.getValor());
            contaDestino.setSaldo(contaOrigem.getSaldo() + extratoOrigem.getValor());
            
            _contaRepository.save(contaOrigem);
            _contaRepository.save(contaDestino);
            
            extratoOrigem.setValor((extratoOrigem.getValor() * -1));
        	extratoOrigem.setDataHora(LocalDateTime.now());
        	
        	Extrato extratoDestino = new Extrato();
        	extratoDestino.setDataHora(LocalDateTime.now());
        	extratoDestino.setOperacao(extratoOrigem.getOperacao());
            extratoDestino.setValor((extratoOrigem.getValor() * -1));
            extratoDestino.setConta(contaDestino);
            
            _extratoRepository.save(extratoDestino);        
        	
    		System.out.println("Foi realizada uma transferência");
       	
            return new ResponseEntity<Extrato>(_extratoRepository.save(extratoOrigem), HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<>("Conta de destino não encontrada", HttpStatus.NOT_FOUND);
        }
            
    }

    @RequestMapping(value = "/extrato/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Extrato> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Extrato newExtrato)
    {
        Optional<Extrato> oldExtrato = _extratoRepository.findById(id);
        if(oldExtrato.isPresent()){
            Extrato extrato = oldExtrato.get();
            extrato.setDataHora(newExtrato.getDataHora());
            extrato.setOperacao(newExtrato.getOperacao());
            extrato.setConta(newExtrato.getConta());
            extrato.setValor(newExtrato.getValor());
            _extratoRepository.save(extrato);
            return new ResponseEntity<Extrato>(extrato, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/extrato/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Extrato> extrato = _extratoRepository.findById(id);
        if(extrato.isPresent()){
            _extratoRepository.delete(extrato.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

