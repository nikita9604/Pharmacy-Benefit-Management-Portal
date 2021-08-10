package com.springwebfluxproject.springwebfluxproject.controller;

import com.springwebfluxproject.springwebfluxproject.dto.CreateUserDTO;
import com.springwebfluxproject.springwebfluxproject.entity.Patient;
import com.springwebfluxproject.springwebfluxproject.security.User;
import com.springwebfluxproject.springwebfluxproject.security.UserRepository;
import com.springwebfluxproject.springwebfluxproject.service.PMBService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Controller
@Log4j2
public class AppController {

    @Autowired
    UserRepository users;

    @Autowired
    private PMBService service;


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
    public Mono<Patient> addPatient(@ModelAttribute CreateUserDTO client,Model model){

        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode(client.getPassword());
        User userWithEncodedPassword=new User(null,client.getName(),encodedPassword,"ROLE_USER");

        Patient patient=new Patient(null, client.getName(), client.getCity(), client.getInid());
        return users.save(userWithEncodedPassword).zipWith(service.savePatient(patient),(newUser,newPatient)->newPatient);

    }
}
