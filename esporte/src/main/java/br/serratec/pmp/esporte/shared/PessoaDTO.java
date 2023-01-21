package br.serratec.pmp.esporte.shared;

import org.springframework.security.core.userdetails.UserDetails;

import br.serratec.pmp.esporte.model.Pessoa;

public class PessoaDTO {
    
    private Long id_pessoa;
    private String name;
    private String cpf;
    private String password;
    private String phone;
    private String email;

public PessoaDTO() {}

public PessoaDTO(
    Long id_pessoa,
    String name,
    String cpf,
    String password,
    String phone,
    String email
){
    this.id_pessoa= id_pessoa;
    this.name = name;
    this.cpf = cpf;
    this.password = password;
    this.phone = phone;
    this.email = email;
}

public PessoaDTO(Pessoa pessoa){
    id_pessoa = pessoa.getPessoa_Id();
    name = pessoa.getName();
    cpf = pessoa.getCpf();
    password = pessoa.getPassword();
    phone = pessoa.getPhone();
    email = pessoa.getEmail();
    }

    public Long getId_pessoa() {
        return this.id_pessoa;
    }

    public void setId_pessoa(Long id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetails get() {
        return null;
    }


}