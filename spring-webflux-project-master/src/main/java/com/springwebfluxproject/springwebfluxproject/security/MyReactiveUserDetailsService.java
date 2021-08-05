package com.springwebfluxproject.springwebfluxproject.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashSet;

//@Service
public class MyReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private UserRepository users;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        return this.users.findByUsername(username).switchIfEmpty(Mono.defer(() -> {

            return Mono.error(new UsernameNotFoundException("User Not Found"));

        })).map(this::toUserDetails);
    }


    private BridgedUser toUserDetails(User user) {

        return new BridgedUser(user);
    }

    private static class BridgedUser extends User implements UserDetails {
        Boolean enabled=true;

        public BridgedUser(User user) {
            super(user);


        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> authorities = new HashSet<>();
            String role=this.getRole();

            if ("ROLE_ADMIN".equals(role)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            }
            else if("ROLE_USER".equals(role))
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return authorities;
        }

        @Override
        public boolean isAccountNonExpired() {
            return this.enabled;
        }

        @Override
        public boolean isAccountNonLocked() {
            return this.enabled;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return this.enabled;
        }

        @Override
        public boolean isEnabled() {
            return this.enabled;
        }
    }
}

