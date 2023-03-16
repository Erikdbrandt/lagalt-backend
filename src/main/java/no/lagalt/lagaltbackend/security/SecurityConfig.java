package no.lagalt.lagaltbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
//@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .sessionManagement().disable()
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user","/user/**").permitAll()
//                        .requestMatchers("/project/").hasRole("ROLE_ADMIN")
//                        .requestMatchers("/api/v1/resources/authorized").hasAuthority("profile")
//                        .requestMatchers("/api/v1/resources/authorize/offline").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
