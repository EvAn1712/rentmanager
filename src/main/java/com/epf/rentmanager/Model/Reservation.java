package com.epf.rentmanager.Model;


import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    private int ID;
    private int client_id;
    private int vehicle_id;
    private LocalDate debut;
    private LocalDate fin;

    public Reservation(int ID, int client_id, int vehicle_id, LocalDate debut, LocalDate fin) {
        this.ID = ID;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation(int client_id, int vehicle_id, LocalDate debut, LocalDate fin) {
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ID=" + ID +
                ", client_id=" + client_id +
                ", vehicle_id=" + vehicle_id +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }

    public int getID() {
        return ID;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public LocalDate getFin() {
        return fin;
    }
}
