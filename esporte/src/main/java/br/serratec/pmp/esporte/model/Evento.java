package br.serratec.pmp.esporte.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "evento")
public class Evento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEvento")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoriaId;

    @ManyToOne
    @JoinColumn(name="centroEsportivoId")  
    private CentroEsportivo centroEsportivoId;


    @Column(name = "Data", nullable = false)
    private LocalDate data;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public CentroEsportivo getCentroEsportivoId() {
        return centroEsportivoId;
    }

    public void setCentroEsportivoId(CentroEsportivo centroEsportivoId) {
        this.centroEsportivoId = centroEsportivoId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}
