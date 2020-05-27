package com.kursova.repository;

import com.kursova.model.Customer;
import com.kursova.model.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.kursova.util.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private static Session session = HibernateUtil.getSession();
    private static CriteriaBuilder cb = session.getCriteriaBuilder();

    public static List<Customer> getCustomers() {
        CriteriaQuery<Customer> cr = cb.createQuery(Customer.class);
        Root<Customer> root = cr.from(Customer.class);
        cr.select(root);
        Query<Customer> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static List<Customer> getCustomerShop(Integer shopId) {
        CriteriaQuery<Purchase> cr = cb.createQuery(Purchase.class);
        Root<Purchase> root = cr.from(Purchase.class);
        cr.where(cb.equal(root.join("shop").get("id"), shopId));
        cr.select(root);
        Query<Purchase> query = session.createQuery(cr);
        List<Purchase> purchases = query.getResultList();
        List<Customer> customers = new ArrayList<Customer>();
        for(Purchase purchase : purchases) {
            if(!isCustomerAdded(customers, purchase.getCustomer().getId())) {
                customers.add(purchase.getCustomer());
            }
        }
        return customers;
    }

    private static boolean isCustomerAdded(List<Customer> customers, int  id) {
        for(Customer customer : customers) {
            if(customer.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static Customer getCustomer(Integer customerId) {
        CriteriaQuery<Customer> cr = cb.createQuery(Customer.class);
        Root<Customer> root = cr.from(Customer.class);
        cr.where(cb.equal(root.get("id"), customerId));
        cr.select(root);
        Query<Customer> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static void saveCustomer(Customer customer) {
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
    }

    public static void deleteCustomer(String customerId) {
        CriteriaDelete<Customer> criteriaDelete = cb.createCriteriaDelete(Customer.class);
        Root<Customer> root = criteriaDelete.from(Customer.class);
        criteriaDelete.where(cb.equal(root.get("id"), customerId));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public static void updateCustomer(Integer id, Customer newCustomer) {
        CriteriaUpdate<Customer> criteriaUpdate = cb.createCriteriaUpdate(Customer.class);
        Root<Customer> root = criteriaUpdate.from(Customer.class);
        criteriaUpdate.set("name", newCustomer.getName());
        criteriaUpdate.set("surname", newCustomer.getSurname());
        criteriaUpdate.set("date", newCustomer.getDate());
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

}
