package com.pbm.springbootpbm.controller;

import com.pbm.springbootpbm.entity.*;
import com.pbm.springbootpbm.entity.Record;
import com.pbm.springbootpbm.service.PMBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PBMController {
    @Autowired
    private PMBService service;

    // Patient Details
    @PostMapping("/addP")
    public Patient addPatient(@RequestBody Patient patient){ return service.savePatient(patient); }

    // Record Details
    @GetMapping("/updateRS")
    public String updateRecordStatus(@RequestBody int id){ return service.updateRecordStatus(id); }

    // Drug Details
    @PostMapping("/addD")
    public List<Drug> addDrugs(@RequestBody List<Drug> drugs){ return service.addDrugs(drugs); }
    @GetMapping("/getD")
    public List<Drug> getDrugList(){ return service.getDrugList(); }
    @DeleteMapping("/deleteD/{id}")
    public String deleteDrug(@PathVariable int id){
        return service.deleteDrug(id);
    }

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
