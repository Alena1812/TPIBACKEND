package com.tpi.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GWConfig {

    // Configuración de rutas para Gateway
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder,
                                     @Value("${tpi-agencia-api-gw.microservicio-agencia}") String uriAgencia,
                                     @Value("${tpi-agencia-api-gw.microservicio-notificaciones}") String uriNotificaciones) {

        return builder.routes()
                // Ruta para el microservicio "agencia"
                .route("ruta-agencia", r -> r
                        .path("/api/v1/agencia/**")  // Se aplica a cualquier solicitud que comience con /api/v1/agencia
                        .filters(f -> f.stripPrefix(3))  // Elimina los tres primeros segmentos del path ("/api/v1/agencia") antes de redirigir
                        .uri(uriAgencia))  // Redirige a la URI del microservicio "agencia"

                // Ruta para el microservicio "notificaciones"
                .route("ruta-notificaciones", r -> r
                        .path("/api/v1/notificaciones/**")  // Se aplica a cualquier solicitud que comience con /api/v1/notificaciones
                        .filters(f -> f.stripPrefix(3))  // Elimina los tres primeros segmentos del path ("/api/v1/notificaciones") antes de redirigir
                        .uri(uriNotificaciones))  // Redirige a la URI del microservicio "notificaciones"
                .build();
    }

    // Configuración de seguridad para las rutas del Gateway
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchange -> exchange
                        // Rutas con permisos específicos
                        .pathMatchers("/api/v1/agencia/pruebas/new").hasRole("EMPLEADO")
                        .pathMatchers("/api/v1/notificaciones/promocion").hasRole("EMPLEADO")
                        .pathMatchers("/api/v1/agencia/pruebas/posicion").hasRole("VEHICULO")
                        .pathMatchers("/api/v1/agencia/reportes/**").hasRole("ADMIN")

                        // Requiere autenticación para cualquier otra ruta
                        .anyExchange().authenticated()
                )
                // Configuración de autenticación mediante JWT como servidor de recursos OAuth2
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))

                // Desactiva CSRF (útil para aplicaciones sin interfaz web)
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

    // Configuración para convertir JWT en autoridades (roles)
    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        // Converter que extrae roles de las "claims" del token JWT
        ReactiveJwtAuthenticationConverter jwtConverter = new ReactiveJwtAuthenticationConverter();

        // Configuración de cómo extraer roles
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("authorities");  // Define el nombre del campo en el JWT que contiene los roles
        authoritiesConverter.setAuthorityPrefix("ROLE_");  // Prefijo a añadir a cada rol

        // Asigna el converter configurado para adaptar las autoridades
        jwtConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(authoritiesConverter));

        return jwtConverter;
    }
}
