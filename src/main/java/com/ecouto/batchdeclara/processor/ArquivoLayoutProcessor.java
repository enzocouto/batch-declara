package com.ecouto.batchdeclara.processor;

import java.util.Iterator;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Component
public class ArquivoLayoutProcessor {

	
	@Bean
	public ItemProcessor<ArquivoLayout, ArquivoLayout> processor() {
		
		
		return arquivoLayout -> arquivoLayout;
	}
}
