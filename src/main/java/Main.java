import Entity.*;
import Service.*;

import java.sql.Date;
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
        Admin admin = null;
        Clinic clinic = null;
        Doctor doctor = null;
        Hospital hospital = null;
        Patient patient = null;
        Prescription prescription = null;
        Schedule schedule = null;
        String username = null;
        String password = null;
        String fullName = null;
        String nationalCode = null;
        String prescriptionTxt = null;
        String name = null;
        String address = null;
        Integer whatToDo = 0;
        Integer role = 0;
        Long longId = 0L;
        String date;
        Boolean loop = true;

        System.out.println("1.Register \n2.Login");
        whatToDo = scanner.nextInt();
        if (whatToDo == 1) {
            System.out.println("what are you? \n1.Admin      2.Patient ");
            role = scanner.nextInt();
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
                nationalCode = scanner.nextLine();
                patient = new Patient(null, username, password, fullName, nationalCode);
                patientService.save(patient);
            }

        } else if (whatToDo == 2) {
            System.out.println("what are you? \n1.Admin     2.Patient ");
            role = scanner.nextInt();
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
                                longId = scanner.nextLong();
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
                                longId = scanner.nextLong();
                                clinic = clinicService.findById(longId);
                                doctor = new Doctor(null, fullName, clinic);
                                doctorService.save(doctor);
                                break;
                            case 4:
                                System.out.println("enter prescription id: ");
                                longId = scanner.nextLong();
                                prescription = prescriptionService.findById(longId);
                                scanner.nextLine();
                                System.out.println("enter new prescription text: ");
                                prescriptionTxt = scanner.nextLine();
                                prescription.setPrescriptionText(prescriptionTxt);
                                prescriptionService.update(prescription);
                                break;
                            case 5:
                                System.out.println("enter schedule id: ");
                                longId = scanner.nextLong();
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
                                longId = scanner.nextLong();
                                clinicService.findByHospitalId(longId).forEach(System.out::println);
                                break;
                            case 5:
                                System.out.println("enter doc id: ");
                                longId = scanner.nextLong();
                                scheduleService.findByDocId(longId).forEach(System.out::println);
                                break;
                            case 6:
                                System.out.println("enter doc id: ");
                                longId = scanner.nextLong();
                                doctor = doctorService.findById(longId);
                                System.out.println("enter your id: ");
                                longId = scanner.nextLong();
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
