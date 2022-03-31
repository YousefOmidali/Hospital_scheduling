package Repository;

import Entity.Admin;
import Entity.Clinic;
import Entity.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicRepositoryTest {
    private ClinicRepository clinicRepository;
    private Clinic clinic;
    private Hospital hospital;

    @BeforeEach
    public void beforeEach() {
        clinicRepository = new ClinicRepository();
    }

    @Test
    void save() {
        clinic = new Clinic(null,"Brain",new Hospital(1L));

        clinicRepository.save(clinic);

        assertEquals(clinic.getName(), clinicRepository.findById(clinic.getId()).getName());
        clinicRepository.delete(clinic);
    }

    @Test
    void delete() {
        clinic = new Clinic(null,"Brain",new Hospital(1L));

        clinicRepository.save(clinic);
        clinicRepository.delete(clinic);

        assertNull(clinicRepository.findById(clinic.getId()));
    }

    @Test
    void update() {
        clinic = new Clinic(null,"Brain",new Hospital(1L));

        clinicRepository.save(clinic);
        clinic.setName("Hand");
        clinicRepository.update(clinic);

        assertEquals(clinic.getName(), clinicRepository.findById(clinic.getId()).getName());
        clinicRepository.delete(clinic);
    }

    @Test
    void findById() {
        clinic = new Clinic(null,"Brain",new Hospital(1L));

        clinicRepository.save(clinic);

        assertEquals(clinic.getName(), clinicRepository.findById(clinic.getId()).getName());
        clinicRepository.delete(clinic);
    }
}