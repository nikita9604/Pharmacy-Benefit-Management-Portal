package com.springwebfluxproject.springwebfluxproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDrug {
    @Id
    private int idid;
    private int inid;
    private int did;
    private int reducedprice;
}
