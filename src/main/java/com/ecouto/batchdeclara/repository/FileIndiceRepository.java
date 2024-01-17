package com.ecouto.batchdeclara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecouto.batchdeclara.model.FileIndice;

@Repository
public interface FileIndiceRepository extends JpaRepository<FileIndice, Long> {

}
