import controller.exception.*;
import controller.exception.exceptionHandler.ExceptionHandler;
import controller.service.DoctorService;
import controller.service.PatientService;
import controller.service.SecretaryService;
import controller.service.Service;
import model.entity.*;
import model.repository.impl.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var doctorService = new DoctorService();
        var patientService = new PatientService();
        var secretaryService = new SecretaryService();
        var clinicService = new Service<RepositoryImpl<Clinic, Integer>, Clinic, Integer>(new RepositoryImpl<>());
        var prescriptionService = new Service<RepositoryImpl<Prescription, Integer>, Prescription, Integer>(new RepositoryImpl<>());
        var turnService = new Service<RepositoryImpl<Turn, Integer>, Turn, Integer>(new RepositoryImpl<>());
        var exceptionHandler = new ExceptionHandler();
        Doctor doctor = null;
        Clinic clinic = null;
        Patient patient = null;
        Prescription prescription = null;
        Secretary secretary = null;
        Turn turn;
        boolean flag = false;
        boolean state = true;
        int permission = -1;
        String id = "";
        String commendLine;
        String[] commend;
        while (state) {
            printLoginForm();
            System.out.print("comment: ");
            commendLine = scn.nextLine().trim();
            commend = commendLine.split(" ");
            switch (commend[0]) {
                case "addClinic":
                    try {
                        clinic = Clinic.builder()
                                .id(null)
                                .name(commend[1])
                                .build();
                        clinicService.save(clinic);
                    } catch (Exception e) {
                        System.out.println("wrong input");
                    }
                    break;
                case "registerSecretory":
                    try {
                        exceptionHandler.isId(commend[3]);
                        clinic = clinicService.findById(Clinic.class, Integer.valueOf(commend[3]));
                        if (clinic == null) {
                            throw new ClinicNotFoundException("clinic not found");
                        }
                        secretary = Secretary.builder()
                                .id(null)
                                .username(commend[1])
                                .password(commend[2])
                                .clinic(clinic)
                                .name(commend[4])
                                .build();
                        secretaryService.save(secretary);
                    } catch (IdException | ClinicNotFoundException exception) {
                        System.out.println(exception.getMessage());
                    } catch (Exception e) {
                        System.out.println("wrong input");
                    }
                    break;
                case "registerPatient":
                    System.out.println("registerPatient username password name");
                    try {
                        patient = Patient.builder()
                                .id(null)
                                .username(commend[1])
                                .password(commend[2])
                                .name(commend[3])
                                .build();
                        patientService.save(patient);
                    } catch (Exception e) {
                        System.out.println("wrong input");
                    }
                    break;
                case "login":
                    try {
                        secretary = secretaryService.login(commend[1], commend[2]);
                        patient = patientService.login(commend[1], commend[2]);
                        doctor = doctorService.login(commend[1], commend[2]);
                        if (patient != null) {
                            permission = 1;
                            flag = true;
                            printPatientMenu();
                            break;
                        }
                        if (secretary != null) {
                            permission = 2;
                            flag = true;
                            printSecretaryMenu();
                            break;
                        }
                        if (doctor != null) {
                            permission = 3;
                            flag = true;
                            printDoctorMenu();
                            break;
                        }
                        System.out.println("user not found");
                    } catch (Exception e) {
                        System.out.println("wrong input");
                    }
                    break;
                case "showClinic":
                    clinicService.findAll(Clinic.class).forEach(System.out::println);
                    break;
                default:
                    System.out.println("wrong input");
                    break;
            }

            while (flag) {

                switch (permission) {
                    case 1:
                        System.out.print("commend : ");
                        commendLine = scn.nextLine().trim();
                        commend = commendLine.split(" ");
                        switch (commend[0]) {
                            case "showProfile":
                                System.out.println(patientService.findById(Patient.class, patient.getId()));
                                break;
                            case "showInformation":
                                try {
                                    patientService.getHistory(patient).forEach(System.out::println);
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "showClinic":
                                clinicService.findAll(Clinic.class).forEach(System.out::println);
                                break;
                            case "showDoctor":
                                doctorService.findAll(Doctor.class).forEach(System.out::println);
                                break;
                            case "showTurn":
                                try {
                                    secretaryService.getTurns().forEach(System.out::println);
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "bookTurn":
                                try {
                                    exceptionHandler.isId(commend[1]);
                                    turn = turnService.findById(Turn.class, Integer.valueOf(commend[1]));
                                    if (turn == null)
                                        throw new TurnNotFoundException("turn not find");
                                    turn.setPatient(patient);
                                    turnService.update(turn);
                                } catch (IdException exception) {
                                    System.out.println(exception.getMessage());
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "help":
                                printPatientMenu();
                                break;
                            case "logout":
                                flag = false;
                                break;
                            case "exit":
                                state = false;
                                flag = false;
                                break;
                            default:
                                System.out.println("wrong input!");
                                break;
                        }
                        break;
                    case 2:
                        System.out.print("commend : ");
                        commendLine = scn.nextLine().trim();
                        commend = commendLine.split(" ");
                        switch (commend[0]) {
                            case "showProfile":
                                System.out.println(secretaryService.findById(Secretary.class, secretary.getId()));
                                break;
                            case "showPatients":
                                patientService.findAll(Patient.class).forEach(System.out::println);
                                break;
                            case "showClinics":
                                clinicService.findAll(Clinic.class).forEach(System.out::println);
                                break;
                            case "showDoctors":
                                doctorService.findAll(Doctor.class).forEach(System.out::println);
                                break;
                            case "showHistory":
                                secretaryService.getClinicHistory(secretary).forEach(System.out::println);
                                break;
                            case "showWaitingLinesTurn":
                                try {
                                    secretaryService.getWaitingLineTurns(secretary).forEach(System.out::println);
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "showPatientHistory":
                                try {
                                    exceptionHandler.isId(commend[1]);
                                    patient = patientService.findById(Patient.class, Integer.valueOf(commend[1]));
                                    if (patient == null)
                                        throw new PatientNotFoundException("patient not found");
                                    patientService.getHistory(patient).forEach(System.out::println);
                                } catch (IdException | PatientNotFoundException exception) {
                                    System.out.println(exception.getMessage());
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "addTurn":
                                try {
                                    exceptionHandler.isId(commend[3]);
                                    exceptionHandler.isId(commend[4]);
                                    clinic = clinicService.findById(Clinic.class, Integer.valueOf(commend[3]));
                                    doctor = doctorService.findById(Doctor.class, Integer.valueOf(commend[4]));
                                    if (clinic == null)
                                        throw new ClinicNotFoundException("clinic not found");
                                    if (doctor == null)
                                        throw new DoctorNotFoundException("doctor not found");
                                    if (commend[5].equals("waiting_line"))
                                        patient = null;
                                    else {
                                        exceptionHandler.isId(commend[5]);
                                        patient = patientService.findById(Patient.class, Integer.valueOf(commend[5]));
                                        if (patient == null)
                                            throw new PatientNotFoundException("patient not found");
                                    }
                                    turn = Turn.builder()
                                            .id(null)
                                            .date(Date.valueOf(commend[1]))
                                            .time(Time.valueOf(commend[2]))
                                            .clinic(clinic)
                                            .doctor(doctor)
                                            .patient(patient)
                                            .prescription(null)
                                            .build();
                                    secretaryService.checkTurn(secretary, turn);
                                    turnService.save(turn);

                                } catch (IdException | TurnBookedException | ClinicNotFoundException | DoctorNotFoundException | PatientNotFoundException exception) {
                                    System.out.println(exception.getMessage());
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "addPatient":
                                try {
                                    patient = Patient.builder()
                                            .id(null)
                                            .username(commend[1])
                                            .password(commend[2])
                                            .name(commend[3])
                                            .build();
                                    patientService.save(patient);
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "addDoctor":
                                try {
                                    doctor = Doctor.builder()
                                            .id(null)
                                            .username(commend[1])
                                            .password(commend[2])
                                            .name(commend[3])
                                            .specialty(commend[4])
                                            .clinic(secretary.getClinic())
                                            .build();
                                    doctorService.save(doctor);
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "help":
                                printSecretaryMenu();
                                break;
                            case "logout":
                                flag = false;
                                break;
                            case "exit":
                                state = false;
                                flag = false;
                                break;
                            default:
                                System.out.println("wrong input!");
                                break;
                        }
                        break;
                    case 3:
                        System.out.print("commend : ");
                        commendLine = scn.nextLine().trim();
                        commend = commendLine.split(" ");
                        switch (commend[0]) {
                            case "showProfile":
                                System.out.println(doctorService.findById(Doctor.class, doctor.getId()));
                                break;
                            case "showTurn":
                                doctorService.getTurns(doctor).forEach(System.out::println);
                                break;
                            case "setPrescription":
                                try {
                                    exceptionHandler.isId(commend[1]);
                                    turn = turnService.findById(Turn.class, Integer.valueOf(commend[1]));
                                    if (turn == null)
                                        throw new TurnNotFoundException("turn not found");
                                    prescription = Prescription.builder()
                                            .id(null)
                                            .description(commend[2])
                                            .build();
                                    prescriptionService.save(prescription);
                                    turn.setPrescription(prescription);
                                    turnService.update(turn);
                                } catch (IdException | TurnNotFoundException exception) {
                                    System.out.println(exception.getMessage());
                                } catch (Exception e) {
                                    System.out.println("wrong input");
                                }
                                break;
                            case "help":
                                break;
                            case "logout":
                                flag = false;
                                break;
                            case "exit":
                                state = false;
                                flag = false;
                                break;
                            default:
                                System.out.println("wrong input!");
                                break;
                        }
                        break;
                }

            }
        }
    }

    public static void printLoginForm() {
        System.out.println("addClinic name");
        System.out.println("registerSecretory username password clinicId name");
        System.out.println("registerPatient username password name");
        System.out.println("login userName password ");
        System.out.println("showClinic");
    }

    public static void printPatientMenu() {
        System.out.println("showProfile");
        System.out.println("showInformation");
        System.out.println("showClinic");
        System.out.println("showDoctor");
        System.out.println("showTurn");
        System.out.println("bookTurn turnId");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");

    }

    public static void printSecretaryMenu() {
        System.out.println("showPatients");
        System.out.println("showClinics");
        System.out.println("showDoctors");
        System.out.println("showHistory");
        System.out.println("showWaitingLinesTurn");
        System.out.println("showPatientHistory patientId");
        System.out.println("addTurn date time clinicId doctorId patientId||write->(waiting_line)");
        System.out.println("addPatient username password name");
        System.out.println("addDoctor username password name specialty");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");
    }

    public static void printDoctorMenu() {
        System.out.println("showProfile");
        System.out.println("showTurn");
        System.out.println("setPrescription turnId description");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");
    }

}

