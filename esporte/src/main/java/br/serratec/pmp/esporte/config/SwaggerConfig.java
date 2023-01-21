package br.serratec.pmp.esporte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.serratec.pmp.esporte"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("API - Projeto: Onde Tem Esporte ? - Prefeitura Municipal de Petrópolis")
				.description("Essa é uma API para Prefeitura de Petrópolis estreitar o relacionamento com a população a respeito dos eventos esportivos realizados na cidade imperial.").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/license/LICENSE-2.0").version("1.0.0")
				.contact(new Contact("Onde Tem Esporte? Prefeitura Municipal de Petrópolis", "www.ondetemesporte.org.br", "teste@gmail.com"))
				.build();
		return apiInfo;
	}

  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }
}