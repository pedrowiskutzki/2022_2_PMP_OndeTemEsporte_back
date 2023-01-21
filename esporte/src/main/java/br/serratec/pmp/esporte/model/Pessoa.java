package br.serratec.pmp.esporte.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Table(name = "Pessoa" )
public class Pessoa implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pessoa_id")
    private Long pessoa_id;

    @Column(nullable = false, length = 35)
    private String name;

    @Column(unique = false)
    private String cpf;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = true, length = 11)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;
    
    public Pessoa() {
    }

    public Pessoa(Long pessoa_id, String name, String cpf, String password, String phone, String email) {
        this.pessoa_id = pessoa_id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public Pessoa(String name, String cpf, String password, String phone, String email) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }



    public Long getPessoa_Id() {
        return pessoa_id;
    }
    public void setPessoa_Id(Long user_Id) {
        this.pessoa_id = user_Id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //Daqui pra baixo é implementação de UserDetails

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return Arrays.asList(new SimpleGrantedAuthority("USER"));

    }

    @JsonIgnore
    public String getPassword1() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
       return true;
    }



}
