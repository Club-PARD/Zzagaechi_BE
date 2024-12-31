package com.zzagaechi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Server httpsServer = new Server();
        httpsServer.setUrl("https://nanoplan.store");
        httpsServer.setDescription("Production HTTPS server");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development server");

        return new OpenAPI()
                .components(new Components())
                .servers(Arrays.asList(httpsServer, localServer))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Zzagaechi API Documentation")
                .description("nanoplan API 문서입니다.")
                .version("1.0.0");
    }
}
