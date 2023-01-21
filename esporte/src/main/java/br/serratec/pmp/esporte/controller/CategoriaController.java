package br.serratec.pmp.esporte.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.serratec.pmp.esporte.model.Categoria;
import br.serratec.pmp.esporte.service.CategoriaService;
import br.serratec.pmp.esporte.shared.CategoriaDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Lista a categoria por Id", notes = "Lista categoria")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Retorna categoria"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })

  public ResponseEntity<CategoriaDTO> obterPorId(@PathVariable Long id) {
    CategoriaDTO dto = categoriaService.obterPorId(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  @ApiOperation(value = "Lista todas as categorias ", notes = "Lista todas as categorias")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Retorna todas as categorias"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<List<CategoriaDTO>> obterTodos() {
    List<CategoriaDTO> dto = categoriaService.obterTodos();
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  @ApiOperation(value = "Cadastra categoria", notes = "Cadastra categoria")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Sucesso no cadastro"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<CategoriaDTO> cadastrar(@Valid @RequestBody Categoria categoria) {
    CategoriaDTO dto = categoriaService.cadastrar(categoria);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  @ApiOperation(value = "Altera dados da categoria", notes = "Altera dados categoria")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Sucesso na alteração dos dados de categoria"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<CategoriaDTO> atualizar(@Valid @PathVariable Long id, @RequestBody CategoriaDTO categoriaDto) {
    categoriaDto = categoriaService.atualizar(id, categoriaDto);
    return ResponseEntity.ok(categoriaDto);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Exclui categoria", notes = "Exclui categoria")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Exclui categoria"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    categoriaService.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
