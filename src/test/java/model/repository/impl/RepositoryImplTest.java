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
        repositoryClinic = new Service<>(new RepositoryImpl<>());
    }


    @Test
    void save() {
        //        arrange
        var doctor = Doctor.builder()
                .id(null)
                .username("a")
                .password("a")
                .name("b")
                .specialty("d")
                .clinic(null)
                .build();
//        act
        repositoryDoctor.save(doctor);
        var load = repositoryDoctor.findById(Doctor.class, doctor.getId());
//        assert
        Assertions.assertThat(load).isNotNull();
    }

    @Test
    void update() {
        //        arrange
//        var clinic = new Clinic("aaa");
        var clinic = Clinic.builder()
                .id(null)
                .name("lar")
                .build();
//        act
        repositoryClinic.save(clinic);
        repositoryClinic.update(
                Clinic.builder()
                        .id(clinic.getId())
                        .name("bbb")
                        .build()
        );
        var load = repositoryClinic.findById(Clinic.class, clinic.getId());
        System.out.println("--------------------------------------");
        System.out.println(load);
//        assert
        Assertions.assertThat("bbb").isEqualTo(load.getName());
    }

    @Test
    void delete() {
        //        arrange
        var doctor = Doctor.builder()
                .id(null)
                .username("a")
                .password("aa")
                .name("ali")
                .specialty("dnt")
                .clinic(null)
                .build();
//        act
        repositoryDoctor.save(doctor);
        repositoryDoctor.delete(doctor);
        var load = repositoryDoctor.findById(Doctor.class, doctor.getId());
//        assert
        Assertions.assertThat(load).isNull();
    }

    @Test
    void deleteById() {

    }

    @Test
    void findById() {
        //        arrange
        var doctor = Doctor.builder()
                .id(null)
                .username("a")
                .password("aa")
                .name("ali")
                .specialty("dnt")
                .clinic(null)
                .build();
//        act
        repositoryDoctor.save(doctor);
        var load = repositoryDoctor.findById(Doctor.class,doctor.getId());
//        assert
        Assertions.assertThat(load).isNotNull();
    }

    @Test
    void findAll() {
        //        arrange
        var doctor = Doctor.builder()
                .id(null)
                .username("a")
                .password("aa")
                .name("ali")
                .specialty("dnt")
                .clinic(null)
                .build();
//        act
        var size = repositoryDoctor.findAll(Doctor.class);
        repositoryDoctor.save(doctor);
        size.add(doctor);
//        assert
        Assertions.assertThat(size).hasSize(repositoryDoctor.findAll(Doctor.class).size());
    }
}