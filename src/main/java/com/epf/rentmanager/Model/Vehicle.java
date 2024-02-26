package com.epf.rentmanager.Model;

public class Vehicle {
    private String constructeur;
    private String modele;
    private int nb_place;
    private int ID;

    public Vehicle(int ID, String constructeur, String modele, int nb_place) {
        this.constructeur = constructeur;
        this.modele = modele;
        this.nb_place = nb_place;
        this.ID = ID;
    }

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
                "constructeur=" + constructeur +
                ", modele=" + modele +
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

    public String getModele() {
        return modele;
    }
    public int getID() {
        return ID;
    }
}
