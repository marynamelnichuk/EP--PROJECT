package com.kursova.util;

import com.kursova.model.CategoryLocation;
import com.kursova.model.Shop;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.kursova.repository.CategoryRepository;
import com.kursova.repository.LocationRepository;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocationGenerationEngine {

    private static Session session = HibernateUtil.getSession();

    private static class CategoryLocationDTO {
        Integer locationId;
        Integer categoryId;
        Integer purchaseId;
        Integer countOfPurchase;

        @Override
        public String toString() {
            return "CategoryLocationDTO{" +
                    "locationId=" + locationId +
                    ", categoryId=" + categoryId +
                    ", purchaseId=" + purchaseId +
                    ", countOfPurchase=" + countOfPurchase +
                    "} \n";
        }
    }


    public static List<CategoryLocation> generateLocations(Shop shop){
       // List<Category>
        Map<Integer,List<CategoryLocationDTO>> listOfCategoryLocationDTOS = new HashMap<>();
        Integer shopId = shop.getId();
        TypedQuery<Long> queryCountOfShop = session.createQuery(
                "SELECT COUNT(s) FROM Shop s"
                , Long.class);
        int shopCount = (int)(long)queryCountOfShop.getSingleResult();
        List<CategoryLocationDTO> currentShopDtos = new ArrayList<>();
        for(int i = 1; i <= shopCount; i++) {
            Transaction tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT purchase.location_id, categories_locations.category_id, purchase.purchase_id, COUNT(purchase_id) AS count FROM placement_of_goods.purchase\n" +
                    "RIGHT JOIN placement_of_goods.categories_locations \n" +
                    "ON purchase.location_id = categories_locations.location_id\n" +
                    "WHERE shop_id = " + i + "\n" +
                    "GROUP BY category_id;");
            List<Object[]> rows = query.list();
            tx.commit();
            List<CategoryLocationDTO> categoryLocationDTOS = new CopyOnWriteArrayList<>();
            for (Object[] row : rows) {
                CategoryLocationDTO categoryLocationDTO = new CategoryLocationDTO();
                categoryLocationDTO.locationId = Integer.parseInt(row[0].toString());
                categoryLocationDTO.categoryId = Integer.parseInt(row[1].toString());
                categoryLocationDTO.purchaseId = Integer.parseInt(row[2].toString());
                categoryLocationDTO.countOfPurchase = Integer.parseInt(row[3].toString());
                categoryLocationDTOS.add(categoryLocationDTO);
            }
            if(i == shopId) {
                currentShopDtos = categoryLocationDTOS;
            }else {
            listOfCategoryLocationDTOS.put(i, categoryLocationDTOS);
            }
        }


        List<Integer> categoriesIds = new ArrayList<>();
        for(CategoryLocationDTO categoryLocationDTO : currentShopDtos){
            categoriesIds.add(categoryLocationDTO.categoryId);
        }

        for (Map.Entry<Integer, List<CategoryLocationDTO>> entry : listOfCategoryLocationDTOS.entrySet()) {
            for(CategoryLocationDTO curentCategoryLocationDTO : currentShopDtos) {
                for (CategoryLocationDTO categoryLocationDTO : entry.getValue()) {
                    if (categoryLocationDTO.categoryId.equals(curentCategoryLocationDTO.categoryId)) {
                        if (curentCategoryLocationDTO.countOfPurchase < categoryLocationDTO.countOfPurchase) {
                            curentCategoryLocationDTO.locationId = categoryLocationDTO.locationId;
                            curentCategoryLocationDTO.countOfPurchase = categoryLocationDTO.countOfPurchase;
                        }
                    }
                }
            }
        }

        TypedQuery<Integer> query = session.createQuery(
                "SELECT cl.globalLocationId FROM CategoryLocation cl order by cl.globalLocationId desc"
                , Integer.class);
        int locationLastId = query.getResultList().get(0);

        List<CategoryLocation> categoryLocations = new ArrayList<>();
        for(CategoryLocationDTO categoryLocationDTO : currentShopDtos) {
            CategoryLocation categoryLocation = new CategoryLocation();
            categoryLocation.setCategory(CategoryRepository.getCategory(categoryLocationDTO.categoryId));
            categoryLocation.setLocation(LocationRepository.getLocation(categoryLocationDTO.locationId));
            categoryLocation.setGlobalLocationId(locationLastId + 1);
            categoryLocation.setDate(LocalDateTime.now());
            categoryLocations.add(categoryLocation);
        }
        return categoryLocations;
    }


}
