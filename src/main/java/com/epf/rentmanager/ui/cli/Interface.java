package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;
import com.epf.rentmanager.dao.ClientDao;

import java.time.LocalDate;
import java.util.List;

public class Interface {

    private static final ClientService clientService = ClientService.getInstance();
    private static final VehicleService vehicleService = VehicleService.getInstance();

    public static void main(String[] args) {
        displayMainMenu();
    }

    private static void displayMainMenu() {
        boolean running = true;
        while (running) {
            IOUtils.print("\n### Menu principal ###");
            IOUtils.print("1. Créer un client");
            IOUtils.print("2. Lister tous les clients");
            IOUtils.print("3. Créer un véhicule");
            IOUtils.print("4. Lister tous les véhicules");
            IOUtils.print("5. Supprimer un client (bonus)");
            IOUtils.print("6. Supprimer un véhicule (bonus)");
            IOUtils.print("7. Quitter");

            int choice = IOUtils.readInt("Choisissez une option : ");

            switch (choice) {
                case 1:
                    createClient();
                    break;
                case 2:
                    listClients();
                    break;
                case 3:
                    createVehicle();
                    break;
                case 4:
                    listVehicles();
                    break;
                case 5:
                    deleteClient();
                    break;
                case 6:
                    deleteVehicle();
                    break;
                case 7:
                    running = false;
                    IOUtils.print("Au revoir !");
                    break;
                default:
                    IOUtils.print("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void createClient() {
        IOUtils.print("\n### Création d'un client ###");
        String nom = IOUtils.readString("Nom : ",true);
        String prenom = IOUtils.readString("Prénom : ", true);
        String email = IOUtils.readString("Email : ", true);
        LocalDate naissance = IOUtils.readDate("Date de naissance (format dd/MM/yyyy) : ", true);

        try {
            long clientId = clientService.create(new Client(nom, prenom, email, naissance));
            IOUtils.print("Client créé avec succès ! (ID : " + clientId + ")");
        } catch (ServiceException e) {
            IOUtils.print("Erreur lors de la création du client : " + e.getMessage());
        }
    }

    private static void listClients() {
        IOUtils.print("\n### Liste des clients ###");
        try {
            List<Client> clients = clientService.findAll();
            if (!clients.isEmpty()) {
                for (Client client : clients) {
                    IOUtils.print(client.toString());
                }
            } else {
                IOUtils.print("Aucun client trouvé.");
            }
        } catch (ServiceException e) {
            IOUtils.print("Erreur lors de la récupération des clients : " + e.getMessage());
        }
    }

    private static void createVehicle() {
        IOUtils.print("\n### Création d'un véhicule ###");
        String constructeur = IOUtils.readString("Constructeur : ", true);
        int nbPlaces = IOUtils.readInt("Nombre de places : ");

        try {
            long vehicleId = vehicleService.create(new Vehicle(constructeur, nbPlaces));
            IOUtils.print("Véhicule créé avec succès ! (ID : " + vehicleId + ")");
        } catch (ServiceException e) {
            IOUtils.print("Erreur lors de la création du véhicule : " + e.getMessage());
        }
    }

    private static void listVehicles() {
        IOUtils.print("\n### Liste des véhicules ###");
        try {
            List<Vehicle> vehicles = vehicleService.findAll();
            if (!vehicles.isEmpty()) {
                for (Vehicle vehicle : vehicles) {
                    IOUtils.print(vehicle.toString());
                }
            } else {
                IOUtils.print("Aucun véhicule trouvé.");
            }
        } catch (ServiceException e) {
            IOUtils.print("Erreur lors de la récupération des véhicules : " + e.getMessage());
        }
    }


    private static void deleteClient() {
        IOUtils.print("\n### Suppression d'un client ###");
        int clientId = IOUtils.readInt("ID du client à supprimer : ");

        try {
            Client client = clientService.findById(clientId);
            if (client != null) {
                clientService.delete(client);
                IOUtils.print("Client supprimé avec succès !");
            } else {
                IOUtils.print("Aucun client trouvé avec l'ID spécifié.");
            }
        } catch (ServiceException e) {
            IOUtils.print("Erreur lors de la suppression du client : " + e.getMessage());
        }
    }

    private static void deleteVehicle() {
        IOUtils.print("\n### Suppression d'un véhicule ###");
        int vehicleId = IOUtils.readInt("ID du véhicule à supprimer : ");

        try {
            Vehicle vehicle = vehicleService.findById(vehicleId);
            if (vehicle != null) {
                vehicleService.delete(vehicle);
                IOUtils.print("Véhicule supprimé avec succès !");
            } else {
                IOUtils.print("Aucun véhicule trouvé avec l'ID spécifié.");
            }
        } catch (ServiceException e) {
            IOUtils.print("Erreur lors de la suppression du véhicule : " + e.getMessage());
        }
    }
}