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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.serratec.pmp.esporte.model.CentroEsportivo;
import br.serratec.pmp.esporte.service.CentroEsportivoService;
import br.serratec.pmp.esporte.shared.CentroEsportivoDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/centroEsportivo")
public class CentroEsportivoController {

  @Value("${app.caminhoImagens1}")
  private String caminhoImagens1;
  
  @Autowired
  private CentroEsportivoService centroEsportivoService;
  
  @GetMapping
  @ApiOperation(value = "Lista todos os centros esportivos", notes = "Lista todos os centros esportivos")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista centros esportivos"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<List<CentroEsportivoDTO>> obterTodos() {
    List<CentroEsportivoDTO> dto = centroEsportivoService.obterTodos();
    return ResponseEntity.ok(dto);
  }

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Lista centro esportivo", notes = "Lista centro esportivo")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista centro esportivo"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<CentroEsportivoDTO> obterPorId(@PathVariable Long id) {
    CentroEsportivoDTO dto = centroEsportivoService.obterPorId(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/mostrarImagem/{imagem}")
  public ResponseEntity<byte[]> retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
    System.out.println(imagem);
    File imagemArquivo = new File(caminhoImagens1 + imagem);
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
  @ApiOperation(value = "Cadastra centro esportivo", notes = "Cadastra centro esportivo")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cadastra centro esportivo"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<CentroEsportivoDTO> cadastrar(
      @Valid @RequestPart("centroEsportivo") CentroEsportivo centroEsportivo,
      @RequestParam("file") MultipartFile arquivo) {
    CentroEsportivoDTO dto = centroEsportivoService.cadastrar(centroEsportivo, arquivo);

    try {
			if (!arquivo.isEmpty()) {
			  byte[] bytes = arquivo.getBytes();
			  Path caminho = Paths
				  .get(caminhoImagens1 + String.valueOf(centroEsportivo.getId()) + arquivo.getOriginalFilename());
			  Files.write(caminho, bytes);
	  
			  centroEsportivo.setNomeImagem(String.valueOf(centroEsportivo.getId()) + arquivo.getOriginalFilename());
			  
			  dto = centroEsportivoService.cadastrar(centroEsportivo, arquivo);
			}
	  
		  } catch (IOException e) {
			e.printStackTrace();
		  }
    
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @Transactional
  @PutMapping(value = "/{id}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
  @ApiOperation(value = "Altera dados dos centros esportivos", notes = "Altera dados dos centros esportivos")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Altera dados dos centros esportivos"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<CentroEsportivoDTO> atualizar(@Valid @PathVariable Long id,
  @Valid @RequestPart("centroEsportivo") CentroEsportivoDTO centroEsportivoDto,
  @RequestParam("file") MultipartFile arquivo) {
// eventoEspecialDto = eventoEspecialService.atualizar(id, eventoEspecialDto, arquivo);
try {
  if (!arquivo.isEmpty()) {
    byte[] bytes = arquivo.getBytes();
    Path caminho = Paths
      .get(caminhoImagens1 + String.valueOf(id) + arquivo.getOriginalFilename());
    Files.write(caminho, bytes);

    centroEsportivoDto.setNomeImagem(String.valueOf(id) + arquivo.getOriginalFilename());
    
    centroEsportivoDto = centroEsportivoService.atualizar(id, centroEsportivoDto, arquivo);
  }	  
  } catch (IOException e) {
  e.printStackTrace();
  }
return ResponseEntity.ok(centroEsportivoDto);
}

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Deleta centro esportivo", notes = "Deleta centro esportivo")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Deleta centro esportivo"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    centroEsportivoService.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
