package com.pbm.springbootpbm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String name;
    private String city;
    private int pass;
    @Column(nullable = true)
    private int inid;

    public Patient(String name, String city, int pass, int inid)
    {
        super();
        this.name = name;
        this.city = city;
        this.pass = pass;
        this.inid = inid;
    }
}
