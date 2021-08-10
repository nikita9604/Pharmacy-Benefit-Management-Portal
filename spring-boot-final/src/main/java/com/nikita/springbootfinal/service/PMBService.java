package com.nikita.springbootfinal.service;

import com.nikita.springbootfinal.entity.*;
import com.nikita.springbootfinal.entity.Record;
import com.nikita.springbootfinal.repository.*;
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
    private PharmacyInfoRepository pharmacyInfoRepository;

    // Patient Repository
    // Add details from Patient sign up page to database - button
    /*
    public String insertPatient(String name, String city, Integer inid){
        Patient patient1 = new Patient(null,name,city,inid);
        patientEntryRepository.insertPatient(patient1);
        return "Signed Up Successfully";
    }*/
    public Patient savePatient(Patient patient){ return patientRepository.save(patient); }
    public List<Patient> getPatientList(){ return patientRepository.findAll(); }

    // Record Repository
    public List<Record> addRecords(List<Record> records){ return recordRepository.saveAll(records); }
    public Record saveRecord(Record record){ return recordRepository.save(record); }
    // Update status from pending to confirmed
    public String updateRecordStatus(Integer id){
        Record existingProduct=recordRepository.findById(id).orElse(null);
        existingProduct.setStatus("confirmed");
        recordRepository.save(existingProduct);
        return "confirmed";
    }
    public List<Record> getPendingRecord(){ return recordRepository.getPendingRecord(); }
    // Add details from dashboard to Record table - submit
    /*
    public String insertRecord(int pid, int did, String docname, String city,int phid){
        Record record = new Record(pid,did,docname,city,"pending",phid);
        recordEntryRepository.insertRecord(record);
        return "Request Sent successfully";
    }
     */

    // Drug Repository
    public List<Drug> addDrugs(List<Drug> drugs){ return drugRepository.saveAll(drugs); }
    public List<Drug> getDrugList(){ return drugRepository.findAll(); }
    public String deleteDrug(Integer did){
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
    public String IsDrugCoveredByInsurance(String dname,String plan){
        Boolean check = pharmacyInfoRepository.checkIfDrugCoveredByInsurance(dname,plan);
        if (check)
            return "Drug Is Covered By Your Insurance";
        else
            return "Drug is not Covered";
    }

    // Insurance Repository
    public List<Insurance> addInsurances(List<Insurance> insurances){ return insuranceRepository.saveAll(insurances); }
    public List<Insurance> getInsuranceList(){ return insuranceRepository.findAll(); }
    public String deleteInsurance(Integer inid){
        drugRepository.deleteById(inid);
        return "Removed Insurance with ID - " + inid;
    }
    // Check if Insurance Id Exists or not - Button (Patient sign up page)
    public String getInsuranceStatus(Integer inid){
        Boolean check = insuranceRepository.hasInid(inid);
        if (check)
            return "Match Found";
        else
            return "Match Failed";
    }

    // Doctor Repository
    public List<Doctor> addDoctors(List<Doctor> doctors){ return doctorRepository.saveAll(doctors); }
    public List<Doctor> getDoctorList(){ return doctorRepository.findAll(); }
    public String deleteDoctor(Integer docid){
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
    public String deletePharmacy(Integer phid){
        pharmacyRepository.deleteById(phid);
        return "Removed Drug with ID - " + phid;
    }
    public List<Pharmacy> getNearbyPharmacies(String dname,String city)
    {
        return pharmacyInfoRepository.getAllPharmacyWithDrugNameInACity(dname, city);
    }
}
