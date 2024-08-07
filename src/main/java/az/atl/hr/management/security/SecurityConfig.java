package az.atl.hr.management.security;

import az.atl.hr.management.exception.CustomAccessDeniedFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomAccessDeniedFilter customAccessDeniedFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("v1/auth/**").permitAll()
                        .requestMatchers("v1/management/employees/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "v1/management/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "v1/management/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(x ->
                        x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e ->
                        e.accessDeniedHandler(customAccessDeniedFilter)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}

//3 adminorstaff user and public