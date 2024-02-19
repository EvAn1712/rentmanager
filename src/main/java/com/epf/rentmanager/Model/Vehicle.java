package com.epf.rentmanager.Model;

public class Vehicle {
    private String constructeur;
    private int nb_place;
    private int ID;

    public Vehicle(int ID,String constructeur, int nb_place) {
        this.constructeur = constructeur;
        this.nb_place = nb_place;
        this.ID = ID;
    }

    public Vehicle(String constructeur, int nb_place) {
        this.constructeur = constructeur;
        this.nb_place = nb_place;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "constructeur='" + constructeur + '\'' +
                ", nb_place=" + nb_place +
                ", ID=" + ID +
                '}';
    }

    public String getConstructeur() {
        return constructeur;
    }

    public int getNb_place() {
        return nb_place;
    }

    public int getID() {
        return ID;
    }
}
