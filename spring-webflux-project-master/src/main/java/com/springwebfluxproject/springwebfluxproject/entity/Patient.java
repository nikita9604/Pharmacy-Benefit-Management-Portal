package com.springwebfluxproject.springwebfluxproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    private int pid;
    private String name;
    private String city;
    private int pass;

    private int inid;
}
