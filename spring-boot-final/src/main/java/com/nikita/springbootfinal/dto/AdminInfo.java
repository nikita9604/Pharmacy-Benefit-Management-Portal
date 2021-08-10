package com.nikita.springbootfinal.dto;

import lombok.Value;

@Value
public class AdminInfo {
    Integer rid;
    Integer pid;
    String name;
    Integer did;
    String dname;
    String docname;
}
