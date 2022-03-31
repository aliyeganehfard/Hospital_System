package controller.service;

import model.entity.Doctor;
import model.repository.impl.DoctorRepositoryImpl;

public class DoctorServiceImpl extends Service<DoctorRepositoryImpl, Doctor,Integer> {
    public DoctorServiceImpl() {
        super(new DoctorRepositoryImpl());
    }
}
