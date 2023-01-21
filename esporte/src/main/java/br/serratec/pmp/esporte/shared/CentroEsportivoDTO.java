package br.serratec.pmp.esporte.shared;

import java.util.List;

import br.serratec.pmp.esporte.model.Categoria;
import br.serratec.pmp.esporte.model.CentroEsportivo;

public class CentroEsportivoDTO {
    
    private Long id;
    private String nome;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private String longitude;
    private String latitude;
    private String telefone;
    private String email;
    private List<Categoria> categoria;
    private String nomeImagem;

    public CentroEsportivoDTO(){

    }
    public CentroEsportivoDTO(
        Long id,
        String nome,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String cep,
        String telefone,
        String email,
        String longitude,
        String latitude,
        List<Categoria> categoria,
        String nomeImagem
    ){
        this.id = id;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
        this.longitude = longitude;
        this.latitude = latitude;
        this.categoria = categoria;
        this.nomeImagem = nomeImagem;
    }

    public CentroEsportivoDTO(CentroEsportivo entity){
        id = entity.getId();
        nome = entity.getNome();
        rua = entity.getRua();
        numero = entity.getNumero();
        complemento = entity.getComplemento();
        bairro = entity.getBairro();
        cidade = entity.getCidade();
        cep = entity.getCep();
        telefone = entity.getTelefone();
        email = entity.getEmail();
        longitude = entity.getLongitude();
        latitude = entity.getLatitude();
        categoria = entity.getCategoria();
        nomeImagem = entity.getNomeImagem();
    }



    public String getNomeImagem() {
        return nomeImagem;
    }
    
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<Categoria> getCategoria() {
        return this.categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
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
