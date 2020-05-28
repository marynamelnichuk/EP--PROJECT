package com.placementsOfGoods.repository;

import com.placementsOfGoods.model.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.placementsOfGoods.util.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.List;

public class LocationRepository {

    private static Session session = HibernateUtil.getSession();
    private static CriteriaBuilder cb = session.getCriteriaBuilder();

    public static List<Location> getLocations() {
        CriteriaQuery<Location> cr = cb.createQuery(Location.class);
        Root<Location> root = cr.from(Location.class);
        cr.select(root);
        Query<Location> query = session.createQuery(cr);
        return query.getResultList();
    }

    public static Location getLocation(Integer locationId) {
        CriteriaQuery<Location> cr = cb.createQuery(Location.class);
        Root<Location> root = cr.from(Location.class);
        cr.where(cb.equal(root.get("id"), locationId));
        cr.select(root);
        Query<Location> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static Location getLocationByGoodId(Integer categoryId) {
        CriteriaQuery<Location> cr = cb.createQuery(Location.class);
        Root<Location> root = cr.from(Location.class);
        cr.where(cb.equal(root.join("categoryLocations").get("id"), categoryId));
        cr.select(root);
        Query<Location> query = session.createQuery(cr);
        return query.getResultList().get(0);
    }

    public static void saveLocation(Location location) {
        Transaction transaction = session.beginTransaction();
        session.save(location);
        transaction.commit();
    }

    public static void deleteLocation(String locationId) {
        CriteriaDelete<Location> criteriaDelete = cb.createCriteriaDelete(Location.class);
        Root<Location> root = criteriaDelete.from(Location.class);
        criteriaDelete.where(cb.equal(root.get("id"), locationId));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public static void updateLocation(Integer id, Location newLocation) {
        CriteriaUpdate<Location> criteriaUpdate = cb.createCriteriaUpdate(Location.class);
        Root<Location> root = criteriaUpdate.from(Location.class);
        criteriaUpdate.set("name", newLocation.getName());
        criteriaUpdate.set("distance", newLocation.getDistance());
        criteriaUpdate.set("side", newLocation.getSide());
        criteriaUpdate.where(cb.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

}
