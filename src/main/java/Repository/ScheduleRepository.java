package Repository;

import Entity.Prescription;
import Entity.Schedule;

import java.util.List;

public class ScheduleRepository extends GenericRepositoryImpl<Schedule,Long>{
    public List<Schedule> findByDocId(Long id) {
        try (var session = sessionFactory.openSession()) {
            String hql = " FROM Entity.Schedule s " +
                    " WHERE doctor.id = :id ";
            var query = session.createQuery(hql, Schedule.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }
    public Schedule findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Schedule.class, id);
            session.getTransaction().commit();
            return a;
        }
    }
}
