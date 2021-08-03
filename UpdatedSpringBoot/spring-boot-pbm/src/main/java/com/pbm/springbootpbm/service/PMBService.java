package com.pbm.springbootpbm.service;

import com.pbm.springbootpbm.entity.*;
import com.pbm.springbootpbm.entity.Record;
import com.pbm.springbootpbm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PMBService {
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RecordRepository recordRepository;

    // Patient Repository
    // Add details in Patient sign up page - button
    public Patient savePatient(Patient patient){ return patientRepository.save(patient); }

    // Record Repository
    // Update status from pending to confirmed
    public String updateRecordStatus(int id){
        Record existingProduct=recordRepository.findById(id).orElse(null);
        existingProduct.setStatus("confirmed");
        recordRepository.save(existingProduct);
        return "confirmed";
    }

    // Drug Repository
    public List<Drug> addDrugs(List<Drug> drugs){ return drugRepository.saveAll(drugs); }
    public List<Drug> getDrugList(){ return drugRepository.findAll(); }
    public String deleteDrug(int did){
        drugRepository.deleteById(did);
        return "Removed Drug with ID - " + did;
    }

    // Insurance Repository
    public List<Insurance> addInsurances(List<Insurance> insurances){ return insuranceRepository.saveAll(insurances); }
    public List<Insurance> getInsuranceList(){ return insuranceRepository.findAll(); }
    public String deleteInsurance(int inid){
        drugRepository.deleteById(inid);
        return "Removed Insurance with ID - " + inid;
    }
    // Check if Insurance Id Exists or not - Button (Patient sign up page)
    public String getInsuranceStatus(int inid){
        Boolean check = insuranceRepository.hasInid(inid);
        if (check == true)
            return "Match Found";
        else
            return "Match Failed";
    }

    // Doctor Repository
    public List<Doctor> addDoctors(List<Doctor> doctors){ return doctorRepository.saveAll(doctors); }
    public List<Doctor> getDoctorList(){ return doctorRepository.findAll(); }
    public String deleteDoctor(int docid){
        drugRepository.deleteById(docid);
        return "Removed Drug with ID - " + docid;
    }
    // Check if Doctor name Exists or not - Button (Add drug items by patient) - Prescription Check
    public String getDoctorPrescription(String docname){
        Boolean check = doctorRepository.hasDocname(docname);
        if (check == true)
            return "Prescription Request Accepted";
        else
            return "Prescription Request Failed";
    }

    // Pharmacy Repository
    public List<Pharmacy> addPharmacies(List<Pharmacy> pharmacies){ return pharmacyRepository.saveAll(pharmacies); }
    public List<Pharmacy> getPharmacyList(){ return pharmacyRepository.findAll(); }
    public String deletePharmacy(int phid){
        drugRepository.deleteById(phid);
        return "Removed Drug with ID - " + phid;
    }
}
