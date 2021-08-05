package com.pbm.springbootpbm.dto;

import com.pbm.springbootpbm.entity.Pharmacy;
import com.pbm.springbootpbm.entity.Record;
import lombok.Value;

@Value
public class RecordWithPharmacyDTO {
    private Record record;
    private Pharmacy pharmacy;
}
