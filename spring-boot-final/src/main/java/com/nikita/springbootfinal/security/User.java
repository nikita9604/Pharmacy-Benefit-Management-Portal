package com.nikita.springbootfinal.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "userrepo")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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