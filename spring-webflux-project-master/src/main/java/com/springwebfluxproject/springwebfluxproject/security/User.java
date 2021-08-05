package com.springwebfluxproject.springwebfluxproject.security;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value="MyUser")
public class User {
    @Id
    private Integer uid;

    private String username;
    private String password;

//    private Collection<String> roles;
    private String role;

    User(User user) {

        this.uid = user.uid;
        this.username = user.username;
        this.password = user.password;

        this.role = user.role;
    }


}