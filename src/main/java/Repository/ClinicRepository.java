package Repository;

import Entity.Clinic;
import Entity.Doctor;

import java.util.List;

public class ClinicRepository extends GenericRepositoryImpl<Clinic, Long>{
    public List<Clinic> findByHospitalId(Long id) {
        try (var session = sessionFactory.openSession()) {
            String hql = " FROM Entity.Clinic c " +
                    " WHERE hospital.id = :id ";
            var query = session.createQuery(hql, Clinic.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }
    public Clinic findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Clinic.class, id);
            session.getTransaction().commit();
            return a;
        }
    }
}
