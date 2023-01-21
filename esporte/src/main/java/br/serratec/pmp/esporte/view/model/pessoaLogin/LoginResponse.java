package br.serratec.pmp.esporte.view.model.pessoaLogin;

import br.serratec.pmp.esporte.model.Pessoa;

public class LoginResponse {
    
    private String token;
    private Pessoa pessoa;

    public LoginResponse() {
    }

    public LoginResponse(String token, Pessoa pessoa) {
        this.token = token;
        this.pessoa = pessoa;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    
    

    
}
