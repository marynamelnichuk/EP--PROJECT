package com.kursova.repository;

import com.kursova.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.kursova.util.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.List;

public class UserRepository {

    private static Session session = HibernateUtil.getSession();
    private static CriteriaBuilder cb = session.getCriteriaBuilder();

    public static List<User> getUsers() {
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);
        Query<User> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static User getUser(Integer userId) {
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.where(cb.equal(root.get("id"), userId));
        cr.select(root);
        Query<User> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static List<User> getLoginedUser(String email, String password) {
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.where(cb.equal(root.get("email"), email));
        cr.where(cb.equal(root.get("password"), password));
        cr.select(root);
        Query<User> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static void saveUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public static void deleteUser(String userId) {
        CriteriaDelete<User> criteriaDelete = cb.createCriteriaDelete(User.class);
        Root<User> root = criteriaDelete.from(User.class);
        criteriaDelete.where(cb.equal(root.get("id"), userId));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public static void updateUser(Integer id, User newUser) {
        CriteriaUpdate<User> criteriaUpdate = cb.createCriteriaUpdate(User.class);
        Root<User> root = criteriaUpdate.from(User.class);
        criteriaUpdate.set("name", newUser.getName());
        criteriaUpdate.set("surname", newUser.getSurname());
        criteriaUpdate.set("email", newUser.getEmail());
        criteriaUpdate.set("password", newUser.getPassword());
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

}
