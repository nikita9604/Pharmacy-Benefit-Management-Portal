package com.springwebfluxproject.springwebfluxproject.security;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.MediaTypeServerWebExchangeMatcher;

import java.util.HashMap;
import java.util.Map;

@EnableWebFluxSecurity
public class LoginWebfluxSecurityConfig {
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }

    @Bean
    public MyReactiveUserDetailsService userDetailsService()
    {
        return new MyReactiveUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        String idForEncode = "noop";
//        Map encoders = new HashMap<>();
//        encoders.put("bcrypt", new BCryptPasswordEncoder());
//        encoders.put(idForEncode, NoOpPasswordEncoder.getInstance());
//        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//        encoders.put("scrypt", new SCryptPasswordEncoder());
//        encoders.put("sha256", new StandardPasswordEncoder());
//
//        PasswordEncoder passwordEncoder =
//                new DelegatingPasswordEncoder(idForEncode, encoders);
//        return passwordEncoder;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().authenticated().and()
                .httpBasic().and()
                .formLogin().and()
                .csrf().disable();

//        http.authorizeExchange().pathMatchers(HttpMethod.DELETE).denyAll()
//               .pathMatchers("/login", "/logout").permitAll()
//                .pathMatchers(HttpMethod.GET, "/managers-can-see-this-folder/**", "/and-this-page")
//                .hasRole("MANAGER")
//                .matchers(exchange -> new MediaTypeServerWebExchangeMatcher(MediaType.APPLICATION_PDF).matches(exchange))
//                .hasRole("ADMIN")
//                .anyExchange().authenticated()
        return http.build();
    }
}
