package com.springwebfluxproject.springwebfluxproject.security;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRepository extends ReactiveCrudRepository<User,Integer> {
    Mono<User> findByUsername(String username);
}

