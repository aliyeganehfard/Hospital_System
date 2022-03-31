package model.repository.impl;

import controller.service.Service;
import model.entity.Clinic;
import model.entity.Doctor;
import model.entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.Doc;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryImplTest {
    private Service<RepositoryImpl<Doctor, Integer>, Doctor, Integer> repositoryDoctor;
    private Service<RepositoryImpl<Clinic, Integer>, Clinic, Integer> repositoryClinic;

    @BeforeEach
    public void beforeEach() {
        repositoryDoctor = new Service<>(new RepositoryImpl<>());
        repositoryClinic = new Service<>(new ClinicRepositoryImpl());
    }


    @Test
    void save() {
        //        arrange
        var doctor = new Doctor(null, "azad", null);
//        act
        repositoryDoctor.save(doctor);
        var load = repositoryDoctor.findById(Doctor.class, doctor.getId());
//        assert
        Assertions.assertThat(load).isNotNull();
    }

    @Test
    void update() {
        //        arrange
        var clinic = new Clinic(null, "aaa");
//        act
        repositoryClinic.save(clinic);
        repositoryClinic.update(new Clinic(clinic.getId(), "tehran"));
        var load = repositoryClinic.findById(Clinic.class, clinic.getId());
//        assert
        Assertions.assertThat("tehran").isEqualTo(load.getName());
    }

    @Test
    void delete() {
        //        arrange
        var doctor = new Doctor(null, "dff",null);
//        act
        repositoryDoctor.save(doctor);
        repositoryDoctor.delete(doctor);
        var load = repositoryDoctor.findById(Doctor.class,doctor.getId());
//        assert
        Assertions.assertThat(load).isNull();
    }

    @Test
    void deleteById() {

    }

    @Test
    void findById() {
        //        arrange
        var doctor = new Doctor(null, "sddff",null);
//        act
        repositoryDoctor.save(doctor);
        var load = repositoryDoctor.findById(Doctor.class,doctor.getId());
//        assert
        Assertions.assertThat(load).isNotNull();
    }

    @Test
    void findAll() {
        //        arrange
        var doctor = new Doctor(null, "sddff",null);
//        act
        var size = repositoryDoctor.findAll(Doctor.class);
        repositoryDoctor.save(doctor);
        size.add(doctor);
//        assert
        Assertions.assertThat(size).hasSize(repositoryDoctor.findAll(Doctor.class).size());
    }
}