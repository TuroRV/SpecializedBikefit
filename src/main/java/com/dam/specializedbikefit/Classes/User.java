package com.dam.specializedbikefit.Classes;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String user_name;
    private String user_surname;
    private String user_email;
    private String user_password;
    private float user_height;
    private float user_torsolength;
    private float user_armlength;
    private float user_inseamlength;
    @ManyToMany(mappedBy = "users")
    private Set<Bicycle> bicycles;

    public User(){}

    public User (String user_name, String user_surname, String user_email, String user_password, float user_height, float user_torsolength, float user_armlength, float user_inseamlength) {
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_height = user_height;
        this.user_torsolength = user_torsolength;
        this.user_armlength = user_armlength;
        this.user_inseamlength = user_inseamlength;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public float getUser_height() {
        return user_height;
    }

    public void setUser_height(float user_height) {
        this.user_height = user_height;
    }

    public float getUser_torsolength() {
        return user_torsolength;
    }

    public void setUser_torsolength(float user_torsolength) {
        this.user_torsolength = user_torsolength;
    }

    public float getUser_armlength() {
        return user_armlength;
    }

    public void setUser_armlength(float user_armlength) {
        this.user_armlength = user_armlength;
    }

    public float getUser_inseamlength() {
        return user_inseamlength;
    }

    public void setUser_inseamlength(float user_inseamlength) {
        this.user_inseamlength = user_inseamlength;
    }

    public Set<Bicycle> getBicycles() {
        return bicycles;
    }

    public void setBicycles(Set<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_surname='" + user_surname + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_height=" + user_height +
                ", user_torsolength=" + user_torsolength +
                ", user_armlength=" + user_armlength +
                ", user_inseamlength=" + user_inseamlength +
                '}';
    }
}
