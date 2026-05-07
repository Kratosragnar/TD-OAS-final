<<<<<<< HEAD
package com.federation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI federationOpenAPI() {
        Server devServer = new Server()
                .url("http://localhost:8080")
                .description("Serveur de développement");

        Contact contact = new Contact()
                .name("Fédération Agricole")
                .email("contact@federation-agricole.mg")
                .url("https://federation-agricole.mg");

        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("Federation API")
                .version("1.0.0")
                .description("""
                API de gestion d'une fédération agricole nationale.
                
                Fonctionnalités :
                - Gestion des collectivités
                - Gestion des membres
                - Gestion des rôles et mandats
                - Gestion financière (comptes et paiements)
                - Gestion des activités
                - Gestion des présences
                - Calcul des statistiques
                """)
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
=======
// OpenApiConfig.java
package com.federation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Federation API")
                        .version("0.0.5")
                        .description("API pour la fédération agricole"));
>>>>>>> d7e79cd (Fourth commit)
    }
}