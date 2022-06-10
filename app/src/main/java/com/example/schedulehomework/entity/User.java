package com.example.schedulehomework.entity;

public class User {
    private String name;
    private int ID;
    private String picture;

    public User(String name, int ID, String picture) {
        this.name = name;
        this.ID = ID;
        this.picture = picture;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", picture='" + picture + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
