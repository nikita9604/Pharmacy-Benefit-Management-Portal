package com.nikita.springbootfinal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;
    private Integer pid;
    private Integer did;
    private String docname;
    private String city;
    //@ColumnDefault("pending")
    private String status;
    private Integer phid=1;
}
