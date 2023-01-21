package br.serratec.pmp.esporte.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //Informo que é uma classe de segurança do WebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;
   

    @Override
   public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder);
   }  
   
   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception{

        return super.authenticationManagerBean();

   }

   //metodo com configuração global de acesso as
   @Override
   protected void configure(HttpSecurity http) throws Exception {

    //Parte padrão da configuração
        http    
            .cors().and().csrf().disable()
            .exceptionHandling()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/pessoa/login")
            .permitAll()
            /**
             * Daqui pra baixo declaramos as rotas que precisarão de autenticação
             */
            //│ HttpMethod.POST, "/Home", "/Calendario" │(codigo que entra dentro do parenteses do antMatchers)
             //Definir melhor o método(verbo) depois e as rotas que podem ser liberadas de autenticação
            .antMatchers("/**").permitAll()
             .anyRequest().permitAll();
             
             


            //antes de qualquer requisição http, o sistema usa o filtro
             http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
   }
}





// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
    
//     @Autowired
//     private JWTFilter filter;
  
//     @Autowired
//     private UserDetailsServiceImpl uds;
  
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//       http
//         .csrf()
//         .disable()
//         .httpBasic()
//         .disable()
//         .cors()
//         .and()
//         .authorizeHttpRequests()
//         .antMatchers(
//           "/auth/**",
//           "/swagger-ui/**",
//           "/v3/api-docs/**",
//           "/actuator/**",
//           "/h2-console/**"
//         )
//         .permitAll()
//         .antMatchers("/user/**")
//         .hasRole("USER")
//         .anyRequest()
//         .authenticated()
//         .and()
//         .userDetailsService(uds)
//         .exceptionHandling()
//         .authenticationEntryPoint(
//           (request, response, authException) ->
//             response.sendError(
//               HttpServletResponse.SC_UNAUTHORIZED,
//               "Unauthorized"
//             )
//         )
//         .and()
//         .sessionManagement()
//         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  
//       http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//     }
  
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//       return new BCryptPasswordEncoder();
//     }
  
//     @Bean
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//       return super.authenticationManagerBean();
//     }
// }
