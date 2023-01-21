package br.serratec.pmp.esporte.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.serratec.pmp.esporte.model.EventoEspecial;
import br.serratec.pmp.esporte.service.EventoEspecialService;
import br.serratec.pmp.esporte.shared.EventoEspecialDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/eventoEspecial")
public class EventoEspecialController {

  @Value("${app.caminhoImagens}")
  private String caminhoImagens;

  @Autowired
  private EventoEspecialService eventoEspecialService;

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Lista Evento Especial", notes = "Lista Evento Especial")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista Evento especial"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<EventoEspecialDTO> obterPorId(@PathVariable Long id) {
    EventoEspecialDTO dto = eventoEspecialService.obterPorId(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  @ApiOperation(value = "Lista todos os Eventos Especiais", notes = "Lista todos os Eventos Especiais")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista Eventos Especiais"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<List<EventoEspecialDTO>> obterTodos() {
    List<EventoEspecialDTO> dto = eventoEspecialService.obterTodos();
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/mostrarImagem/{imagem}")
  public ResponseEntity<byte[]> retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
    System.out.println(imagem);
    File imagemArquivo = new File(caminhoImagens + imagem);
    if (imagem != null || imagem.trim().length() > 0) {

      byte[] data = Files.readAllBytes(imagemArquivo.toPath());
      InputStream is = new BufferedInputStream(new ByteArrayInputStream(data));
      String mimeType = URLConnection.guessContentTypeFromStream(is);

      HttpHeaders headers = new HttpHeaders();
      headers.add("content-type", mimeType);
      headers.add("content-length", String.valueOf(data.length));
      return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
    return null;
  }

  @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
  @ResponseBody
  @ApiOperation(value = "Cadastra Evento Especial", notes = "Cadastra Evento Especial")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cadastra Evento Especial"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<EventoEspecialDTO> cadastrar(
      @Valid @RequestPart("eventoEspecial") EventoEspecial eventoEspecial,
      @RequestParam("file") MultipartFile arquivo) {
    EventoEspecialDTO dto = eventoEspecialService.cadastrar(eventoEspecial, arquivo);
    
    try {
			if (!arquivo.isEmpty()) {
			  byte[] bytes = arquivo.getBytes();
			  Path caminho = Paths
				  .get(caminhoImagens + String.valueOf(eventoEspecial.getId()) + arquivo.getOriginalFilename());
			  Files.write(caminho, bytes);
	  
			  eventoEspecial.setNomeImagem(String.valueOf(eventoEspecial.getId()) + arquivo.getOriginalFilename());
        
        dto = eventoEspecialService.cadastrar(eventoEspecial, arquivo);
			}	  
		  } catch (IOException e) {
			e.printStackTrace();
		  }

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(eventoEspecialService.obterPorId(dto.getId()));
  }

  @Transactional
  @PutMapping(value = "/{id}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
  @ApiOperation(value = "Altera dados do Evento Especial", notes = "Altera dados do Evento Especial")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Altera Evento Especial"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<EventoEspecialDTO> atualizar(@Valid @PathVariable Long id,
      @Valid @RequestPart("eventoEspecial") EventoEspecialDTO eventoEspecialDto,
      @RequestParam("file") MultipartFile arquivo) {
    // eventoEspecialDto = eventoEspecialService.atualizar(id, eventoEspecialDto, arquivo);
    try {
			if (!arquivo.isEmpty()) {
			  byte[] bytes = arquivo.getBytes();
			  Path caminho = Paths
				  .get(caminhoImagens + String.valueOf(id) + arquivo.getOriginalFilename());
			  Files.write(caminho, bytes);
	  
			  eventoEspecialDto.setNomeImagem(String.valueOf(id) + arquivo.getOriginalFilename());
        
        eventoEspecialDto = eventoEspecialService.atualizar(id, eventoEspecialDto, arquivo);
			}	  
		  } catch (IOException e) {
			e.printStackTrace();
		  }
    return ResponseEntity.ok(eventoEspecialDto);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Deleta Evento Especial", notes = "Deleta Evento Especial")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Deleta Evento Especial"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    eventoEspecialService.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
