package com.springwebfluxproject.springwebfluxproject.dto;

import com.springwebfluxproject.springwebfluxproject.entity.Drug;
import com.springwebfluxproject.springwebfluxproject.entity.Pharmacy;
import com.springwebfluxproject.springwebfluxproject.entity.Record;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyDrugResponse {

    private Pharmacy pharmacy;
    private List<Drug> drugs;
}
