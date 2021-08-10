package com.nikita.springbootfinal.controller;

import com.nikita.springbootfinal.dto.*;
import com.nikita.springbootfinal.entity.*;
import com.nikita.springbootfinal.entity.Record;
import com.nikita.springbootfinal.repository.PharmacyInfoRepository;
import com.nikita.springbootfinal.security.User;
import com.nikita.springbootfinal.security.UserRepository;
import com.nikita.springbootfinal.service.PMBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PBMainController {
    @Autowired
    private PMBService service;
    @Autowired
    private PharmacyInfoRepository pharmacyInfoRepository;
    @Autowired
    private UserRepository users;

    // Pharmacy Info Details
    //@GetMapping("/getPRRR")
    //public List<RecordWithPharmacyDTO> getRecordWithPharmacy(){ return pharmacyInfoRepository.getRecordWithPharmacy(); }
    @GetMapping("/getPRr/{did}/{city}")
    public List<PharmacyInfo> getPharmacyInfo(@PathVariable Integer did,@PathVariable String city){ return pharmacyInfoRepository.getPharmacyInfo(did,city); }
    @GetMapping("/getCR/{pid}/{phname}")
    public List<CartInfo> getCartInfo(@PathVariable Integer pid){ return pharmacyInfoRepository.getCartInfo(pid); }

    // Patient Details
    /*
    @GetMapping("/addPP/{name}/{city}/{pass}/{inid}")
    public String insertPatient(@PathVariable String name, @PathVariable String city, @PathVariable Integer pass, @PathVariable Integer inid){
        return service.insertPatient(name,city,pass,inid);
    }
     */
    @PostMapping("/addP")
    public String addPatient(@RequestBody CreateUserDTO client){
        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode(client.getPassword());
        User userWithEncodedPassword=new User(null,client.getName(),encodedPassword,"ROLE_USER");
        Patient patient=new Patient(null, client.getName(), client.getCity(), client.getInid());
        users.save(userWithEncodedPassword);
        service.savePatient(patient);
        return "User Registered Successfully";
    }
    @PostMapping("/addPp")
    public Patient addPatient(@RequestBody Patient patient){ return service.savePatient(patient); }
    @GetMapping("/getP")
    public List<Patient> getPatientList(){ return service.getPatientList(); }
    //@GetMapping("/getPlogin/{name}/{pass}")
    //public String getLoginStatus(@PathVariable String name, @PathVariable Integer pass){ return service.getLoginStatus(name,pass); }

    // Admin Dasshboard Details (PharmacyInfoRepo)
    @GetMapping("/getAI")
    public List<AdminInfo> getAdminInfoList(){ return pharmacyInfoRepository.getAdminInfo(); }
    @GetMapping("/getAIP/{pid}")
    public List<AdminPharmacyInfo> getAdminPharmacyInfoList(@PathVariable Integer pid){ return pharmacyInfoRepository.getAdminPharmacyInfo(pid); }

    // Record Details
    @PostMapping("/addRR")
    public String addRecord(@RequestBody Record record){
        Record record1=new Record(null,record.getPid(),record.getDid(),record.getDocname(),record.getCity(),"pending",record.getPhid());
        service.saveRecord(record1);
        return "Record Added Successfully";
    }
    @PostMapping("/addR")
    public List<Record> addRecords(@RequestBody List<Record> records){ return service.addRecords(records); }
    @GetMapping("/updateRS")
    public String updateRecordStatus(@RequestBody Integer id){ return service.updateRecordStatus(id); }
    @GetMapping("/getRPending")
    public List<Record> getPendingRecord(){ return service.getPendingRecord(); }
    /*
    @GetMapping("/addRR/{pid}/{did}/{docname}/{city}")
    public String insertRecord(@PathVariable Integer pid, @PathVariable Integer did, @PathVariable String docname, @PathVariable String city){
        return service.insertRecord(pid,did,docname,city);
    }
     */

    // Drug Details
    @PostMapping("/addD")
    public List<Drug> addDrugs(@RequestBody List<Drug> drugs){ return service.addDrugs(drugs); }
    @GetMapping("/getD")
    public List<Drug> getDrugList(){ return service.getDrugList(); }
    @DeleteMapping("/deleteD/{id}")
    public String deleteDrug(@PathVariable Integer id){
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
    public String deleteInsurance(@PathVariable Integer id){
        return service.deleteInsurance(id);
    }
    @GetMapping("/getIS/{id}")
    public String getInsuranceStatus(@PathVariable Integer id){ return service.getInsuranceStatus(id); }

    // Doctor Details
    @PostMapping("/addDr")
    public List<Doctor> addDoctors(@RequestBody List<Doctor> doctors){ return service.addDoctors(doctors); }
    @GetMapping("/getDr")
    public List<Doctor> getDoctorList(){ return service.getDoctorList(); }
    @DeleteMapping("/deleteDr/{id}")
    public String deleteDoctor(@PathVariable Integer id){
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
    public String deletePharmacy(@PathVariable Integer id){
        return service.deletePharmacy(id);
    }

    //User Repository
    @PostMapping("/addUser")
    public User addPharmacies(@RequestBody User user){
        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        User userWithEncodedPassword=new User(user.getUid(),user.getUsername(),encodedPassword,user.getRole());
        return users.save(userWithEncodedPassword);
    }
}
