package com.nikita.springbootfinal.dto;

import lombok.Value;

@Value
public class CreateUserDTO {
    String name;
    String city;
    String password;
    Integer inid;
}
