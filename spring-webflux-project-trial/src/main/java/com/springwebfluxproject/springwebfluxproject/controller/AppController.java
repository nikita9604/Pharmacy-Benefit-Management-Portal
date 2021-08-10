package com.springwebfluxproject.springwebfluxproject.controller;

import com.springwebfluxproject.springwebfluxproject.dto.CreateUserDTO;
import com.springwebfluxproject.springwebfluxproject.entity.Insurance;
import com.springwebfluxproject.springwebfluxproject.entity.Patient;
import com.springwebfluxproject.springwebfluxproject.repository.DrugRepository;
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
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@Log4j2
public class AppController {

    @Autowired
    UserRepository users;

    @Autowired
    private PMBService service;

    @Autowired
    private DrugRepository drugRepository;


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

    @GetMapping("/all")
    public String showAll(Model model)
    {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(drugRepository.findAll(), 1);
        model.addAttribute("drugs",reactiveDataDrivenMode);
        //log.info();
        return "userdash";
    }

    @GetMapping("/confirm")
    public String showDashboard()
    {
        return "admindash";
    }
}
