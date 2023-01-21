package br.serratec.pmp.esporte.view.model;

import java.util.Date;

import br.serratec.pmp.esporte.model.Categoria;
import br.serratec.pmp.esporte.model.CentroEsportivo;

public class EventoRequest {
    
    private Categoria categoriaId;
    private CentroEsportivo centroEsportivoId;
    private Date data;


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

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }


}
