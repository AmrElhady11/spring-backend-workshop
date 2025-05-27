package com.example.demo.config;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "OpenApi for ProductShop App",
                version = "1.00",
                description = "Open api for task 08.2 in the spring backend workshop",
                termsOfService = "Terms of service",
                contact = @Contact(
                        name = "amr",
                        email = "amr_elhady@gmail.com"
                ),
                license = @License(
                        name = "licence name",
                        url = "https://url.com"
                )
        ),
        servers = {
                @Server(
                        description = "local host",
                        url = "http://localhost:7070"
                )
        }
)

public class AppConfig {

}
