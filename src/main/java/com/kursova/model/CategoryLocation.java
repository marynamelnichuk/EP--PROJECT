package com.kursova.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories_locations")
public class CategoryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "global_location_id")
    private Integer globalLocationId;

    @Column(name="date")
    protected LocalDateTime date;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Location location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGlobalLocationId() {
        return globalLocationId;
    }

    public void setGlobalLocationId(Integer globalLocationId) {
        this.globalLocationId = globalLocationId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
