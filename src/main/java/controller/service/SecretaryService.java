package controller.service;

import controller.exception.IdException;
import controller.exception.TurnBookedException;
import model.entity.Patient;
import model.entity.Secretary;
import model.entity.Turn;
import model.repository.impl.PatientRepositoryImpl;
import model.repository.impl.RepositoryImpl;
import model.repository.impl.SecretaryRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class SecretaryService extends Service<SecretaryRepositoryImpl, Secretary,Integer> {
    private SecretaryRepositoryImpl secretaryRepository;
    private final Service<RepositoryImpl<Turn, Integer>, Turn, Integer> turnService = new Service<>(new RepositoryImpl<>());
    public SecretaryService() {
        super(new SecretaryRepositoryImpl());
        secretaryRepository = new SecretaryRepositoryImpl();
    }
    public Secretary login(String username , String password){
        return secretaryRepository.login(Secretary.class , username ,password);
    }
    public List<Turn> getClinicTurns(Secretary secretary){
        return turnService.findAll(Turn.class)
                .stream()
                .filter(turn -> turn.getPatient() != null && turn.getClinic().equals(secretary.getClinic()))
                .collect(Collectors.toList());
    }
    public List<Turn> getWaitingLineTurns(Secretary secretary){
        return turnService.findAll(Turn.class)
                .stream()
                .filter(turn -> turn.getPatient() == null && turn.getClinic().equals(secretary.getClinic()))
                .collect(Collectors.toList());
    }
    public void checkTurn(Secretary secretary,Turn turn){
        var load = getWaitingLineTurns(secretary);
        load
                .forEach(turn1 -> {
                    if (turn1.getDate().equals(turn.getDate()) && turn1.getTime().equals(turn.getTime())){
                            throw new TurnBookedException("this time already booked");
                    }
                });
    }
    public List<Turn> getTurns(){
        return turnService.findAll(Turn.class)
                .stream()
                .filter(turn -> turn.getPatient() == null)
                .collect(Collectors.toList());
    }
    public List<Turn> getClinicHistory(Secretary secretary){
        return turnService.findAll(Turn.class)
                .stream()
                .filter(turn -> turn.getPatient() != null && turn.getClinic().equals(secretary.getClinic()))
                .collect(Collectors.toList());
    }
}
