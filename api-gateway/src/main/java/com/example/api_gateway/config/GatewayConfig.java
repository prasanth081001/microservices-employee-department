package com.example.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("employee-service", route -> route
                        .path("/employees/**")
                        .uri("lb://employee-service"))
                .route("department-service",route->route
                        .path("/departments/**")
                        .uri("lb://department-service"))
                .build();
    }
}
