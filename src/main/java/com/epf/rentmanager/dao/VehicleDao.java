package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try (Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			 PreparedStatement ps = connexion.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, vehicle.getConstructeur());
			ps.setString(2, vehicle.getModele());
			ps.setInt(3, vehicle.getNb_place());
			ps.setInt(4, vehicle.getID());

			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new DaoException("Creating vehicle failed, no rows affected.", null);
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				} else {
					throw new DaoException("Creating vehicle failed, no ID obtained.", null);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Error", e);
		}
	}

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			PreparedStatement ps = connexion.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setInt(1, vehicle.getID());
			ps.execute();
		} catch (SQLException e) {
			throw new DaoException("Error ", e);
		}
		return vehicle.getID();
	}

	public Vehicle findById(long id) throws DaoException {
		Vehicle vehicle = null;
		try (Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			 PreparedStatement ps = connexion.prepareStatement(FIND_VEHICLE_QUERY)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					int ID = rs.getInt("id");
					String constructeur = rs.getString("constructeur");
					String modele = rs.getString("modele");
					int nb_place = rs.getInt("nb_place");
					vehicle = new Vehicle (ID, constructeur, modele, nb_place);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Error finding client by ID.", e);
		}
		return vehicle;
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<>();
		try (Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			 PreparedStatement ps = connexion.prepareStatement(FIND_VEHICLES_QUERY);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				int ID = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				String modele = rs.getString("modele");
				int nb_place = rs.getInt("nb_place");
				Vehicle vehicle = new Vehicle (ID, constructeur, modele, nb_place);
				vehicles.add(vehicle);
			}
		} catch (SQLException e) {
			throw new DaoException("Error finding all clients.", e);
		}
		return vehicles;
	}
}
