package Repository;

import Entity.Clinic;
import Entity.Doctor;

import java.util.List;

public class DoctorRepository extends GenericRepositoryImpl<Doctor, Long>{
    public List<Doctor> findClinicByDocId(Long clinicId) {
        try (var session = sessionFactory.openSession()) {
            String hql = " FROM Entity.Doctor d " +
                    " WHERE clinic.id = :id ";
            var query = session.createQuery(hql, Doctor.class);
            query.setParameter("id", clinicId);
            return query.getResultList();
        }
    }
    public Doctor findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Doctor.class, id);
            session.getTransaction().commit();
            return a;
        }
    }
}
