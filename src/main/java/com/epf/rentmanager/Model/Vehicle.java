package com.epf.rentmanager.Model;

public class Vehicle {
    private String constructeur;
    private String modele;
    private int nb_place;
    private int ID;

    public Vehicle(String constructeur, String modele, int nb_place, int ID) {
        this.constructeur = constructeur;
        this.modele = modele;
        this.nb_place = nb_place;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "constructeur='" + constructeur + '\'' +
                ", modele='" + modele + '\'' +
                ", nb_place=" + nb_place +
                ", ID=" + ID +
                '}';
    }
}