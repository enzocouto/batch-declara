package com.ecouto.batchdeclara.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoEnvioEnum {

	
	MEN(1), SEM(2), ANO(3);

	private int valor;
	
	private static final Map<String,TipoEnvioEnum> tipoEnvioPorValor = new HashMap<String,TipoEnvioEnum>();
	
	static {
		for(TipoEnvioEnum tipoEnvio : TipoEnvioEnum.values()) {
			tipoEnvioPorValor.put(tipoEnvio.name(), tipoEnvio);
		}
	}
	
	public Integer getValor() {
		return valor;
	}
	
	TipoEnvioEnum(int valor) {
		this.valor = valor;
	}
	
	public static TipoEnvioEnum getTipoEnvioByName(String name) {
		return tipoEnvioPorValor.get(name);
	}
}
