package com.springwebfluxproject.springwebfluxproject.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drug {
    @Id
    private Integer did;
    private String dname;
    private int price;
}
