package br.serratec.pmp.esporte.shared;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.serratec.pmp.esporte.model.Categoria;
import br.serratec.pmp.esporte.model.CentroEsportivo;
import br.serratec.pmp.esporte.model.Evento;

public class EventoDTO {
    
    private Long id;
    private Categoria categoriaId;
    private CentroEsportivo centroEsportivoId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    public EventoDTO(){

    }
    public EventoDTO(
        Long id,
        Categoria categoriaId,
        CentroEsportivo centroEsportivoId,
        LocalDate data 
    ){
        this.id = id;
        this.categoriaId = categoriaId;
        this.centroEsportivoId = centroEsportivoId;
        this.data = data;
    }

    public EventoDTO(Evento evento){
        id = evento.getId();
        categoriaId = evento.getCategoriaId();
        centroEsportivoId = evento.getCentroEsportivoId();
        data = evento.getData();

    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public CentroEsportivo getCentroEsportivoId() {
        return this.centroEsportivoId;
    }

    public void setCentroEsportivoId(CentroEsportivo centroEsportivoId) {
        this.centroEsportivoId = centroEsportivoId;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}
