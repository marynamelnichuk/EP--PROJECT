package com.kursova.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "distance")
    private Float distance;

    @Column(name = "side")
    private String side;

    @ManyToOne
    private Shop shop;

    @OneToMany(mappedBy = "location")
    private List<CategoryLocation> categoryLocations;

    @OneToMany(mappedBy = "location")
    private List<Purchase> purchases;

    public Location() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<CategoryLocation> getCategoryLocations() {
        return categoryLocations;
    }

    public void setCategoryLocations(List<CategoryLocation> categoryLocations) {
        this.categoryLocations = categoryLocations;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", side='" + side + '\'' +
                '}';
    }
}
