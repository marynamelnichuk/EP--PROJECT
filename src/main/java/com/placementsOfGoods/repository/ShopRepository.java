package com.placementsOfGoods.repository;

import com.placementsOfGoods.model.Shop;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.placementsOfGoods.util.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.List;

public class ShopRepository {

    private static Session session = HibernateUtil.getSession();
    private static CriteriaBuilder cb = session.getCriteriaBuilder();

    public static List<Shop> getShops() {
        CriteriaQuery<Shop> cr = cb.createQuery(Shop.class);
        Root<Shop> root = cr.from(Shop.class);
        cr.select(root);
        Query<Shop> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static List<Shop> getUsersShop(Integer userId) {
        CriteriaQuery<Shop> cr = cb.createQuery(Shop.class);
        Root<Shop> root = cr.from(Shop.class);
        cr.where(cb.equal(root.join("user").get("id"), userId));
        cr.select(root);
        Query<Shop> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static Shop getShop(Integer shopId) {
        CriteriaQuery<Shop> cr = cb.createQuery(Shop.class);
        Root<Shop> root = cr.from(Shop.class);
        cr.where(cb.equal(root.get("id"), shopId));
        cr.select(root);
        Query<Shop> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static void saveShop(Shop shop) {
        Transaction transaction = session.beginTransaction();
        session.save(shop);
        transaction.commit();
    }

    public static void deleteShop(String shopId) {
        CriteriaDelete<Shop> criteriaDelete = cb.createCriteriaDelete(Shop.class);
        Root<Shop> root = criteriaDelete.from(Shop.class);
        criteriaDelete.where(cb.equal(root.get("id"), shopId));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public static void updateShop(Integer id, Shop newShop) {
        CriteriaUpdate<Shop> criteriaUpdate = cb.createCriteriaUpdate(Shop.class);
        Root<Shop> root = criteriaUpdate.from(Shop.class);
        criteriaUpdate.set("name", newShop.getName());
        criteriaUpdate.set("country", newShop.getCountry());
        criteriaUpdate.set("city", newShop.getCity());
        criteriaUpdate.set("user", newShop.getUser());
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

}
