package br.serratec.pmp.esporte.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.serratec.pmp.esporte.model.Evento;
import br.serratec.pmp.esporte.service.EventoService;
import br.serratec.pmp.esporte.shared.EventoDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/evento")
public class EventoController {

  @Autowired
  private EventoService eventoService;

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Lista Evento", notes = "Lista Evento")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista evento"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<EventoDTO> obterPorId(@PathVariable Long id) {
    EventoDTO dto = eventoService.obterPorId(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  @ApiOperation(value = "Lista todos os eventos", notes = "Lista todos os eventos")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista eventos"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<List<EventoDTO>> obterTodos() {
    List<EventoDTO> dto = eventoService.obterTodos();
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  @ApiOperation(value = "Cadastra evento", notes = "Cadastra evento")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cadastra evento"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<EventoDTO> cadastrar(@Valid @RequestBody Evento evento) {
    EventoDTO dto = eventoService.cadastrar(evento);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  @ApiOperation(value = "Altera dados do evento", notes = "Altera dados do evento")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Altera evento"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<EventoDTO> atualizar(@Valid @PathVariable Long id, @RequestBody EventoDTO eventoDto) {
    eventoDto = eventoService.atualizar(id, eventoDto);
    return ResponseEntity.ok(eventoDto);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Deleta evento", notes = "Deleta evento")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Deleta evento"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    eventoService.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
