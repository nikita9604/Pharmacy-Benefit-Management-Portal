package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.dto.PharmacyInfo;
import com.pbm.springbootpbm.dto.RecordWithPharmacyDTO;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PharmacyInfoRepository {
    @PersistenceContext
    private EntityManager entityManager;
    /*
    public List<RecordWithPharmacyDTO> getRecordWithPharmacy() {
        String query = "select distinct new com.pbm.springbootpbm.dto.RecordWithPharmacyDTO(r, p) from Record r inner join Pharmacy p on r.did = p.did and r.city=p.city";
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(query, RecordWithPharmacyDTO.class).getResultList();
    }
     */

    // Get Pharmacy Name, City (Pharmacy) and Drug name (Drug) for a particular did and city (Record)
    public List<PharmacyInfo> getPharmacyInfo(int did, String city) {
        String query = "select distinct new com.pbm.springbootpbm.dto.PharmacyInfo(p.phname, p.city, d.dname) from Pharmacy p, Record r, Drug d " +
                "where p.did = r.did and p.city = r.city and r.did = d.did and r.did = ?1 and r.city = ?2";
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(query, PharmacyInfo.class).setParameter(1,did).setParameter(2,city).getResultList();
    }
}
