package com.kursova.repository;

import com.kursova.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.kursova.util.HibernateUtil;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class GoodRepository {

    private static Session session = HibernateUtil.getSession();
    private static CriteriaBuilder cb = session.getCriteriaBuilder();

    public static List<Good> getGoods() {
        CriteriaQuery<Good> cr = cb.createQuery(Good.class);
        Root<Good> root = cr.from(Good.class);
        cr.select(root);
        Query<Good> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static List<Good> getGoodsByPurchaseId(Integer purchaseId) {
        CriteriaQuery<Purchase> cr = cb.createQuery(Purchase.class);
        Root<Purchase> root = cr.from(Purchase.class);
        cr.where(cb.equal(root.get("purchaseId"), purchaseId));
        cr.select(root);
        Query<Purchase> query = session.createQuery(cr);
        List<Purchase> purchases = query.getResultList();
        List<Good> goods = new ArrayList<Good>();
        for(Purchase purchase : purchases) {
            goods.add(purchase.getGood());
        }
        return goods;
    }

    public static List<Good> getGoodsShop(Integer shopId){
        Shop shop = ShopRepository.getShop(shopId);
        List<Category> categories = shop.getCategories();
        List<Good> goodsToReturn = new ArrayList<>();
        for(Category category : categories) {
            goodsToReturn.addAll(category.getGoods());
        }
        return goodsToReturn;
    }

    public static Good getGood(Integer goodId) {
        CriteriaQuery<Good> cr = cb.createQuery(Good.class);
        Root<Good> root = cr.from(Good.class);
        cr.where(cb.equal(root.get("id"), goodId));
        cr.select(root);
        Query<Good> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static void saveGood(Good good) {
        Transaction transaction = session.beginTransaction();
        session.save(good);
        transaction.commit();
    }

    public static void deleteGood(String goodId) {
        CriteriaDelete<Good> criteriaDelete = cb.createCriteriaDelete(Good.class);
        Root<Good> root = criteriaDelete.from(Good.class);
        criteriaDelete.where(cb.equal(root.get("id"), goodId));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public static void updateGood(Integer id, Good newGood) {
        CriteriaUpdate<Good> criteriaUpdate = cb.createCriteriaUpdate(Good.class);
        Root<Good> root = criteriaUpdate.from(Good.class);
        criteriaUpdate.set("name", newGood.getName());
        criteriaUpdate.set("category", newGood.getCategory());
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

}
