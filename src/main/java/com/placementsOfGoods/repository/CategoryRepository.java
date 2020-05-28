package com.placementsOfGoods.repository;

import com.placementsOfGoods.model.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.placementsOfGoods.util.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.List;

public class CategoryRepository {

    private static Session session = HibernateUtil.getSession();
    private static CriteriaBuilder cb = session.getCriteriaBuilder();

    public static List<Category> getCategories() {
        CriteriaQuery<Category> cr = cb.createQuery(Category.class);
        Root<Category> root = cr.from(Category.class);
        cr.select(root);
        Query<Category> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static Category getCategory(Integer categoryId) {
        CriteriaQuery<Category> cr = cb.createQuery(Category.class);
        Root<Category> root = cr.from(Category.class);
        cr.where(cb.equal(root.get("id"), categoryId));
        cr.select(root);
        Query<Category> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static Category getCategoryByName(String categoryName){
        CriteriaQuery<Category> cr = cb.createQuery(Category.class);
        Root<Category> root = cr.from(Category.class);
        cr.where(cb.equal(root.get("name"), categoryName));
        cr.select(root);
        Query<Category> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static List<Category> getCategoriesShop(Integer shopId) {
        CriteriaQuery<Category> cr = cb.createQuery(Category.class);
        Root<Category> root = cr.from(Category.class);
        cr.where(cb.equal(root.join("shops").get("id"), shopId));
        cr.select(root);
        Query<Category> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static void saveCategory(Category category) {
        Transaction transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
    }

    public static void deleteCategory(String categoryName) {
        CriteriaDelete<Category> criteriaDelete = cb.createCriteriaDelete(Category.class);
        Root<Category> root = criteriaDelete.from(Category.class);
        criteriaDelete.where(cb.equal(root.get("name"), categoryName));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public static void updateCategory(Integer id, String newCategoryName) {
        CriteriaUpdate<Category> criteriaUpdate = cb.createCriteriaUpdate(Category.class);
        Root<Category> root = criteriaUpdate.from(Category.class);
        criteriaUpdate.set("name", newCategoryName);
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

}
