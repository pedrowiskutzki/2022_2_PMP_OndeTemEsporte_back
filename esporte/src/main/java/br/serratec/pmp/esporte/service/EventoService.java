package br.serratec.pmp.esporte.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.serratec.pmp.esporte.exception.ResourceNotFoundException;
import br.serratec.pmp.esporte.model.Evento;
import br.serratec.pmp.esporte.repository.EventoRepository;
import br.serratec.pmp.esporte.shared.EventoDTO;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepositorio;

	public List<EventoDTO> obterTodos(){
        return eventoRepositorio.findAll().stream().map(EventoDTO:: new).collect(Collectors.toList());
    }

    public EventoDTO obterPorId(Long id){
        Optional<Evento> optEvento = eventoRepositorio.findById(id);

        if (optEvento.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a evento com id " + id);
		}
		return new EventoDTO(optEvento.get());
    } 

    public EventoDTO cadastrar(Evento evento) {
		
		return new EventoDTO(eventoRepositorio.save(evento));
	}

  public EventoDTO atualizar(Long id, EventoDTO eventoDto) {
		try {
		  Evento entity = eventoRepositorio
			.findById(id)
			.orElseThrow(
			  () -> new ResourceNotFoundException("Evento nao encontrada")
			);
		  copyDtoToEntity(entity, eventoDto);
		  entity = eventoRepositorio.save(entity);
		  return new EventoDTO(entity);
		} catch (EntityNotFoundException e) {
		  throw new ResourceNotFoundException("Recurso nao encontrado");
		}
	  }

    public void deletar(Long id) {
		eventoRepositorio.deleteById(id);
	}

  private void copyDtoToEntity(Evento entity, EventoDTO eventoDTO)  {
    entity.setCategoriaId(eventoDTO.getCategoriaId());
    entity.setCentroEsportivoId(eventoDTO.getCentroEsportivoId());
    entity.setData(eventoDTO.getData());

    }

    
}



