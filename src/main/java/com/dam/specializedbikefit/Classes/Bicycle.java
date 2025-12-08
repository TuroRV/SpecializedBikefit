package com.dam.specializedbikefit.Classes;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bicycles")
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bike_id;
    private String bike_brand;
    private String bike_model;
    private String bike_size;
    private int bike_year;
    private float bike_reach;
    private float bike_stack;
    private float bike_seattubelength;
    private float bike_toptubelength;
    private boolean bike_isEbike;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_bicycle",
                joinColumns = @JoinColumn(name = "bike_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Bicycle() {}

    public Bicycle(String bike_brand, String bike_model, String bike_size, int bike_year, float bike_reach, float bike_stack, float bike_seattubelength, float bike_toptubelength, boolean bike_isEbike) {
        this.bike_brand = bike_brand;
        this.bike_model = bike_model;
        this.bike_size = bike_size;
        this.bike_year = bike_year;
        this.bike_reach = bike_reach;
        this.bike_stack = bike_stack;
        this.bike_seattubelength = bike_seattubelength;
        this.bike_toptubelength = bike_toptubelength;
        this.bike_isEbike = bike_isEbike;
    }

    public int getBike_id() {
        return bike_id;
    }

    public String getBike_brand() {
        return bike_brand;
    }

    public void setBike_brand(String bike_brand) {
        this.bike_brand = bike_brand;
    }

    public String getBike_model() {
        return bike_model;
    }

    public void setBike_model(String bike_model) {
        this.bike_model = bike_model;
    }

    public String getBike_size() {
        return bike_size;
    }

    public void setBike_size(String bike_size) {
        this.bike_size = bike_size;
    }

    public int getBike_year() {
        return bike_year;
    }

    public void setBike_year(int bike_year) {
        this.bike_year = bike_year;
    }

    public float getBike_reach() {
        return bike_reach;
    }

    public void setBike_reach(float bike_reach) {
        this.bike_reach = bike_reach;
    }

    public float getBike_stack() {
        return bike_stack;
    }

    public void setBike_stack(float bike_stack) {
        this.bike_stack = bike_stack;
    }

    public float getBike_seattubelength() {
        return bike_seattubelength;
    }

    public void setBike_seattubelength(float bike_seattubelength) {
        this.bike_seattubelength = bike_seattubelength;
    }

    public float getBike_toptubelength() {
        return bike_toptubelength;
    }

    public void setBike_toptubelength(float bike_toptubelength) {
        this.bike_toptubelength = bike_toptubelength;
    }

    public boolean isBike_isEbike() {
        return bike_isEbike;
    }

    public void setBike_isEbike(boolean bike_isEbike) {
        this.bike_isEbike = bike_isEbike;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return bike_brand + " " + bike_model + " " + bike_size + " " + bike_year;
    }
}
