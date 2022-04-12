import Entity.*;
import Service.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        ClinicService clinicService = new ClinicService();
        DoctorService doctorService = new DoctorService();
        HospitalService hospitalService = new HospitalService();
        PatientService patientService = new PatientService();
        PrescriptionService prescriptionService = new PrescriptionService();
        ScheduleService scheduleService = new ScheduleService();
        Scanner scanner = new Scanner(System.in);
        Admin admin;
        Clinic clinic;
        Doctor doctor;
        Hospital hospital;
        Patient patient;
        Prescription prescription;
        Schedule schedule;
        String username;
        String password;
        String fullName;
        String nationalCode = null;
        String prescriptionTxt;
        String name;
        String address;
        int whatToDo = 0;
        int role = 0;
        long longId = 0L;
        String date;
        boolean loop = true;

        System.out.println("1.Register \n2.Login");
        try {
            whatToDo = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong Input");
        }
        if (whatToDo == 1) {
            System.out.println("what are you? \n1.Admin      2.Patient ");
            try {
                role = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input");
            }
            if (role == 1) {
                scanner.nextLine();
                System.out.println("enter username: ");
                username = scanner.nextLine();
                System.out.println("enter password: ");
                password = scanner.nextLine();
                admin = new Admin(null, username, password);
                adminService.save(admin);
            } else if (role == 2) {
                scanner.nextLine();
                System.out.println("enter username: ");
                username = scanner.nextLine();
                System.out.println("enter username: ");
                password = scanner.nextLine();
                System.out.println("enter fullName: ");
                fullName = scanner.nextLine();
                System.out.println("enter your nationalCode: ");
                try {
                    nationalCode = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Wrong Input");
                }
                patient = new Patient(null, username, password, fullName, nationalCode);
                patientService.save(patient);
            }

        } else if (whatToDo == 2) {
            System.out.println("what are you? \n1.Admin     2.Patient ");
            try {
                role = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input");
            }
            if (role == 1) {
                scanner.nextLine();
                System.out.println("enter username: ");
                username = scanner.nextLine();
                System.out.println("enter password: ");
                password = scanner.nextLine();
                admin = adminService.login(username, password);
                if (admin != null) {
                    while (loop) {
                        System.out.println("Menu: \n1.Add hospital \n2.Add clinic to a hospital " +
                                "\n3.Add doctor \n4.edite a prescription \n5.see a schedule \n6.Exit");
                        role = scanner.nextInt();
                        switch (role) {
                            case 1:
                                scanner.nextLine();
                                System.out.println("enter hospital name: ");
                                name = scanner.nextLine();
                                System.out.println("enter address: ");
                                address = scanner.nextLine();
                                hospital = new Hospital(null, name, address);
                                hospitalService.save(hospital);
                                break;
                            case 2:
                                System.out.println("enter hospital id: ");
                                try {
                                    longId = scanner.nextLong();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong Input");
                                }
                                hospital = hospitalService.findById(longId);
                                scanner.nextLine();
                                System.out.println("enter name: ");
                                name = scanner.nextLine();
                                clinic = new Clinic(null, name, hospital);
                                clinicService.save(clinic);
                                break;
                            case 3:
                                scanner.nextLine();
                                System.out.println("enter doc full name: ");
                                fullName = scanner.nextLine();
                                System.out.println("enter clinic id: ");
                                try {
                                    longId = scanner.nextLong();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong Input");
                                }
                                clinic = clinicService.findById(longId);
                                doctor = new Doctor(null, fullName, clinic);
                                doctorService.save(doctor);
                                break;
                            case 4:
                                scanner.nextLine();
                                System.out.println("enter prescription id: ");
                                try {
                                    longId = scanner.nextLong();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong Input");
                                }
                                prescription = prescriptionService.findById(longId);
                                scanner.nextLine();
                                System.out.println("enter new prescription text: ");
                                prescriptionTxt = scanner.nextLine();
                                prescription.setPrescriptionText(prescriptionTxt);
                                prescriptionService.update(prescription);
                                break;
                            case 5:
                                System.out.println("enter schedule id: ");
                                try {
                                    longId = scanner.nextLong();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong Input");
                                }
                                System.out.println(scheduleService.findById(longId));
                                break;
                            case 6:
                                loop = false;
                                break;
                        }
                    }
                }

            } else if (role == 2) {
                scanner.nextLine();
                System.out.println("enter username: ");
                username = scanner.nextLine();
                System.out.println("enter password: ");
                password = scanner.nextLine();
                patient = patientService.login(username, password);
                if (patient != null) {
                    while (loop) {
                        System.out.println("Menu: \n1.self Info \n2.prescription history " +
                                "\n3.show all hospitals \n4.show all clinics of a hospital " +
                                "\n5.show all schedule of a doctor \n6.reserve a schedule " +
                                "\n7.write a prescription (Only for Doctors!) \n8.Exit");
                        role = scanner.nextInt();
                        switch (role) {
                            case 1:
                                System.out.println(patient);
                                break;
                            case 2:
                                prescriptionService.findByPatientId(patient.getId())
                                        .forEach(System.out::println);
                                break;
                            case 3:
                                hospitalService.findAll().forEach(System.out::println);
                                break;
                            case 4:
                                System.out.println("enter hospital id: ");
                                longId = scanner.nextLong();
                                clinicService.findByHospitalId(longId).forEach(System.out::println);
                                break;
                            case 5:
                                scanner.nextLine();
                                System.out.println("enter doc id: ");
                                try {
                                    longId = scanner.nextLong();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong Input");
                                }
                                scheduleService.findByDocId(longId).forEach(System.out::println);
                                break;
                            case 6:
                                scanner.nextLine();
                                System.out.println("enter doc id: ");
                                try {
                                    longId = scanner.nextLong();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong Input");
                                }
                                doctor = doctorService.findById(longId);
                                System.out.println("enter your id: ");
                                try {
                                    longId = scanner.nextLong();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong Input");
                                }
                                patient = patientService.findById(longId);
                                System.out.println("enter schedule date: \t(example: 2022-03-01 14:00)---> yyyy-mm-dd hh ");
                                scanner.nextLine();
                                date = scanner.nextLine();
                                schedule = new Schedule(null, patient, doctor, date);
                                scheduleService.save(schedule);
                                break;
                            case 7:
                                scanner.nextLine();
                                System.out.println("write the prescription: ");
                                prescriptionTxt = scanner.nextLine();
                                prescription = new Prescription(null, prescriptionTxt, patient);
                                prescriptionService.save(prescription);
                                break;
                            case 8:
                                loop = false;
                                break;
                        }
                    }
                }
            }
        }
    }
}
