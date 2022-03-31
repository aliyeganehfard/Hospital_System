package controller.service;

import model.entity.Patient;
import model.repository.impl.PatientRepositoryImpl;

public class PatientServiceImpl extends Service<PatientRepositoryImpl, Patient,Integer> {
    public PatientServiceImpl() {
        super(new PatientRepositoryImpl());
    }
}
