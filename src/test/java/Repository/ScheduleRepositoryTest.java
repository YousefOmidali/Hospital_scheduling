package Repository;

import Entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleRepositoryTest {
    private ScheduleRepository scheduleRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private Schedule schedule;
    private Schedule schedule1;

    @BeforeEach
    public void beforeEach() {
        doctorRepository = new DoctorRepository();
        scheduleRepository = new ScheduleRepository();
        patientRepository = new PatientRepository();
    }

    @Test
    void save() {
        schedule = new Schedule(null, patientRepository.findById(1L),
                doctorRepository.findById(1L), "2022-03-03 15:00");

        scheduleRepository.save(schedule);

        assertEquals("2022-03-03 15:00",
                scheduleRepository.findById(schedule.getId()).getDate());
        scheduleRepository.delete(schedule);
    }

    @Test
    void update() {
        schedule = new Schedule(null, patientRepository.findById(1L),
                doctorRepository.findById(1L), "2022-03-03 15:00");

        scheduleRepository.save(schedule);
        schedule.setDate("2022-04-02 16:00");
        scheduleRepository.update(schedule);

        assertEquals("2022-04-02 16:00",
                scheduleRepository.findById(schedule.getId()).getDate());
        scheduleRepository.delete(schedule);
    }

    @Test
    void delete() {
        schedule = new Schedule(null, patientRepository.findById(1L),
                doctorRepository.findById(1L), "2022-03-03 15:00");

        scheduleRepository.save(schedule);
        scheduleRepository.delete(schedule);

        assertNull(scheduleRepository.findById(schedule.getId()));
    }

    @Test
    void findByDocId() {
        schedule = new Schedule(null, patientRepository.findById(1L),
                doctorRepository.findById(1L), "2022-03-03 15:00");
        schedule1 = new Schedule(null, patientRepository.findById(1L),
                doctorRepository.findById(1L), "2022-03-03 16:00");

        scheduleRepository.save(schedule);
        scheduleRepository.save(schedule1);

        assertNotNull(scheduleRepository.findByDocId(1L).size() >= 2);
        scheduleRepository.delete(schedule);
        scheduleRepository.delete(schedule1);
    }

    @Test
    void findById() {
        schedule = new Schedule(null, patientRepository.findById(1L),
                doctorRepository.findById(1L), "2022-03-03 15:00");

        scheduleRepository.save(schedule);

        assertNotNull(scheduleRepository.findById(schedule.getId()));
        scheduleRepository.delete(schedule);
    }
}