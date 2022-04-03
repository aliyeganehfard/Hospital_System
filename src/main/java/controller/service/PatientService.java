package controller.service;

import model.entity.Patient;
import model.entity.Turn;
import model.repository.impl.PatientRepositoryImpl;
import model.repository.impl.RepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class PatientService extends Service<PatientRepositoryImpl, Patient,Integer> {
    private PatientRepositoryImpl patientRepository ;
    private final Service<RepositoryImpl<Turn, Integer>, Turn, Integer> turnService = new Service<>(new RepositoryImpl<>());
    public PatientService() {
        super(new PatientRepositoryImpl());
        patientRepository = new PatientRepositoryImpl();
    }

    public Patient login(String username, String password){
        return patientRepository.login(Patient.class,username,password);
    }
    public List<Turn> getHistory(Patient patient){
        return turnService.findAll(Turn.class)
                .stream()
                .filter(turn -> turn.getPatient().equals(patient))
                .collect(Collectors.toList());
    }

}
