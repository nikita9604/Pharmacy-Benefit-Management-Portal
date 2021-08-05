package com.pbm.springbootpbm.controller;

import com.pbm.springbootpbm.dto.PharmacyInfo;
import com.pbm.springbootpbm.dto.RecordWithPharmacyDTO;
import com.pbm.springbootpbm.entity.*;
import com.pbm.springbootpbm.entity.Record;
import com.pbm.springbootpbm.repository.PatientEntryRepository;
import com.pbm.springbootpbm.repository.PharmacyInfoRepository;
import com.pbm.springbootpbm.service.PMBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PBMController {
    @Autowired
    private PMBService service;
    @Autowired
    private PharmacyInfoRepository pharmacyInfoRepository;

    // Pharmacy Info Details
    //@GetMapping("/getPRRR")
    //public List<RecordWithPharmacyDTO> getRecordWithPharmacy(){ return pharmacyInfoRepository.getRecordWithPharmacy(); }
    @GetMapping("/getPRr/{did}/{city}")
    public List<PharmacyInfo> getPharmacyInfo(@PathVariable int did,@PathVariable String city){ return pharmacyInfoRepository.getPharmacyInfo(did,city); }

    // Patient Details
    @GetMapping("/addPP/{name}/{city}/{pass}/{inid}")
    public String insert(@PathVariable String name, @PathVariable String city, @PathVariable int pass, @PathVariable int inid){
        return service.insertPatient(name,city,pass,inid);
    }
    @PostMapping("/addP")
    public Patient addPatient(@RequestBody Patient patient){ return service.savePatient(patient); }
    @GetMapping("/getP")
    public List<Patient> getPatientList(){ return service.getPatientList(); }
    @GetMapping("/getPlogin/{name}/{pass}")
    public String getLoginStatus(@PathVariable String name, @PathVariable int pass){ return service.getLoginStatus(name,pass); }

    // Record Details
    @PostMapping("/addR")
    public List<Record> addRecords(@RequestBody List<Record> records){ return service.addRecords(records); }
    @GetMapping("/updateRS")
    public String updateRecordStatus(@RequestBody int id){ return service.updateRecordStatus(id); }
    @GetMapping("/getRPending")
    public List<Record> getPendingRecord(){ return service.getPendingRecord(); }

    // Drug Details
    @PostMapping("/addD")
    public List<Drug> addDrugs(@RequestBody List<Drug> drugs){ return service.addDrugs(drugs); }
    @GetMapping("/getD")
    public List<Drug> getDrugList(){ return service.getDrugList(); }
    @DeleteMapping("/deleteD/{id}")
    public String deleteDrug(@PathVariable int id){
        return service.deleteDrug(id);
    }
    @GetMapping("/getDPS/{dname}")
    public String getDrugnameStatus(@PathVariable String dname){ return service.getDrugnameStatus(dname); }

    // Insurance Details
    @PostMapping("/addI")
    public List<Insurance> addInsurances(@RequestBody List<Insurance> insurance){ return service.addInsurances(insurance); }
    @GetMapping("/getI")
    public List<Insurance> getInsuranceList(){ return service.getInsuranceList(); }
    @DeleteMapping("/deleteI/{id}")
    public String deleteInsurance(@PathVariable int id){
        return service.deleteInsurance(id);
    }
    @GetMapping("/getIS/{id}")
    public String getInsuranceStatus(@PathVariable int id){ return service.getInsuranceStatus(id); }

    // Doctor Details
    @PostMapping("/addDr")
    public List<Doctor> addDoctors(@RequestBody List<Doctor> doctors){ return service.addDoctors(doctors); }
    @GetMapping("/getDr")
    public List<Doctor> getDoctorList(){ return service.getDoctorList(); }
    @DeleteMapping("/deleteDr/{id}")
    public String deleteDoctor(@PathVariable int id){
        return service.deleteDoctor(id);
    }
    @GetMapping("/getDrPS/{docname}")
    public String getDoctorPrescription(@PathVariable String docname){ return service.getDoctorPrescription(docname); }

    // Pharmacy Details
    @PostMapping("/addPh")
    public List<Pharmacy> addPharmacies(@RequestBody List<Pharmacy> pharmacies){ return service.addPharmacies(pharmacies); }
    @GetMapping("/getPh")
    public List<Pharmacy> getPharmacyList(){ return service.getPharmacyList(); }
    @DeleteMapping("/deletePh/{id}")
    public String deletePharmacy(@PathVariable int id){
        return service.deletePharmacy(id);
    }
}
