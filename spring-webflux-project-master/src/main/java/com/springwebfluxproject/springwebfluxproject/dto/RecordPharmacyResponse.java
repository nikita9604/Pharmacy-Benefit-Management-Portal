package com.springwebfluxproject.springwebfluxproject.dto;

import com.springwebfluxproject.springwebfluxproject.entity.Record;
import com.springwebfluxproject.springwebfluxproject.entity.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordPharmacyResponse {
    private Record record;
    private Pharmacy pharmacy;
}
