package com.ecouto.batchdeclara.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import com.ecouto.batchdeclara.model.ArquivoLayout;


public class GravarArquivoLayoutWriterListener implements ItemWriteListener<ArquivoLayout> {

	@Override
	public void beforeWrite(List<? extends ArquivoLayout> items) {
		System.out.println("GravarArquivoLayoutWriterListener - beforeWrite");
		items.stream().forEach(System.out::println);
		
	}

	@Override
	public void afterWrite(List<? extends ArquivoLayout> items) {
		System.out.println("GravarArquivoLayoutWriterListener - afterWrite");
		items.stream().forEach(System.out::println);
		
	}

	@Override
	public void onWriteError(Exception exception, List<? extends ArquivoLayout> items) {
		
		System.out.println("GravarArquivoLayoutWriterListener - onWriteError"+exception.getMessage());
	}

}
