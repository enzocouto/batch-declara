package com.ecouto.batchdeclara.enums;

public enum StatusEnvio {

	
	AGUARDANDO_PROCESSAMENTO(1), PROCESSANDO(2), PROCESSAMENTO_FINALIZADO(3);
	
	
	private int valor;
	
	StatusEnvio(int valor){
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
}
