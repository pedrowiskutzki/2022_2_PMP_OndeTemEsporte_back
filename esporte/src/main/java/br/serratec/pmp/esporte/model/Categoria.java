package br.serratec.pmp.esporte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Long id;

    @NotNull
    @Column(name = "Modal",length = 25, nullable = false)
    private String modal;

    @NotNull
    @Column(name = "Descricao", length = 150, nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "categoria")
    @JsonBackReference
    private List<CentroEsportivo> centroEsportivo = new ArrayList<CentroEsportivo>() ;

   
    public List<CentroEsportivo> getCentroEsportivo() {
        return centroEsportivo;
    }
    public void setCentroEsportivo(List<CentroEsportivo> centroEsportivo) {
        this.centroEsportivo = centroEsportivo;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getModal() {
        return modal;
    }
    public void setModal(String modal) {
        this.modal = modal;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
