package com.ecouto.batchdeclara.enums;

public enum StatusEnvioEnum {

	
	AGUARDANDO_PROCESSAMENTO(1), PROCESSANDO(2), PROCESSAMENTO_FINALIZADO(3);
	
	
	private int valor;
	
	StatusEnvioEnum(int valor){
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
}
