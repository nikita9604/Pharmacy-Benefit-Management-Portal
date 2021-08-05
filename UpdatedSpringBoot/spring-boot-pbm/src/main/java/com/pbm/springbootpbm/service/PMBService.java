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
    @Autowired
    private PatientEntryRepository patientEntryRepository;

    // Patient Repository
    // Add details from Patient sign up page to database - button
    public String insertPatient(String name, String city, int pass, int inid){
        Patient patient1 = new Patient(name,city,pass,inid);
        patientEntryRepository.insertPatient(patient1);
        return "Signed Up Successfully";
    }
    public Patient savePatient(Patient patient){ return patientRepository.save(patient); }
    public List<Patient> getPatientList(){ return patientRepository.findAll(); }
    // Check Name and Password pair exists for Login Page - button
    public String getLoginStatus(String name, int pass){
        Boolean check = patientRepository.hasLogin(name,pass);
        if (check)
            return "Login Successfully";
        else
            return "Login Failed";
    }

    // Record Repository
    public List<Record> addRecords(List<Record> records){ return recordRepository.saveAll(records); }
    // Update status from pending to confirmed
    public String updateRecordStatus(int id){
        Record existingProduct=recordRepository.findById(id).orElse(null);
        existingProduct.setStatus("confirmed");
        recordRepository.save(existingProduct);
        return "confirmed";
    }
    public List<Record> getPendingRecord(){ return recordRepository.getPendingRecord(); }

    // Drug Repository
    public List<Drug> addDrugs(List<Drug> drugs){ return drugRepository.saveAll(drugs); }
    public List<Drug> getDrugList(){ return drugRepository.findAll(); }
    public String deleteDrug(int did){
        drugRepository.deleteById(did);
        return "Removed Drug with ID - " + did;
    }
    // Check if Drug name Exists or not - Button
    public String getDrugnameStatus(String dname){
        Boolean check = drugRepository.hasDrugname(dname);
        if (check)
            return "Drug found";
        else
            return "Drug not found";
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
        if (check)
            return "Match found";
        else
            return "Match not found";
    }

    // Doctor Repository
    public List<Doctor> addDoctors(List<Doctor> doctors){ return doctorRepository.saveAll(doctors); }
    public List<Doctor> getDoctorList(){ return doctorRepository.findAll(); }
    public String deleteDoctor(int docid){
        doctorRepository.deleteById(docid);
        return "Removed Drug with ID - " + docid;
    }
    // Check if Doctor name Exists or not - Button (Add drug items by patient) - Prescription Check
    public String getDoctorPrescription(String docname){
        Boolean check = doctorRepository.hasDocname(docname);
        if (check)
            return "Prescription Request Accepted";
        else
            return "Prescription Request Failed";
    }

    // Pharmacy Repository
    public List<Pharmacy> addPharmacies(List<Pharmacy> pharmacies){ return pharmacyRepository.saveAll(pharmacies); }
    public List<Pharmacy> getPharmacyList(){ return pharmacyRepository.findAll(); }
    public String deletePharmacy(int phid){
        pharmacyRepository.deleteById(phid);
        return "Removed Drug with ID - " + phid;
    }
}
