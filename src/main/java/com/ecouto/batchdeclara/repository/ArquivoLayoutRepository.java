package com.ecouto.batchdeclara.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Repository
public interface ArquivoLayoutRepository extends JpaRepository<ArquivoLayout, Long>{

	public List<ArquivoLayout> findByStatusEnvio(Integer statusEnvio);
}
