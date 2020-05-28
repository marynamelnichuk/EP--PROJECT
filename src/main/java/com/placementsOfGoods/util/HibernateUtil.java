package com.placementsOfGoods.util;

import com.placementsOfGoods.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        try {
            for (Class<?> claz : ClassFinder.find("com.placementsOfGoods.model")) {
                    configuration.addAnnotatedClass(claz);
            }
        }catch (Exception e) {
                try {
                    configuration.addAnnotatedClass(Category.class);
                    configuration.addAnnotatedClass(CategoryLocation.class);
                    configuration.addAnnotatedClass(Customer.class);
                    configuration.addAnnotatedClass(Good.class);
                    configuration.addAnnotatedClass(Location.class);
                    configuration.addAnnotatedClass(Purchase.class);
                    configuration.addAnnotatedClass(Shop.class);
                    configuration.addAnnotatedClass(User.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        }
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

}
