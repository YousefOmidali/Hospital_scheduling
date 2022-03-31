package Repository;

import Entity.Prescription;

import java.util.List;

public class PrescriptionRepository extends GenericRepositoryImpl<Prescription, Long>{
    public List<Prescription> findByPatientId(Long id) {
        try (var session = sessionFactory.openSession()) {
            String hql = " FROM Entity.Prescription p " +
                    " WHERE patient.id = :id ";
            var query = session.createQuery(hql, Prescription.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }
    public Prescription findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Prescription.class, id);
            session.getTransaction().commit();
            return a;
        }
    }

}
