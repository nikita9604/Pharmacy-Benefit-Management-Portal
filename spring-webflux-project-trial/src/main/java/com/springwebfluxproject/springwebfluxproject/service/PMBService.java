package com.springwebfluxproject.springwebfluxproject.service;


import com.springwebfluxproject.springwebfluxproject.dto.RecordDTO;
import com.springwebfluxproject.springwebfluxproject.entity.*;
import com.springwebfluxproject.springwebfluxproject.entity.Record;
import com.springwebfluxproject.springwebfluxproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



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

    public PMBService() {
    }

    // Patient Repository
    // Add details in Patient sign up page - button
    public Mono<Patient> savePatient(Patient patient){ return patientRepository.save(patient); }
    public Flux<Patient> getPatientList(){ return patientRepository.findAll(); }
    // Record Repository
    // Update status from pending to confirmed
    public Mono<String> updateRecordStatus(int id){

        return recordRepository.findById(id).flatMap(existingProduct->{
            existingProduct.setStatus("confirmed");
            return recordRepository.save(existingProduct);
        }).map(updateProduct->"confirmed").defaultIfEmpty("product not found");

    }

    // Drug Repository
    public Flux<Drug> addDrugs(List<Drug> drugs){ return drugRepository.saveAll(drugs); }
    public Flux<Drug> getDrugList(){ return drugRepository.findAll(); }
    public Mono<String> deleteDrug(int did){
        return drugRepository.findById(did).flatMap(drug->drugRepository.delete(drug).then(Mono.just("Removed Drug with ID - " + did))).defaultIfEmpty("Drug Not found");

    }

    public Mono<String> IsDrugCoveredByInsurance(String dname,String plan){
        return drugRepository.checkIfDrugCoveredByInsurance(dname,plan).flatMap(check->{
            if (check == true)
                return Mono.just("Drug Is Covered By Your Insurance");
            else
                return Mono.just("Drug is not Covered");

        });

    }

//    //Check if the drug is present
//    public Mono<String> getDrugList(String dname){
//        return drugRepository.hasDrug(dname).flatMap(check->{
//            if (check == true)
//                return Mono.just("Drug Available");
//            else
//                return Mono.just("Drug Unavailable");
//
//        });


    // Insurance Repository
    public Flux<Insurance> addInsurances(List<Insurance> insurances){ return insuranceRepository.saveAll(insurances); }
    public Flux<Insurance> getInsuranceList(){ return insuranceRepository.findAll(); }
    public Mono<String> deleteInsurance(int inid){

        return insuranceRepository.findById(inid).flatMap(insurance->insuranceRepository.delete(insurance).then(Mono.just("Removed Insurance with ID" + inid))).defaultIfEmpty("Insurance Not found");

    }
    // Check if Insurance Id Exists or not - Button (Patient sign up page)
    public Mono<String> getInsuranceStatus(int inid) {
        return insuranceRepository.hasInid(inid).flatMap(check -> {
            if (check == true)
                return Mono.just("Match Found");
            else
                return Mono.just("Match Failed");
        });
    }


    // Doctor Repository
    public Flux<Doctor> addDoctors(List<Doctor> doctors){ return doctorRepository.saveAll(doctors); }
    public Flux<Doctor> getDoctorList(){ return doctorRepository.findAll(); }
    public Mono<String> deleteDoctor(int docid){
        return doctorRepository.findById(docid).flatMap(doc->doctorRepository.delete(doc).then(Mono.just("Removed Doctor with ID - " + docid))).defaultIfEmpty("Doctor Not found");

    }
    // Check if Doctor name Exists or not - Button (Add drug items by patient) - Prescription Check
    public Mono<String> getDoctorPrescription(String docname){
        return doctorRepository.hasDocname(docname).flatMap(check->{
            if (check == true)
                return Mono.just("Accepted");
            else
                return Mono.just("Denied");

        });

    }

    // Pharmacy Repository
    public Flux<Pharmacy> addPharmacies(List<Pharmacy> pharmacies){ return pharmacyRepository.saveAll(pharmacies); }
    public Flux<Pharmacy> getPharmacyList(){ return pharmacyRepository.findAll(); }
    public Mono<String> deletePharmacy(int phid){
        return pharmacyRepository.findById(phid).flatMap(pharmacy->pharmacyRepository.delete(pharmacy).then(Mono.just("Removed Pharmacy with ID - " + phid))).defaultIfEmpty("Pharmacy Not found");
    }
    public Flux<Pharmacy> getNearbyPharmacies(String dname,String city)
    {
        return pharmacyRepository.getAllPharmacyWithDrugNameInACity(dname,city);
    }
    //Record Repository
    public Mono<Record> addRecord(Record record){ return recordRepository.save(record); }
    public Flux<Record> getAllRecordsOfAPatient(String pname){ return patientRepository.findIdGivenName(pname).flatMapMany(pid->recordRepository.getAllRecordsOfAPatient(pid)); }
    public Flux<Record> getRecordList(){ return recordRepository.findAll(); }
    public Flux<RecordDTO> getAllApprovedRecords()
    {
        return recordRepository.getAllApprovedRecords();
    }


}
