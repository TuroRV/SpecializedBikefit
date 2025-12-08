package com.dam.specializedbikefit.Classes;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bikefits")
public class Bikefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bikefit_id;
    private String bikefit_name;
    private LocalDate bikefit_date;
    private float bikefit_saddleHeight;
    private float bikefit_saddleToBar;
    private float bikefit_saddleSetBack;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bikefit_id")
    private Bicycle bicycle;

    public Bikefit (){}

    public Bikefit(String name, User user, Bicycle bicycle) {
        this.bikefit_name = name;
        this.user = user;
        this.bicycle = bicycle;
        this.bikefit_date = LocalDate.now();
    }

    public int getBikefit_id() {
        return bikefit_id;
    }

    public void setBikefit_id(int bikefit_id) {
        this.bikefit_id = bikefit_id;
    }

    public String getBikefit_name() {
        return bikefit_name;
    }

    public void setBikefit_name(String bikefit_name) {
        this.bikefit_name = bikefit_name;
    }

    public LocalDate getBikefit_date() {
        return bikefit_date;
    }

    public void setBikefit_date(LocalDate bikefit_date) {
        this.bikefit_date = bikefit_date;
    }

    public float getBikefit_saddleHeight() {
        return bikefit_saddleHeight;
    }

    public void setBikefit_saddleHeight(float bikefit_saddleHeight) {
        this.bikefit_saddleHeight = bikefit_saddleHeight;
    }

    public float getBikefit_saddleToBar() {
        return bikefit_saddleToBar;
    }

    public void setBikefit_saddleToBar(float bikefit_saddleToBar) {
        this.bikefit_saddleToBar = bikefit_saddleToBar;
    }

    public float getBikefit_saddleSetBack() {
        return bikefit_saddleSetBack;
    }

    public void setBikefit_saddleSetBack(float bikefit_saddleSetBack) {
        this.bikefit_saddleSetBack = bikefit_saddleSetBack;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    @Override
    public String toString() {
        return "Bikefit{" +
                "bikefit_id=" + bikefit_id +
                ", bikefit_name='" + bikefit_name + '\'' +
                ", bikefit_date=" + bikefit_date +
                ", bikefit_saddleHeight=" + bikefit_saddleHeight +
                ", bikefit_saddleToBar=" + bikefit_saddleToBar +
                ", bikefit_saddleSetBack=" + bikefit_saddleSetBack +
                ", user=" + user +
                ", bicycle=" + bicycle +
                '}';
    }
}
