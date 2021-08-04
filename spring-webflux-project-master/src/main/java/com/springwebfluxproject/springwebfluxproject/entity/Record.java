package com.springwebfluxproject.springwebfluxproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Embedded;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    private int rid;
    private int pid;
    private int did;
    private String docname;
    private String city;

    private String status;
    private int phid=0;
}
