package controller.service;

import model.entity.Doctor;
import model.entity.Turn;
import model.repository.impl.DoctorRepositoryImpl;
import model.repository.impl.RepositoryImpl;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorService extends Service<DoctorRepositoryImpl, Doctor,Integer> {
    private DoctorRepositoryImpl doctorRepository ;
    private final Service<RepositoryImpl<Turn, Integer>, Turn, Integer> turnService = new Service<>(new RepositoryImpl<>());
    public DoctorService() {
        super(new DoctorRepositoryImpl());
        doctorRepository = new DoctorRepositoryImpl();
    }
    public Doctor login(String username, String password){
        return doctorRepository.login(Doctor.class,username,password);
    }

    public List<Turn> getTurns(Doctor doctor){
        return turnService.findAll(Turn.class)
                .stream()
                .filter(turn -> turn.getDoctor().equals(doctor) && turn.getPrescription()==null)
                .collect(Collectors.toList());
    }
}
