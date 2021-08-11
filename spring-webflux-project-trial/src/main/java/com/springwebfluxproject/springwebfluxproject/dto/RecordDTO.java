package com.springwebfluxproject.springwebfluxproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    private int pid;
    private int did;
    private String docname;
    private String city;
    private String pname;
    private String dname;
}
