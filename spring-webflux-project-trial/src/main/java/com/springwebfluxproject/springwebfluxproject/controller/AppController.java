package com.springwebfluxproject.springwebfluxproject.controller;

import com.springwebfluxproject.springwebfluxproject.dto.CreateUserDTO;
import com.springwebfluxproject.springwebfluxproject.dto.DrugRequest;
import com.springwebfluxproject.springwebfluxproject.entity.Doctor;
import com.springwebfluxproject.springwebfluxproject.entity.Drug;
import com.springwebfluxproject.springwebfluxproject.entity.Insurance;
import com.springwebfluxproject.springwebfluxproject.entity.Patient;
import com.springwebfluxproject.springwebfluxproject.entity.Record;

import com.springwebfluxproject.springwebfluxproject.repository.DrugRepository;
import com.springwebfluxproject.springwebfluxproject.repository.PatientRepository;
import com.springwebfluxproject.springwebfluxproject.repository.RecordRepository;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Controller
@Log4j2
public class AppController {

    @Autowired
    UserRepository users;

    @Autowired
    private PMBService service;

    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RecordRepository recordRepository;


    @GetMapping("/home")
    public String viewHomePage()
    {
        return "home";
    }

    @GetMapping("/register")
    public String viewSignUpPage(Model model)

    {
        model.addAttribute("client",new CreateUserDTO());
        log.info("rendering form");
        return "signup";
    }

    @PostMapping("/register")
    public Mono<String> addPatient(@ModelAttribute CreateUserDTO client,Model model){
        log.info("Client=",client.toString());
        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode(client.getPassword());
        User userWithEncodedPassword=new User(null,client.getName(),encodedPassword,"ROLE_USER");

        Patient patient=new Patient(null, client.getName(), client.getCity(), client.getInid());
        return users.save(userWithEncodedPassword).zipWith(service.savePatient(patient),(newUser,newPatient)->newPatient).then(Mono.just("home"));
//        model.addAttribute("client",new CreateUserDTO());


    }

    @GetMapping("/addRecord")
    public Mono<String> addRecord(@RequestParam("did") String did,@RequestParam("docname") String docname, Model model){

        //log.info("output",did,docname);
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(principal-> (User) principal)
                .flatMap(user->patientRepository.getPatientGivenName(user.getUsername()))
                .zipWith(service.getDoctorPrescription(docname),
                        (patient,status)->new Record(null,patient.getPid(),Integer.parseInt(did), docname,patient.getCity(),status))
                .flatMap(record->{
                    model.addAttribute("status",record.getStatus());
                    return service.addRecord(record);
                }).then(Mono.just("requestsuccess"));



    }


    @GetMapping("/all")
    public String showAll(Model model)
    {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(drugRepository.findAll(), 1);
        model.addAttribute("drugs",reactiveDataDrivenMode);
//        model.addAttribute("request",new DrugRequest());
        //log.info();
        return "userdash";
    }

    @GetMapping("/requests")
    public String showAdminDash(Model model)
    {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(recordRepository.findAll(), 1);
        model.addAttribute("records",reactiveDataDrivenMode);
//
        //log.info();
        return "admindash";
    }


}
