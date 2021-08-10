package com.nikita.springbootfinal.dto;

import com.nikita.springbootfinal.entity.Pharmacy;
import com.nikita.springbootfinal.entity.Record;

public interface RecordWithPharmacy {
    public Record getRecord();
    public Pharmacy getPharmacy();
}
