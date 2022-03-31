package Service;

import Entity.Clinic;
import Entity.Doctor;
import Repository.DoctorRepository;

import java.util.List;

public class DoctorService {
    private DoctorRepository doctorRepository = new DoctorRepository();

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    public void update(Doctor doctor) {
        doctorRepository.update(doctor);
    }

    public List<Doctor> findClinicByDocId(Long clinicId) {
        return doctorRepository.findClinicByDocId(clinicId);
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id);
    }
}
