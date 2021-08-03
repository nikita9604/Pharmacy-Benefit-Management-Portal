package com.pbm.springbootpbm.dto;

import com.pbm.springbootpbm.entity.Pharmacy;
import com.pbm.springbootpbm.entity.Record;
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
