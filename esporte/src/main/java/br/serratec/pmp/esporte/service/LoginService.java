package br.serratec.pmp.esporte.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import br.serratec.pmp.esporte.model.Pessoa;
import br.serratec.pmp.esporte.repository.PessoaRepository;
import br.serratec.pmp.esporte.security.JWTService;
import br.serratec.pmp.esporte.view.model.pessoaLogin.LoginResponse;


@Service
public class LoginService{

    @Autowired
    private PessoaRepository pessoaRepositorio;

    @Autowired
	private JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;



public LoginResponse logar(String email, String senha){

    Authentication autenticacao = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email, senha));

    SecurityContextHolder.getContext().setAuthentication(autenticacao);

    String token = jwtService.gerarToken(autenticacao);

    Pessoa pessoa = pessoaRepositorio.findByEmail(email).get();

    return new LoginResponse(token, pessoa);

}
}