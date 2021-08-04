package com.springwebfluxproject.springwebfluxproject.controller;


import com.springwebfluxproject.springwebfluxproject.entity.*;
import com.springwebfluxproject.springwebfluxproject.service.PMBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class PBMController {

    @Autowired
    private PMBService service;

    // Patient Details
    @PostMapping("/addP")
    public Mono<Patient> addPatient(@RequestBody Patient patient){ return service.savePatient(patient); }

    // Record Details
    @GetMapping("/updateRS")
    public Mono<String> updateRecordStatus(@RequestBody int id){ return service.updateRecordStatus(id); }

    // Drug Details
    @PostMapping("/addD")
    public Flux<Drug> addDrugs(@RequestBody List<Drug> drugs){ return service.addDrugs(drugs); }
    @GetMapping("/getD")
    public Flux<Drug> getDrugList(){ return service.getDrugList(); }
    @DeleteMapping("/deleteD/{id}")
    public Mono<String> deleteDrug(@PathVariable int id){
        return service.deleteDrug(id);
    }

    // Insurance Details
    @PostMapping("/addI")
    public Flux<Insurance> addInsurances(@RequestBody List<Insurance> insurance){ return service.addInsurances(insurance); }
    @GetMapping("/getI")
    public Flux<Insurance> getInsuranceList(){ return service.getInsuranceList(); }
    @DeleteMapping("/deleteI/{id}")
    public Mono<String> deleteInsurance(@PathVariable int id){
        return service.deleteInsurance(id);
    }
    @GetMapping("/getIS/{id}")
    public Mono<String> getInsuranceStatus(@PathVariable int id){ return service.getInsuranceStatus(id); }

    // Doctor Details
    @PostMapping("/addDr")
    public Flux<Doctor> addDoctors(@RequestBody List<Doctor> doctors){ return service.addDoctors(doctors); }
    @GetMapping("/getDr")
    public Flux<Doctor> getDoctorList(){ return service.getDoctorList(); }
    @DeleteMapping("/deleteDr/{id}")
    public Mono<String> deleteDoctor(@PathVariable int id){
        return service.deleteDoctor(id);
    }
    @GetMapping("/getDrPS/{docname}")
    public Mono<String> getDoctorPrescription(@PathVariable String docname){ return service.getDoctorPrescription(docname); }

    // Pharmacy Details
    @PostMapping("/addPh")
    public Flux<Pharmacy> addPharmacies(@RequestBody List<Pharmacy> pharmacies){ return service.addPharmacies(pharmacies); }
    @GetMapping("/getPh")
    public Flux<Pharmacy> getPharmacyList(){ return service.getPharmacyList(); }
    @DeleteMapping("/deletePh/{id}")
    public Mono<String> deletePharmacy(@PathVariable int id){
        return service.deletePharmacy(id);
    }
}
