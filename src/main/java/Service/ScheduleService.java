package Service;


import Entity.Prescription;
import Entity.Schedule;
import Repository.ScheduleRepository;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class ScheduleService {
    private ScheduleRepository scheduleRepository = new ScheduleRepository();

    public void save(Schedule schedule) {
        try {
            scheduleRepository.save(schedule);
        }catch (ConstraintViolationException e){
            System.out.println("XXXXX this date is already reserved! choose another date XXXXX");
        }
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }

    public void update(Schedule schedule) {
        scheduleRepository.update(schedule);
    }

    public List<Schedule> findByDocId(Long id) {
        return scheduleRepository.findByDocId(id);
    }

    public Schedule findById(Long id) {
        return scheduleRepository.findById(id);
    }

}
