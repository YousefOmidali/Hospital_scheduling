package Service;

import Entity.Hospital;
import Entity.Patient;
import Repository.PatientRepository;

public class PatientService {
    private PatientRepository patientRepository = new PatientRepository();

    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public void update(Patient patient) {
        patientRepository.update(patient);
    }

    public Patient login(String username, String password) {
        return patientRepository.login(username, password);
    }
    public Patient findById(Long id) {
        return patientRepository.findById(id);
    }
}
