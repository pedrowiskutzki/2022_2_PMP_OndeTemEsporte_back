package br.serratec.pmp.esporte.repository;

import org.springframework.stereotype.Repository;

import br.serratec.pmp.esporte.model.CentroEsportivo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CentroEsportivoRepository extends JpaRepository <CentroEsportivo, Long>{
    
}
