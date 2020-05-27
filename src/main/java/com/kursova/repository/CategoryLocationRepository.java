package com.kursova.repository;

import com.kursova.model.CategoryLocation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.kursova.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;

public class CategoryLocationRepository {

    private static Session session = HibernateUtil.getSession();
    private static CriteriaBuilder cb = session.getCriteriaBuilder();

    public static void saveCategoryLocation(CategoryLocation categoryLocation) {
        Transaction transaction = session.beginTransaction();
        session.save(categoryLocation);
        transaction.commit();
    }

}
