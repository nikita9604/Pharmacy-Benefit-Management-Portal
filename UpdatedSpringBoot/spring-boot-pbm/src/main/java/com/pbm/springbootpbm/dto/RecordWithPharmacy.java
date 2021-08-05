package com.pbm.springbootpbm.dto;

import com.pbm.springbootpbm.entity.Pharmacy;
import com.pbm.springbootpbm.entity.Record;

public interface RecordWithPharmacy {
    public Record getRecord();
    public Pharmacy getPharmacy();
}
