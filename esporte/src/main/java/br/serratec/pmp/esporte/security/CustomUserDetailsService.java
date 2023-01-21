package br.serratec.pmp.esporte.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import br.serratec.pmp.esporte.service.PessoaService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private PessoaService pessoaService;

    public UserDetails loadUserByUsername (String email) {
        return pessoaService.obterPorEmail(email).get();    
    
    }

    public UserDetails obterPessoaPorId (Long id) {
        return pessoaService.obterPessoaPorId(id);
    }

}
