package br.serratec.pmp.esporte.security;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.serratec.pmp.esporte.model.Pessoa;


public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken implements UserDetails{
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;

	public CustomUsernamePasswordAuthenticationToken(String username, String password, Pessoa pessoa) {
		super(username, password, Arrays.asList(new SimpleGrantedAuthority("USER")));
		this.pessoa = pessoa;
		
		
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return pessoa.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	

	
	
	

}
