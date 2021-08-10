package com.nikita.springbootfinal.dto;

import com.nikita.springbootfinal.entity.Pharmacy;
import com.nikita.springbootfinal.entity.Record;
import lombok.Value;

@Value
public class RecordWithPharmacyDTO {
    private Record record;
    private Pharmacy pharmacy;
}
