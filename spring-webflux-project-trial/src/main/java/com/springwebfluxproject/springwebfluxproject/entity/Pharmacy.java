package com.springwebfluxproject.springwebfluxproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
    @Id
    private Integer phid;
    private String phname;
    private String city;
    // First record would have phid=1, phname="unknown", city="unknown";
}
