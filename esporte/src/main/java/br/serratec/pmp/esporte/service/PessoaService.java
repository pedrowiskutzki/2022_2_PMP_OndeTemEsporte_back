package br.serratec.pmp.esporte.service;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.serratec.pmp.esporte.exception.ResourceBadRequestException;
import br.serratec.pmp.esporte.exception.ResourceNotFoundException;
import br.serratec.pmp.esporte.model.Pessoa;
import br.serratec.pmp.esporte.repository.PessoaRepository;
import br.serratec.pmp.esporte.shared.PessoaDTO;


@Service
public class PessoaService {
    
	

    @Autowired
    private PessoaRepository pessoaRepositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	

    public List<PessoaDTO> obterTodos(){
        return pessoaRepositorio.findAll().stream().map(PessoaDTO:: new).collect(Collectors.toList());
    }

    public PessoaDTO obterPorId(Long id) {
		Optional<Pessoa> optPessoa = pessoaRepositorio.findById(id);

		if (optPessoa.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a pessoa com id " + id);
		}
		return new PessoaDTO(optPessoa.get());
	}
	public Pessoa obterPessoaPorId(Long id) {
		Optional<Pessoa> optPessoa = pessoaRepositorio.findById(id);

		if (optPessoa.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a pessoa com id " + id);
		}
		return optPessoa.get();
	}

	public Optional<Pessoa> obterPorEmail(String email){
			return pessoaRepositorio.findByEmail(email);
	}

    public PessoaDTO cadastrar(Pessoa pessoa) {
		pessoa.setPessoa_Id(null);
		validarNome(pessoa);

		if(obterPorEmail(pessoa.getEmail()).isPresent()){
			//exceptions informando que o usuário existe
			throw new InputMismatchException("Já existe um usuário com este e-mail: " + pessoa.getEmail());
		}
		//codificando a senha para não ficar público, gerando um hash
		
		String senha = passwordEncoder.encode(pessoa.getPassword());
		pessoa.setPassword(senha);

		return new PessoaDTO(pessoaRepositorio.save(pessoa));
	}

	public PessoaDTO atualizar(Long id, PessoaDTO PessoaDto) {
		try {
			Pessoa entity = pessoaRepositorio
			.findById(id)
			.orElseThrow(
			  () -> new ResourceNotFoundException("Pessoa nao encontrada")
			);
		  copyDtoToEntity(entity, PessoaDto);
		  entity = pessoaRepositorio.save(entity);
		  return new PessoaDTO(entity);
		} catch (EntityNotFoundException e) {
		  throw new ResourceNotFoundException("Recurso nao encontrado");
		}
	  }

    public void deletar(Long id) {
		pessoaRepositorio.deleteById(id);
	}

	private void copyDtoToEntity(Pessoa entity, PessoaDTO PessoaDto)  {
		entity.setName(PessoaDto.getName());
		entity.setCpf(PessoaDto.getCpf());
		entity.setEmail(PessoaDto.getEmail());
		entity.setPassword(PessoaDto.getPassword());
		entity.setPhone(PessoaDto.getPhone());
	}


    private void validarNome(Pessoa Pessoa) {
		if (Pessoa.getName().isBlank() || Pessoa.getName() == null) {
			throw new ResourceBadRequestException("O nome deve ser informado");
		} else if (Pessoa.getName().length() > 35) {
			throw new ResourceBadRequestException("Tamanho máximo de 35 caracteres no nome");
		}

    }

	
}
