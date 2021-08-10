package com.nikita.springbootfinal.repository;

import com.nikita.springbootfinal.dto.AdminInfo;
import com.nikita.springbootfinal.dto.AdminPharmacyInfo;
import com.nikita.springbootfinal.dto.CartInfo;
import com.nikita.springbootfinal.dto.PharmacyInfo;
import com.nikita.springbootfinal.entity.Pharmacy;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PharmacyInfoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    // USER CART = Get did, dname, price, phname, city and status for a particular pid (Record)
    public List<CartInfo> getCartInfo(Integer pid) {
        String query = "select distinct new com.pbm.springbootpbm.dto.CartInfo(r.did, d.dname, d.price,ph.phname, r.city, r.status) from Record r, Drug d, Pharmacy ph, Patient p " +
                "where r.did = d.did and r.phid=ph.phid and r.pid = ?1";
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(query, CartInfo.class).setParameter(1,pid).getResultList();
    }

    // Get Pharmacy Name, City (Pharmacy) and Drug name (Drug) for a particular did and city (Record)
    public List<PharmacyInfo> getPharmacyInfo(Integer did, String city) {
        String query = "select distinct new com.pbm.springbootpbm.dto.PharmacyInfo(p.phname, p.city, d.dname) from Pharmacy p, Record r, Drug d " +
                "where p.did = r.did and p.city = r.city and r.did = d.did and r.did = ?1 and r.city = ?2";
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(query, PharmacyInfo.class).setParameter(1,did).setParameter(2,city).getResultList();
    }

    // ADMIN DASHBOARD DISPLAY = Get rid,pid,name,did,dname,docname (ALL)
    public List<AdminInfo> getAdminInfo() {
        String query = "select distinct new com.pbm.springbootpbm.dto.AdminInfo(r.rid,r.pid,p.name,r.did,d.dname,r.docname) from Patient p, Record r, Drug d " +
                "where p.pid = r.pid and r.did = d.did";
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(query, AdminInfo.class).getResultList();
    }

    // ADMIN Pharmacy DISPLAY = Get pid,name,phname,city,did for given patient pid and status pending
    public List<AdminPharmacyInfo> getAdminPharmacyInfo(Integer pid) {
        String query = "select distinct new com.pbm.springbootpbm.dto.AdminInfo(r.pid,p.name,ph.name,r.city,r.did) from Patient p, Record r, Pharmacy ph " +
                "where r.pid = p.pid and r.phid = ph.phid and p.city=ph.city and r.pid= ?1";
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(query, AdminPharmacyInfo.class).setParameter(1,pid).getResultList();
    }

    public Boolean checkIfDrugCoveredByInsurance(String dname, String plan){
        String query = "select new java.lang.Boolean(count(*) > 0) from Insurance i, Drug d, InsuranceDrug id " +
                "where d.did = id.did and i.inid = id.inid and d.dname = ?1 and i.plan = ?2";
        Session session = entityManager.unwrap(Session.class);
        return (Boolean) session.createQuery(query).setParameter(1,dname).setParameter(2,plan).getSingleResult();
    }

    public List<Pharmacy> getAllPharmacyWithDrugName(String dname)
    {
        String query="select new com.pbm.springbootpbm.entity.Pharmacy(p.phid, p.phname, p.city) from Pharmacy p, Drug d" +
                "where d.did = p.did and d.dname = ?1";
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(query, Pharmacy.class).setParameter(1,dname).getResultList();
    }

    public List<Pharmacy> getAllPharmacyWithDrugNameInACity(String dname, String city)
    {
        String query="select new com.pbm.springbootpbm.entity.Pharmacy(p.phid, p.phname, p.city) from Pharmacy p, Drug d" +
                "where d.did = p.did and d.dname = ?1 and p.city = ?2";
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery(query, Pharmacy.class).setParameter(1,dname).setParameter(2,city).getResultList();
    }
}
