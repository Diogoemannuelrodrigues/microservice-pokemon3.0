package br.com.microservice.pokemon.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Pokemon API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.microservice.pokemon.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("POKEDEX API")
                .description(
                        "API responsável pelo gerenciamento " +
                                "de Pokémons e seus movimentos. " +
                                "Funcionalidades: " +
                                "- Verificar movimentos " +
                                "- Iniciar jogo " +
                                "- Consultar Pokémons"
                ).version("1.0.0")
                .termsOfServiceUrl("https://seudominio.com/termos")
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Diogo Emannuel", "https://github.com/seu-github", "diogoemannuel.rodrigues1@gmail.com"))
                .build();
    }
}