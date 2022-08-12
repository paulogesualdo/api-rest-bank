package com.bank;

public enum EnumOpcoesMenu {
	
	SAQUE(1),
	DEPOSITO(2),
	TRANSFERENCIA(3);
	
	private final int valor;
	
	EnumOpcoesMenu (int valorOpcao){
		valor = valorOpcao;
	}
	
	public int getValor() {
		return valor;
	}

}
