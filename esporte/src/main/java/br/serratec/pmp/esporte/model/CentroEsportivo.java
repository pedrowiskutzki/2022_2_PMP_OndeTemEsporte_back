package br.serratec.pmp.esporte.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.ResponseBody;


@Entity
@ResponseBody
@Table(name = "centroEsportivo")
public class CentroEsportivo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCentroEsportivo")
    private Long id;

    @NotNull
    @Column(name = "Nome", length = 100, nullable = false)
    private String nome;
    
    @NotNull
    @Column(length = 70, nullable = false)
    private String rua;

   
    @Column(length = 9, nullable = true)
    private String numero;

    @Column(length = 30, nullable = true)
    private String complemento;

    @NotNull
    @Column(length = 50, nullable = false)
    private String bairro;

    @NotNull
    @Column(length = 30, nullable = false)
    private String cidade;

   
    @Column(length = 9, nullable = false)
    private String cep;
    
    @Column(name = "Telefone", length = 15)
    private String telefone;
        
    @Email
    @Column(name = "Email", length = 40)
    private String email;
    
    @NotNull
    @Column(length = 30)
    private String longitude;

    @NotNull
    @Column(length = 30)
    private String latitude;
    

    @ManyToMany
    @JoinTable(name = "CentroEsportivoCategoria",
            joinColumns = @JoinColumn(name = "IdCentroEsportivo"),
            inverseJoinColumns = @JoinColumn(name="idCategoria"))
    private List<Categoria> categoria = new ArrayList<Categoria>() ;

    private String nomeImagem;



   
    public String getNomeImagem() {
        return nomeImagem;
    }
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
    
    public List<Categoria> getCategoria() {
        return categoria;
    }
    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;

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
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
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
    
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    


}
