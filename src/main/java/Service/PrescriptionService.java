package Service;

import Entity.Patient;
import Entity.Prescription;
import Repository.PatientRepository;
import Repository.PrescriptionRepository;

import java.util.List;

public class PrescriptionService {
    private PrescriptionRepository prescriptionRepository = new PrescriptionRepository();

    public void save(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    public void deleteById(Long id) {
        prescriptionRepository.deleteById(id);
    }

    public void update(Prescription prescription) {
        prescriptionRepository.update(prescription);
    }

    public List<Prescription> findByPatientId(Long id) {
        return prescriptionRepository.findByPatientId(id);
    }

    public Prescription findById(Long id) {
        return prescriptionRepository.findById(id);
    }
}
