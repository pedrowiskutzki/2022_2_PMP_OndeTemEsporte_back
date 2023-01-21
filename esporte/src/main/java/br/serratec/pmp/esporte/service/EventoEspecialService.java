package br.serratec.pmp.esporte.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.serratec.pmp.esporte.exception.ResourceBadRequestException;
import br.serratec.pmp.esporte.exception.ResourceNotFoundException;
import br.serratec.pmp.esporte.model.EventoEspecial;
import br.serratec.pmp.esporte.repository.EventoEspecialRepository;
import br.serratec.pmp.esporte.shared.EventoEspecialDTO;

@Service
public class EventoEspecialService {	

	@Value("${app.caminhoImagens}")
  	private String caminhoImagens;

	@Autowired
	private EventoEspecialRepository eventoEspecialRepositorio;


	public List<EventoEspecialDTO> obterTodos() {
		return eventoEspecialRepositorio.findAll().stream().map(EventoEspecialDTO:: new).collect(Collectors.toList());
		
	}

	public EventoEspecialDTO obterPorId(Long id) {
		Optional<EventoEspecial> optEventoEspecial = eventoEspecialRepositorio.findById(id);

		if (optEventoEspecial.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a evento especial com id " + id);
		}
		return new EventoEspecialDTO(optEventoEspecial.get());
	}

	@Transactional
	public EventoEspecialDTO cadastrar(EventoEspecial eventoEspecial, MultipartFile arquivo) {
		validarNome(eventoEspecial);
		validarDescricao(eventoEspecial);

			eventoEspecial = eventoEspecialRepositorio.saveAndFlush(eventoEspecial);
			eventoEspecial = eventoEspecialRepositorio.findById(eventoEspecial.getId()).get();
		return new EventoEspecialDTO(eventoEspecial);

	}

	public EventoEspecialDTO atualizar(Long id, EventoEspecialDTO EventoEspecialDto, MultipartFile arquivo) {
		try {
			EventoEspecial entity = eventoEspecialRepositorio
					.findById(id)
					.orElseThrow(
							() -> new ResourceNotFoundException("Evento especial nao encontrada"));
			copyDtoToEntity(entity, EventoEspecialDto);
			entity = eventoEspecialRepositorio.save(entity);
			return new EventoEspecialDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso nao encontrado");
		}
	}

	public void deletar(Long id) {
		eventoEspecialRepositorio.deleteById(id);
	}

	private void copyDtoToEntity(EventoEspecial entity, EventoEspecialDTO EventoEspecialDto) {
		entity.setNome(EventoEspecialDto.getNome());
		entity.setDescricao(EventoEspecialDto.getDescricao());
		entity.setData(EventoEspecialDto.getData());
		entity.setTelefone(EventoEspecialDto.getTelefone());
		entity.setEmail(EventoEspecialDto.getEmail());
		entity.setCep(EventoEspecialDto.getCep());
		entity.setBairro(EventoEspecialDto.getBairro());
		entity.setComplemento(EventoEspecialDto.getComplemento());
		entity.setNumero(EventoEspecialDto.getNumero());
		entity.setRua(EventoEspecialDto.getRua());
		entity.setLatitude(EventoEspecialDto.getLatitude());
		entity.setLongitude(EventoEspecialDto.getLongitude());
		entity.setNomeImagem(EventoEspecialDto.getNomeImagem());

	}

	private void validarNome(EventoEspecial eventoEspecial) {
		if (eventoEspecial.getNome().isBlank() || eventoEspecial.getNome() == null) {
			throw new ResourceBadRequestException("O nome deve ser informado");
		} else if (eventoEspecial.getNome().length() > 30) {
			throw new ResourceBadRequestException("Tamanho máximo de 30 caracteres no nome");
		}

	}

	private void validarDescricao(EventoEspecial eventoEspecial) {
		if (eventoEspecial.getDescricao().length() > 150) {
			throw new ResourceBadRequestException("Tamanho máximo de 150 caracteres na descrição");
		}

	}

}
