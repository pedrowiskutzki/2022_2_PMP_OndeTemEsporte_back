package br.serratec.pmp.esporte.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.serratec.pmp.esporte.exception.ResourceBadRequestException;
import br.serratec.pmp.esporte.exception.ResourceNotFoundException;
import br.serratec.pmp.esporte.model.Categoria;
import br.serratec.pmp.esporte.repository.CategoriaRepository;
import br.serratec.pmp.esporte.shared.CategoriaDTO;

@Service
public class CategoriaService {
    
    @Autowired
	private CategoriaRepository categoriaRepositorio;

	public List<CategoriaDTO> obterTodos() {
		return categoriaRepositorio.findAll().stream().map(CategoriaDTO:: new).collect(Collectors.toList());
	}

	public CategoriaDTO obterPorId(Long id) {
		Optional<Categoria> optCategoria = categoriaRepositorio.findById(id);

		if (optCategoria.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a categoria com id " + id);
		}
		return new CategoriaDTO(optCategoria.get()) ;
	}

	public CategoriaDTO cadastrar(Categoria categoria) {
		validarNome(categoria);
		validarDescricao(categoria);
		return new CategoriaDTO(categoriaRepositorio.save(categoria));
	}
	
	public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaDto) {
		try {
		  Categoria entity = categoriaRepositorio
			.findById(id)
			.orElseThrow(
			  () -> new ResourceNotFoundException("Categoria nao encontrada")
			);
		  copyDtoToEntity(entity, categoriaDto);
		  entity = categoriaRepositorio.save(entity);
		  return new CategoriaDTO(entity);
		} catch (EntityNotFoundException e) {
		  throw new ResourceNotFoundException("Recurso nao encontrado");
		}
	  }

	public void deletar(Long id) {
		categoriaRepositorio.deleteById(id);
	}

	private void copyDtoToEntity(Categoria entity, CategoriaDTO categoriaDto)  {
		entity.setModal(categoriaDto.getModal());
		entity.setDescricao(categoriaDto.getDescricao());
	  }

	private void validarNome(Categoria categoria) {
		if (categoria.getModal().isBlank() || categoria.getModal() == null) {
			throw new ResourceBadRequestException("O modal deve ser informado");
		} else if (categoria.getModal().length() > 30) {
			throw new ResourceBadRequestException("Tamanho máximo de 30 caracteres no nome");
		}

	}

	private void validarDescricao(Categoria categoria) {
		if (categoria.getDescricao().length() > 150) {
			throw new ResourceBadRequestException("Tamanho máximo de 150 caracteres na descrição");
		}

	}
}
