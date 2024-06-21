package com.app.gym_management.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gym management",
                version = "1.0.0",
                description = "Gym management application"

        )
)
public class OpenApiConfig {
}
