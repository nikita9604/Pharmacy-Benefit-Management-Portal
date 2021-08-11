package com.springwebfluxproject.springwebfluxproject.entity;

import io.swagger.models.auth.In;
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
    private Integer rid;
    private int pid;
    private int did;
    private String docname;
    private String city;

    private String status;
    private int phid=0;

    public Record(Integer rid, int pid, int did,String docname, String city, String status) {
        this.rid = rid;
        this.pid = pid;
        this.did = did;
        this.docname = docname;
        this.city = city;
        this.status = status;
    }
}
