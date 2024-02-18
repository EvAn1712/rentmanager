package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.Model.Reservation;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";


	public long create(Reservation reservation) throws DaoException {
		try (Connection connection = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			 PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, reservation.getClient_id());
			ps.setInt(2, reservation.getVehicle_id());
			ps.setDate(3, java.sql.Date.valueOf(reservation.getDebut()));
			ps.setDate(4, java.sql.Date.valueOf(reservation.getFin()));

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new DaoException("Creating resavtion failed, no rows affected.", null);
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				} else {
					throw new DaoException("Creating reservation failed, no ID obtained.", null);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Error", e);
		}
	}

	public long delete(Reservation reservation) throws DaoException {
		try {
			Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			PreparedStatement ps = connexion.prepareStatement(DELETE_RESERVATION_QUERY);
			ps.setInt(1, reservation.getID());
			ps.execute();
		} catch (SQLException e) {
			throw new DaoException("Error ", e);
		}
		return reservation.getID();
	}

	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY)) {
			ps.setLong(1, clientId);
			try (ResultSet resultSet = ps.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					int vehicle_id = resultSet.getInt("vehicle_id");
					LocalDate debut = resultSet.getDate("debut").toLocalDate();
					LocalDate fin = resultSet.getDate("fin").toLocalDate();
					Reservation reservation = new Reservation(id, (int)clientId, vehicle_id, debut, fin);
					reservations.add(reservation);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Error ", e);
		}
		return reservations;
	}

	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			 PreparedStatement ps = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY)) {
			ps.setLong(1, vehicleId);
			try (ResultSet resultSet = ps.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					int clientId = resultSet.getInt("client_id");
					LocalDate debut = resultSet.getDate("debut").toLocalDate();
					LocalDate fin = resultSet.getDate("fin").toLocalDate();
					Reservation reservation = new Reservation(id, clientId, (int) vehicleId, debut, fin);
					reservations.add(reservation);
				}
			}
		}
		catch (SQLException e) {
			throw new DaoException("Error ", e);
		}return reservations;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			 PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_QUERY);
			 ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int clientId = resultSet.getInt("client_id");
				int vehicleId = resultSet.getInt("vehicle_id");
				LocalDate debut = resultSet.getDate("debut").toLocalDate();
				LocalDate fin = resultSet.getDate("fin").toLocalDate();
				Reservation reser = new Reservation(id, clientId, vehicleId, debut, fin);
				reservations.add(reser);
			}
		} catch (SQLException e) {
			throw new DaoException("Error ", e);
		}
		return reservations;
	}
}