package com.springwebfluxproject.springwebfluxproject.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyDrug {

    @Id
    private int pdid;
    private int phid;
    private int did;
}
