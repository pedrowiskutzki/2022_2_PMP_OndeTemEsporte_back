package br.serratec.pmp.esporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.serratec.pmp.esporte.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
}
