package Service;

import Entity.Admin;
import Entity.Clinic;
import Repository.ClinicRepository;

import java.util.List;

public class ClinicService {
    private ClinicRepository clinicRepository = new ClinicRepository();

    public void save(Clinic clinic) {
        clinicRepository.save(clinic);
    }

    public void deleteById(Long id) {
        clinicRepository.deleteById(id);
    }

    public void update(Clinic clinic) {
        clinicRepository.update(clinic);
    }

    public List<Clinic> findByHospitalId(Long id) {
        return clinicRepository.findByHospitalId(id);
    }

    public Clinic findById(Long id) {
        return clinicRepository.findById(id);
    }
}
