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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.PageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.serratec.pmp.esporte.exception.ResourceBadRequestException;
import br.serratec.pmp.esporte.exception.ResourceNotFoundException;
import br.serratec.pmp.esporte.model.CentroEsportivo;
import br.serratec.pmp.esporte.repository.CentroEsportivoRepository;
import br.serratec.pmp.esporte.shared.CentroEsportivoDTO;

@Service
public class CentroEsportivoService {

	@Value("${app.caminhoImagens1}")
  	private String caminhoImagens1;
    
    @Autowired
    private CentroEsportivoRepository centroEsportivoRepositorio;

	@Transactional
    public List<CentroEsportivoDTO> obterTodos(){
        return centroEsportivoRepositorio.findAll().stream().map(CentroEsportivoDTO:: new).collect(Collectors.toList());
    }

	@Transactional
    public CentroEsportivoDTO obterPorId(Long id) {
		Optional<CentroEsportivo> optCentroEsportivo = centroEsportivoRepositorio.findById(id);

		if (optCentroEsportivo.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a centro esportivo com id " + id);
		}
		return new CentroEsportivoDTO (optCentroEsportivo.get());
	}

	@Transactional
    public CentroEsportivoDTO cadastrar(CentroEsportivo centroEsportivo, MultipartFile arquivo) {
		validarNome(centroEsportivo);

		
		  
		
			centroEsportivo = centroEsportivoRepositorio.saveAndFlush(centroEsportivo);
			centroEsportivo = centroEsportivoRepositorio.findById(centroEsportivo.getId()).get();
		return new CentroEsportivoDTO(centroEsportivo);
	}

	

	@Transactional
	public CentroEsportivoDTO atualizar(Long id, CentroEsportivoDTO CentroEsportivoDto, MultipartFile arquivo) {
		try {
			CentroEsportivo entity = centroEsportivoRepositorio
			.findById(id)
			.orElseThrow(
			  () -> new ResourceNotFoundException("Centro esportivo nao encontrada")
			);
		  copyDtoToEntity(entity, CentroEsportivoDto);
		  entity = centroEsportivoRepositorio.save(entity);
		  return new CentroEsportivoDTO(entity);
		} catch (EntityNotFoundException e) {
		  throw new ResourceNotFoundException("Recurso nao encontrado");
		}
	  }

	@Transactional
    public void deletar(Long id) {
		centroEsportivoRepositorio.deleteById(id);
	}

	private void copyDtoToEntity(CentroEsportivo entity, CentroEsportivoDTO CentroEsportivoDto)  {
		entity.setNome(CentroEsportivoDto.getNome());
		entity.setBairro(CentroEsportivoDto.getBairro());
		entity.setCategoria(CentroEsportivoDto.getCategoria());
		entity.setCep(CentroEsportivoDto.getCep());
		entity.setCidade(CentroEsportivoDto.getCidade());
		entity.setComplemento(CentroEsportivoDto.getComplemento());
		entity.setId(CentroEsportivoDto.getId());
		entity.setLatitude(CentroEsportivoDto.getLatitude());
		entity.setLongitude(CentroEsportivoDto.getLongitude());
		entity.setNumero(CentroEsportivoDto.getNumero());
		entity.setRua(CentroEsportivoDto.getRua());
		entity.setNomeImagem(CentroEsportivoDto.getNomeImagem());
			
		
	  }

    private void validarNome(CentroEsportivo centroEsportivo) {
		if (centroEsportivo.getNome().isBlank() || centroEsportivo.getNome() == null) {
			throw new ResourceBadRequestException("O nome deve ser informado");
		} else if (centroEsportivo.getNome().length() > 30) {
			throw new ResourceBadRequestException("Tamanho máximo de 30 caracteres no nome");
		}

	}

	   
}
