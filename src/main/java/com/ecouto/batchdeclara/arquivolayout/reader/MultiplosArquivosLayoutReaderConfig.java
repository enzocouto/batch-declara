package com.ecouto.batchdeclara.arquivolayout.reader;

import java.util.List;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.FileIndice;
import com.ecouto.batchdeclara.repository.FileIndiceRepository;

@Configuration
public class MultiplosArquivosLayoutReaderConfig implements ItemReadListener<ArquivoLayout> {


	

	@Bean
	@SuppressWarnings({"rawtypes", "unchecked" })
	public MultiResourceItemReader multiplosArquivosLayoutReader(FlatFileItemReader<ArquivoLayout> reader,
			FileIndiceRepository fileIndiceRepository) {
		
		return new MultiResourceItemReaderBuilder()
				.name("multiplosArquivosLayoutReader")
				.resources(getArquivosFromDatabase(fileIndiceRepository))
				.delegate(new LeituraMultiplosArquivosReader(reader))
				.build();
	}
	
	
	@Override
	public void beforeRead() {
		System.out.println("MultiplosArquivosLayoutReaderConfig - beforeRead");
		
	}

	@Override
	public void afterRead(ArquivoLayout item) {
		System.out.println("MultiplosArquivosLayoutReaderConfig - afterRead");
		
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("MultiplosArquivosLayoutReaderConfig - onReadError");
		
	}
	
	private Resource[] getArquivosFromDatabase(FileIndiceRepository fileIndiceRepository) {
        List<FileIndice> nomesDosArquivos = fileIndiceRepository.findAll();
        Resource[] resources = new Resource[nomesDosArquivos.size()];

        for (int i = 0; i < nomesDosArquivos.size(); i++) {
            resources[i] = new FileSystemResource("src/main/resources/"+nomesDosArquivos.get(i).getNomeArquivo());
        }

        return resources;
    }


	private String getResourcesPath() {
		ClassLoader classLoader = MultiplosArquivosLayoutReaderConfig.class.getClassLoader();
		return classLoader.getResource("").getPath();
	}

}


