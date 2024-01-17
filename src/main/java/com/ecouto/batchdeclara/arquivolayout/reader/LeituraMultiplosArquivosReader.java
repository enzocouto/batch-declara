package com.ecouto.batchdeclara.arquivolayout.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Configuration
public class LeituraMultiplosArquivosReader implements ItemStreamReader<ArquivoLayout>, ResourceAwareItemReaderItemStream<ArquivoLayout>{

	private FlatFileItemReader<ArquivoLayout> delegate;
	
	
	public LeituraMultiplosArquivosReader(FlatFileItemReader<ArquivoLayout> arquivoLayout) {
		this.delegate = arquivoLayout;
	}
	
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		delegate.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		delegate.update(executionContext);
		
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
		
	}

	@Override
	public ArquivoLayout read()throws Exception  {
		
		return delegate.read();
	}

	@Override
	public void setResource(Resource resource) {
		
		delegate.setResource(resource);
		
	}
}
