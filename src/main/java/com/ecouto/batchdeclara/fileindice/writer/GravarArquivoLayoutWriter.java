package com.ecouto.batchdeclara.fileindice.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Component
public class GravarArquivoLayoutWriter implements ItemWriter<ArquivoLayout> {

	@Override
	public void write(List<? extends ArquivoLayout> items) throws Exception {
		
		System.out.println("GravarArquivoLayoutWriter");
		for (ArquivoLayout arquivoLayout : items) {
			System.out.print(arquivoLayout.getNomeArquivo()+ "\t");
			System.out.print(arquivoLayout.getTipoLayout()+ "\t");
			System.out.print(arquivoLayout.getStatusEnvio()+ "\t");
			
		}
		
	}

}
