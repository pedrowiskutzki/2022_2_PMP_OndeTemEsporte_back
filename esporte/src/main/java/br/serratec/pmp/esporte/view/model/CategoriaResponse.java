package br.serratec.pmp.esporte.view.model;

import java.util.List;

import br.serratec.pmp.esporte.model.CentroEsportivo;

public class CategoriaResponse {
    
    private String id;

    private String modal;

    private String descricao;

    private List<CentroEsportivo> centro_esportivo;

    

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModal() {
        return this.modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<CentroEsportivo> getCentro_esportivo() {
        return this.centro_esportivo;
    }

    public void setCentro_esportivo(List<CentroEsportivo> centro_esportivo) {
        this.centro_esportivo = centro_esportivo;
    }

}
