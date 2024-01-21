package com.ecouto.batchdeclara.arquivolayout.reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ecouto.batchdeclara.model.ArquivoLayout;
import com.ecouto.batchdeclara.model.XMLGerado;
import com.ecouto.batchdeclara.repository.ArquivoLayoutRepository;

@Component
public class MultiplosArquivosLayoutReaderConfig {

	
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	ArquivoLayoutRepository arquivoLayoutRepository;

	@SuppressWarnings({"rawtypes", "unchecked" })
	public ItemReader<ArquivoLayout> multiplosArquivosLayoutReader(FlatFileItemReader<XMLGerado> reader) {
		
		return new MultiResourceItemReaderBuilder()
				.name("multiplosArquivosLayoutReader")
				.resources(getArquivosFromDatabase())
				.delegate(new LeituraMultiplosArquivosReader(reader))
				.build();
	}
	
	
	private Resource[] getArquivosFromDatabase() {
		//System.out.println("getArquivosFromDatabase");
		
		List<ArquivoLayout> nomesDosArquivos = jdbcTemplate.query("select id,nome_arquivo from arquivo_layout", new RowMapper<ArquivoLayout>() {

			@Override
			public ArquivoLayout mapRow(ResultSet rs, int rowNum) throws SQLException {
				ArquivoLayout arquivoLayout = new ArquivoLayout();
				arquivoLayout.setId(rs.getLong(1));
				arquivoLayout.setNomeArquivo(rs.getString(2));
				return arquivoLayout;
			}
			
		});
		
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


