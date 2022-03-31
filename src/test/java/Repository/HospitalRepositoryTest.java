package Repository;

import Entity.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HospitalRepositoryTest {
    private HospitalRepository hospitalRepository;
    private Hospital hospital;
    private Hospital hospital2;
    private Hospital hospital3;

    @BeforeEach
    public void beforeEach() {
        hospitalRepository = new HospitalRepository();
    }

    @Test
    void save() {
        hospital = new Hospital(null, "Azadi", "Kish");

        hospitalRepository.save(hospital);

        assertEquals("Azadi", hospitalRepository.findById(hospital.getId()).getName());
        hospitalRepository.delete(hospital);
    }

    void delete() {
        hospital = new Hospital(null, "Azadi", "Kish");

        hospitalRepository.save(hospital);
        hospitalRepository.delete(hospital);

        assertNull(hospitalRepository.findById(hospital.getId()));
    }

    void update() {
        hospital = new Hospital(null, "Azadi", "Kish");

        hospitalRepository.save(hospital);
        hospital.setAddress("Qeshm");
        hospitalRepository.update(hospital);

        assertEquals("Qeshm", hospitalRepository.findById(hospital.getId()).getAddress());
        hospitalRepository.delete(hospital);
    }

    @Test
    void findAll() {
        hospital = new Hospital(null, "Azadi", "Kish");
        hospital2 = new Hospital(null, "Mehr", "Qeshm");
        hospital3 = new Hospital(null, "Mellat", "Tehran");

        hospitalRepository.save(hospital);
        hospitalRepository.save(hospital2);
        hospitalRepository.save(hospital3);

        assertTrue(hospitalRepository.findAll().size() >= 3);

    }

    @Test
    void findById() {
        hospital = new Hospital(null, "Azadi", "Kish");

        hospitalRepository.save(hospital);

        assertNotNull(hospitalRepository.findById(hospital.getId()));
    }
}