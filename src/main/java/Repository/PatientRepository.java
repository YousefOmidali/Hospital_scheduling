package Repository;

import Entity.Admin;
import Entity.Patient;

public class PatientRepository extends GenericRepositoryImpl<Patient, Long>{
    public Patient login(String username, String password) {
        var session = sessionFactory.openSession();
        String hql = " FROM Entity.Patient p " +
                " WHERE p.userName = :username " +
                " AND p.password = :password ";
        var query = session.createQuery(hql, Patient.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
    public Patient findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Patient.class, id);
            session.getTransaction().commit();
            return a;
        }
    }
}
