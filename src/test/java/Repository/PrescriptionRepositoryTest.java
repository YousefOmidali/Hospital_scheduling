package Repository;

import Entity.Patient;
import Entity.Prescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.SecondaryTable;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionRepositoryTest {
    private PrescriptionRepository prescriptionRepository;
    private PatientRepository patientRepository;
    private Patient patient;
    private Prescription prescription;
    private Prescription prescription1;

    @BeforeEach
    public void beforeEach() {
        prescriptionRepository = new PrescriptionRepository();
        patientRepository = new PatientRepository();
    }

    @Test
    void save() {
        prescription = new Prescription(null,
                "Cold", patientRepository.findById(1L));

        prescriptionRepository.save(prescription);

        assertNotNull(prescriptionRepository.findById(prescription.getId()));
        prescriptionRepository.delete(prescription);
    }

    @Test
    void update() {
        prescription = new Prescription(null,
                "Cold", patientRepository.findById(1L));

        prescriptionRepository.save(prescription);
        prescription.setPrescriptionText("Caught");
        prescriptionRepository.update(prescription);

        assertEquals("Caught",
                prescriptionRepository.findById(prescription.getId()).getPrescriptionText());
        prescriptionRepository.delete(prescription);
    }

    @Test
    void delete() {
        prescription = new Prescription(null,
                "Cold", patientRepository.findById(1L));

        prescriptionRepository.save(prescription);
        prescriptionRepository.delete(prescription);

        assertNull(prescriptionRepository.findById(prescription.getId()));
    }

    @Test
    void findByPatientId() {
        prescription = new Prescription(null,
                "Cold", patientRepository.findById(1L));
        prescription1 = new Prescription(null,
                "Caught", patientRepository.findById(1L));

        prescriptionRepository.save(prescription);
        prescriptionRepository.save(prescription1);

        assertTrue(prescriptionRepository.findByPatientId(1L).size() >= 2);
        prescriptionRepository.delete(prescription);
        prescriptionRepository.delete(prescription1);
    }

    @Test
    void findById() {
        prescription = new Prescription(null,
                "Cold", patientRepository.findById(1L));

        prescriptionRepository.save(prescription);

        assertEquals("Cold", prescriptionRepository.findById(prescription.getId()).getPrescriptionText());
        prescriptionRepository.delete(prescription);
    }
}