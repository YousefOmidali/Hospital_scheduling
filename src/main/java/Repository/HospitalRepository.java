package Repository;

import Entity.Hospital;

import java.util.List;

public class HospitalRepository extends GenericRepositoryImpl<Hospital, Long>{
    public List<Hospital> findAll() {
        var session = sessionFactory.openSession();
        String hql = " FROM Entity.Hospital h ";
        var query = session.createQuery(hql, Hospital.class);
        return query.getResultList();
    }

    public Hospital findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Hospital.class, id);
            session.getTransaction().commit();
            return a;
        }
    }
}
