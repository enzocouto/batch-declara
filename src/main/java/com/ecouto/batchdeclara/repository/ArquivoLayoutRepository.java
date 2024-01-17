package com.ecouto.batchdeclara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecouto.batchdeclara.model.ArquivoLayout;

@Repository
public interface ArquivoLayoutRepository extends JpaRepository<ArquivoLayout, Long>{

	
}
