package com.ecouto.batchdeclara.arquivolayout.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.model.ArquivoLayout;


@Component
public class GravarLeituraArquivoLayout implements ItemWriter<ArquivoLayout> {
	
	
	@Override
	public void write(List<? extends ArquivoLayout> items) throws Exception {
		
		System.out.println("GravarLeituraArquivoLayout");
		for (ArquivoLayout arquivoLayout : items) {
			
			System.out.println(arquivoLayout.getNomeArquivo());
			
		}
		
	}
}
