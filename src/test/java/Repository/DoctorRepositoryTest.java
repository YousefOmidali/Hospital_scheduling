package Repository;

import Entity.Clinic;
import Entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorRepositoryTest {
    private DoctorRepository doctorRepository;
    private HospitalRepository hospitalRepository;
    private ClinicRepository clinicRepository;
    private Doctor doctor;

    @BeforeEach
    public void beforeEach() {
        doctorRepository = new DoctorRepository();
        clinicRepository = new ClinicRepository();
        hospitalRepository = new HospitalRepository();
    }

    @Test
    void save() {
        doctor = new Doctor(null, "Mohammad Omidali", clinicRepository.findById(1L));

        doctorRepository.save(doctor);

        assertEquals(doctor.getFullName(), doctorRepository.findById(doctor.getId()).getFullName());
        doctorRepository.delete(doctor);
    }
    @Test
    void update() {
        doctor = new Doctor(null, "Mohammad Omidali", new Clinic(1L));

        doctorRepository.save(doctor);
        doctor.setFullName("Ali");
        doctorRepository.update(doctor);

        assertEquals("Ali",doctorRepository.findById(doctor.getId()).getFullName());
        doctorRepository.delete(doctor);
    }
    @Test
    void delete() {
        doctor = new Doctor(null, "Mohammad Omidali", new Clinic(1L));

        doctorRepository.save(doctor);
        doctorRepository.delete(doctor);

        assertNull(doctorRepository.findById(doctor.getId()));
        doctorRepository.delete(doctor);
    }


    @Test
    void findById() {
        doctor = new Doctor(null, "Mohammad Omidali", new Clinic(1L));

        doctorRepository.save(doctor);

        assertEquals("Mohammad Omidali",doctorRepository.findById(doctor.getId()).getFullName());
    }
}