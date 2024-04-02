package com.carboncell.APIManagementApp.security.configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public OpenAPI openAPI(){
        String schemeName = "bearerScheme";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(schemeName)
                )
                .components(new Components()
                        .addSecuritySchemes(schemeName,new SecurityScheme()
                                .name(schemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer")
                        )
                )
                    .info(new Info()
                    .title("API Data Management And Retrieve Ethereum Account Balance")
                            .description("The Secure API Management Application is a robust platform designed to facilitate secure communication between clients" +
                                    " and our services. Built with security and ease of use in mind, the application leverages JWT (JSON Web Tokens) " +
                                    "for authentication, ensuring that each request is verified for authenticity and integrity. Our comprehensive " +
                                    "API endpoints allow clients to register, login, and perform various secured actions, making it an ideal solution " +
                                    "for managing sensitive data transactions")
                            .version("v1.0.0")
                            .contact(new Contact().name("Saurav Kumar").email("saurav24021998@gmail.com"))
                                    .license(new License().name("Apache"))
                    );

    }

}
