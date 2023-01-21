package br.serratec.pmp.esporte.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;




@Entity
@Table(name="EventoEspecial")
public class EventoEspecial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventoEspecialId")
    private Long id;
    
    @NotNull
    @Column(name = "Nome", length = 30, nullable = false )
    private String nome;

    @NotNull
    @Column(name = "Descrição", length = 150, nullable = false)
    private String descricao;
    
    
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3" )
    @Column(name = "Data")
    private Date data;
    
    @Column(name = "Telefone", length = 10)
    private String telefone;
        
    @Email
    @Column(name = "Email", length = 40)
    private String email;
    
    @NotNull
    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "bairro", length = 25, nullable = false)
    private String bairro;
    

    @Column(name = "complemento", length = 20, nullable = false)
    private String complemento;
   
    @Column(name = "numero", length = 6, nullable = true)
    private String numero;

    @NotNull
    @Column(name = "logradouro", length = 40, nullable = false)
    private String rua;

    @NotNull
    @Column(length = 15)
    private String latitude;
    
    @NotNull
    @Column(length = 15)
    private String longitude;
    
    private String nomeImagem;
   
    
   
    public String getNomeImagem() {
        return nomeImagem;
    }
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
   
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
    
    
      

}
