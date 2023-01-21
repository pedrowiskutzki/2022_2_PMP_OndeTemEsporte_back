package br.serratec.pmp.esporte.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.serratec.pmp.esporte.model.Pessoa;
import br.serratec.pmp.esporte.service.LoginService;
import br.serratec.pmp.esporte.service.PessoaService;
import br.serratec.pmp.esporte.shared.PessoaDTO;
import br.serratec.pmp.esporte.view.model.pessoaLogin.LoginRequest;
import br.serratec.pmp.esporte.view.model.pessoaLogin.LoginResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private LoginService loginService;

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Lista dados da pessoa", notes = "Lista dados da pessoa")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista dados da pessoa"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<PessoaDTO> obterPorId(@PathVariable Long id) {
    PessoaDTO dto = pessoaService.obterPorId(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  @ApiOperation(value = "Lista todas as pessoas", notes = "Lista todas as pessoas")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista todas pessoas"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<List<PessoaDTO>> obterTodos() {
    List<PessoaDTO> dto = pessoaService.obterTodos();
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  @ApiOperation(value = "Cadastra pessoa", notes = "Cadastra pessoa")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cadastra pessoa"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<PessoaDTO> cadastrar(@Valid @RequestBody Pessoa pessoa) {
    PessoaDTO dto = pessoaService.cadastrar(pessoa);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId_pessoa()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  @ApiOperation(value = "Altera dados da pessoa", notes = "Altera dados da pessoa")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Altera dados da pessoa"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<PessoaDTO> atualizar(@Valid @PathVariable Long id, @RequestBody PessoaDTO pessoaDto) {
    pessoaDto = pessoaService.atualizar(id, pessoaDto);
    return ResponseEntity.ok(pessoaDto);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Deleta pessoa", notes = "Deleta pessoa")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Deleta pessoa"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    pessoaService.deletar(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest request){
    return loginService.logar(request.getEmail(), request.getPassword());
   }

}
