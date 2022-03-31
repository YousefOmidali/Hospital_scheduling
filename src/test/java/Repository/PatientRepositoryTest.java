package Repository;

import Entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientRepositoryTest {
    private PatientRepository patientRepository;
    private Patient patient;

    @BeforeEach
    public void beforeEach() {
        patientRepository = new PatientRepository();
    }

    @Test
    void save() {
        patient = new Patient(null, "b", "b", "kamyar", "123456789");

        patientRepository.save(patient);

        assertEquals("kamyar", patientRepository.findById(patient.getId()).getFullName());
        patientRepository.delete(patient);
    }

    @Test
    void update() {
        patient = new Patient(null, "b", "b", "kamyar", "123456789");

        patientRepository.save(patient);
        patient.setFullName("mahmoud");
        patientRepository.update(patient);

        assertEquals("mahmoud", patientRepository.findById(patient.getId()).getFullName());
        patientRepository.delete(patient);
    }

    @Test
    void delete() {
        patient = new Patient(null, "b", "b", "kamyar", "123456789");

        patientRepository.save(patient);
        patientRepository.delete(patient);

        assertNull(patientRepository.findById(patient.getId()));

    }

    @Test
    void login() {
        patient = new Patient(null, "b", "b", "kamyar", "123456789");

        patientRepository.save(patient);

        assertNotNull(patientRepository.login("b", "b"));
        patientRepository.delete(patient);
    }

    @Test
    void findById() {
        patient = new Patient(null, "b", "b", "kamyar", "123456789");

        patientRepository.save(patient);

        assertNotNull(patientRepository.findById(patient.getId()));
        patientRepository.delete(patient);
    }
}