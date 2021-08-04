package com.springwebfluxproject.springwebfluxproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
    @Id
    private int phid;
    private String phname;
    private String city;
    private int did;
}
