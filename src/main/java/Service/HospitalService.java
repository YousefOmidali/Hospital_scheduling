package Service;

import Entity.Doctor;
import Entity.Hospital;
import Repository.HospitalRepository;

import java.util.List;

public class HospitalService {
    private HospitalRepository hospitalRepository = new HospitalRepository();

    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    public void deleteById(Long id) {
        hospitalRepository.deleteById(id);
    }

    public void update(Hospital hospital) {
        hospitalRepository.update(hospital);
    }

    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }
    public Hospital findById(Long id){return hospitalRepository.findById(id);}
}
