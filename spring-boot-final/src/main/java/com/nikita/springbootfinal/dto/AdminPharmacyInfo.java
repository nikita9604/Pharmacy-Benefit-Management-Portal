package com.nikita.springbootfinal.dto;

import lombok.Value;

@Value
public class AdminPharmacyInfo {
    Integer pid;
    String name;
    String phname;
    String city;
    Integer did;
}
