package com.springwebfluxproject.springwebfluxproject.controller;


import com.springwebfluxproject.springwebfluxproject.dto.CreateUserDTO;
import com.springwebfluxproject.springwebfluxproject.entity.*;
import com.springwebfluxproject.springwebfluxproject.entity.Record;
import com.springwebfluxproject.springwebfluxproject.security.User;
import com.springwebfluxproject.springwebfluxproject.security.UserRepository;
import com.springwebfluxproject.springwebfluxproject.service.PMBService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Log4j2
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
        return users.save(userWithEncodedPassword).zipWith(service.savePatient(patient),(newUser,newPatient)->newPatient);

    }
    @GetMapping("/getP")
    public Flux<Patient> getPatientList(){ return service.getPatientList(); }
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

    @GetMapping("/current-user")
    public Mono<User> getCurrentUser(ServerWebExchange exchange) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(principal-> (User) principal);
    }
    //Record Repository
    @PostMapping("/addR")
    public Mono<Record> addPatient(@RequestBody Record record){
        return service.addRecord(record);

    }
    @GetMapping("/getR/{pname}")
    public Flux<Record> getRecordsOfPatient(@PathVariable("pname") String pname)
    {
        return service.getAllRecordsOfAPatient(pname);
    }

    }
