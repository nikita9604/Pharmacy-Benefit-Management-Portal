package com.nikita.springbootfinal.dto;

import lombok.Value;

@Value
public class PharmacyInfo {
    // Pharmacy Name, Pharmacy City (Pharmacy) & Drug Name (Drug) join by (Record)
    String phname, city, dname;
}
