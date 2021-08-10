package com.nikita.springbootfinal.dto;

import lombok.Value;

@Value
public class CartInfo {
    Integer did;
    String dname;
    Integer price;
    String phname;
    String city;
    String status;
}
