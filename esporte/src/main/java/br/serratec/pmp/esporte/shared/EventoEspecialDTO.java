package br.serratec.pmp.esporte.shared;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import br.serratec.pmp.esporte.model.EventoEspecial;

public class EventoEspecialDTO {
    
    private Long id;
    private String nome;
    private String descricao;
  
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT-3" )
    private Date data;
    private String telefone;
    private String email;
    private String cep;
    private String rua;
    private String bairro;
    private String complemento;
    private String numero;
    private String longitude;
    private String latitude;
    private String nomeImagem;


    public EventoEspecialDTO(){

    }
    public EventoEspecialDTO(
        Long id,
        String nome,
        String descricao,
        Date data,
        String bairro,
        String cep,
        String rua,
        String complemento,
        String numero,
        String telefone,
        String email,
        String longitude,
        String latitude,
        String nomeImagem
    ){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nomeImagem = nomeImagem;
    }

    public EventoEspecialDTO(EventoEspecial evento){
        id = evento.getId();
        nome = evento.getNome();
        descricao = evento.getDescricao();
        data = evento.getData();
        telefone = evento.getTelefone();
        email = evento.getEmail();
        cep = evento.getCep();
        bairro = evento.getBairro();
        rua = evento.getRua();
        complemento = evento.getComplemento();
        numero = evento.getNumero();
        longitude = evento.getLongitude();
        latitude = evento.getLatitude();
        nomeImagem = evento.getNomeImagem();
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

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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


}
