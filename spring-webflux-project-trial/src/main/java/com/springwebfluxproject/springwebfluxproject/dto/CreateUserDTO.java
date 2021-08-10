package com.springwebfluxproject.springwebfluxproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String name;
    private String city;
    private String password;
    private int inid;


}
