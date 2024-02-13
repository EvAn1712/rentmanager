package com.epf.rentmanager.Model;

import sun.util.resources.LocaleData;

import java.time.LocalDate;

public class Client {
private int ID;
private String nom;
private String prenom;
private String email;
private LocalDate naissance;

    public Client(int ID, String nom, String prenom, String email, LocalDate naissance) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", naissance=" + naissance +
                '}';
    }

    public int getID() {
        return ID;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }
}
