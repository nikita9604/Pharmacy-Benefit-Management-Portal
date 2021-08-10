package com.springwebfluxproject.springwebfluxproject.controller;


import com.springwebfluxproject.springwebfluxproject.dto.CreateUserDTO;
import com.springwebfluxproject.springwebfluxproject.entity.*;
import com.springwebfluxproject.springwebfluxproject.security.User;
import com.springwebfluxproject.springwebfluxproject.security.UserRepository;
import com.springwebfluxproject.springwebfluxproject.service.PMBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class PBMController {

    @Autowired
    private PMBService service;

    @Autowired
    private UserRepository users;

    // Patient Details
    @PostMapping("/addP")
    public Mono<Patient> addPatient(@RequestBody CreateUserDTO client){

        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode(client.getPassword());
        User userWithEncodedPassword=new User(null,client.getName(),encodedPassword,"ROLE_USER");

        Patient patient=new Patient(null, client.getName(), client.getCity(), client.getInid());
        return  users.save(userWithEncodedPassword).zipWith(service.savePatient(patient),(newUser,newPatient)->newPatient);
    }

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

    @GetMapping("/getPh/{dname}/{city}")
    public Flux<Pharmacy> getNearbyPharmacyList(@PathVariable("dname") String dname,@PathVariable("city") String city)
    {
        return service.getNearbyPharmacies(dname,city);
    }
    @GetMapping("/covered/{dname}/{plan}")
    public Mono<String> isDrugCovered(@PathVariable("dname") String dname,@PathVariable("plan") String plan)
    {
        return service.IsDrugCoveredByInsurance(dname,plan);
    }

    //User Repository
    @PostMapping("/addUser")
    public Mono<User> addUsers(@RequestBody User user){
        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        User userWithEncodedPassword=new User(user.getUid(),user.getUsername(),encodedPassword,user.getRole());
        return users.save(userWithEncodedPassword);
    };
    }
