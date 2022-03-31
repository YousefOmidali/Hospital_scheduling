package Repository;

import Entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryConnection {
    private SessionFactoryConnection(){}
    private static class LazyHolder{
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(Clinic.class)
                    .addAnnotatedClass(Doctor.class)
                    .addAnnotatedClass(Hospital.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Prescription.class)
                    .addAnnotatedClass(Schedule.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }
    public static SessionFactory getInstance(){
        return LazyHolder.INSTANCE;
    }
}
