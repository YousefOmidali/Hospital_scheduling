package Repository;

import Entity.Admin;
import Entity.Clinic;

public class AdminRepository extends GenericRepositoryImpl<Admin, Long>{

    public Admin login(String username, String password) {
        var session = sessionFactory.openSession();
        String hql = " FROM Entity.Admin a " +
                " WHERE a.userName = :username " +
                " AND a.password = :password ";
        var query = session.createQuery(hql, Admin.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
    public Admin findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var a = session.find(Admin.class, id);
            session.getTransaction().commit();
            return a;
        }
    }
}
