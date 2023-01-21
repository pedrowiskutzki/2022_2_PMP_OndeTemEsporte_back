package br.serratec.pmp.esporte.shared;

import br.serratec.pmp.esporte.model.Categoria;

public class CategoriaDTO {
    
    private Long id;
    private String modal;
    private String descricao;

public CategoriaDTO(){}

public CategoriaDTO(
    long id,
    String modal,
    String descricao
){
    this.id = id;
    this.modal = modal;
    this.descricao= descricao;
}

public CategoriaDTO(Categoria categoria){
    id = categoria.getId();
    modal = categoria.getModal();
    descricao = categoria.getDescricao();
}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

}