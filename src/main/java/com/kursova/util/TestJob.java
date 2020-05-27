package com.kursova.util;

import com.kursova.model.*;
import org.hibernate.Session;
import com.kursova.repository.*;
import com.kursova.ui.ShopForm;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Random;

public class TestJob {

    private static Random random = new Random();
    private static Long customerLastId;
    private static Integer purchaseLastId;
    private static Integer shopCount;
    private static Integer goodCount;
    private static Session session = HibernateUtil.getSession();

    public static void runCustomerEngine(){
        setCustomerLastId();
        setPurchaseLastId();
        setShopCount();
        setCountOfGoods();
        while(ShopForm.isPurchaseCalling) {
            try {
                Thread.sleep(20000);
                Customer customer =  new Customer();
                customer.setName("name_" + customerLastId);
                customer.setSurname("surname_" + customerLastId);
                customer.setDate(LocalDateTime.now());
                if(ShopForm.isCustomerCalling) {
                    CustomerRepository.saveCustomer(customer);
                }
                customerLastId++;
                for(int i = 0; i < 5; i++) {
                    if (ShopForm.isPurchaseCalling) {
                        Customer customerPurchase;
                        if (i == 0) {
                            customerPurchase = customer;
                        } else {
                            int customerId = new Random().nextInt((int) (long) (customerLastId)) + 1;
                            if (ShopForm.isPurchaseCalling) {
                                customerPurchase = CustomerRepository.getCustomer(customerId);
                            }else {
                                break;
                            }
                        }
                        int shopId = random.nextInt(shopCount) + 1;
                        Purchase purchase = new Purchase();
                        if (ShopForm.isPurchaseCalling) {
                            Shop shop = ShopRepository.getShop(shopId);
                            purchase.setPurchaseId(purchaseLastId + 1);
                            purchase.setCustomer(customerPurchase);
                            purchase.setShop(shop);
                        }
                        int countOfGoods = random.nextInt(5);
                        for (int j = 0; j < countOfGoods && ShopForm.isPurchaseCalling; j++) {
                            int goodId = random.nextInt(goodCount) + 1;
                            Good good = GoodRepository.getGood(goodId);
                            purchase.setGood(good);
                            Category category = good.getCategory();
                            Location location = LocationRepository.getLocationByGoodId(category.getId());
                            purchase.setLocation(location);
                            purchase.setDate(LocalDateTime.now());
                            if (ShopForm.isPurchaseCalling) {
                                PurchaseRepository.savePurchase(purchase);
                            }
                            Thread.sleep(500);
                        }
                        purchaseLastId++;
                    }else {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setCustomerLastId(){
        TypedQuery<Long> query = session.createQuery(
                "SELECT COUNT(c) FROM Customer c", Long.class);
        customerLastId = query.getSingleResult();
    }

    private static void setPurchaseLastId(){
        TypedQuery<Integer> query = session.createQuery(
                "SELECT p.purchaseId FROM Purchase p group by p.purchaseId order by p.purchaseId desc"
                , Integer.class);
        purchaseLastId = query.getResultList().get(0);
    }

    private static void setShopCount(){
        TypedQuery<Long> query = session.createQuery(
                "SELECT COUNT(s) FROM Shop s"
                , Long.class);
        shopCount = (int)(long)query.getSingleResult();
    }

    private static void setCountOfGoods(){
        TypedQuery<Long> query = session.createQuery(
                "SELECT COUNT(g) FROM Good g"
                , Long.class);
        goodCount = (int)(long)query.getSingleResult();
    }

}
