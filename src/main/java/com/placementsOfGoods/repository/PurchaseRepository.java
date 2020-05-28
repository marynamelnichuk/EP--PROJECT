package com.placementsOfGoods.repository;

import com.placementsOfGoods.model.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.placementsOfGoods.util.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.List;

public class PurchaseRepository {

    private static Session session = HibernateUtil.getSession();
    private static CriteriaBuilder cb = session.getCriteriaBuilder();

    public static List<Purchase> getPurchases() {
        CriteriaQuery<Purchase> cr = cb.createQuery(Purchase.class);
        Root<Purchase> root = cr.from(Purchase.class);
        cr.select(root);
        Query<Purchase> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static List<Purchase> getPurchaseShop(Integer shopId) {
        CriteriaQuery<Purchase> cr = cb.createQuery(Purchase.class);
        Root<Purchase> root = cr.from(Purchase.class);
        cr.where(cb.equal(root.join("shop").get("id"), shopId));
        cr.groupBy(root.get("purchaseId"));
        cr.select(root);
        Query<Purchase> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static Purchase getPurchase(String purchaseId) {
        CriteriaQuery<Purchase> cr = cb.createQuery(Purchase.class);
        Root<Purchase> root = cr.from(Purchase.class);
        cr.where(cb.equal(root.get("id"), purchaseId));
        cr.select(root);
        Query<Purchase> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static void savePurchase(Purchase purchase) {
        Transaction transaction = session.beginTransaction();
        session.persist(purchase);
        transaction.commit();
    }

    public static void deletePurchase(String purchaseId) {
        CriteriaDelete<Purchase> criteriaDelete = cb.createCriteriaDelete(Purchase.class);
        Root<Purchase> root = criteriaDelete.from(Purchase.class);
        criteriaDelete.where(cb.equal(root.get("id"), purchaseId));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public static void updatePurchase(Integer id, Purchase newPurchase) {
        CriteriaUpdate<Purchase> criteriaUpdate = cb.createCriteriaUpdate(Purchase.class);
        Root<Purchase> root = criteriaUpdate.from(Purchase.class);
        criteriaUpdate.set("customer", newPurchase.getCustomer());
        criteriaUpdate.set("shop", newPurchase.getShop());
        criteriaUpdate.set("location", newPurchase.getLocation());
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

}
