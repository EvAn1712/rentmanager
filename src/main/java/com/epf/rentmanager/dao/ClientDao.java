package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;

	public ClientDao(Connection connection) {
		this.connection = connection;
	}

	private Connection connection;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?), RETURN_GENERATED_KEYS;";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";

	public long create(Client client) throws DaoException {
		try (Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			 PreparedStatement ps = connexion.prepareStatement(CREATE_CLIENT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, java.sql.Date.valueOf(client.getNaissance()));

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new DaoException("La création du client a échoué, aucune ligne affectée.");
			}

			try (ResultSet resultSet = ps.getGeneratedKeys()) {
				if (resultSet.next()) {
					return resultSet.getLong(1);
				} else {
					throw new DaoException("La création du client a échoué, aucun identifiant récupéré.");
				}
			} catch (SQLException e) {
				throw new DaoException("Erreur lors de la fermeture des ressources", e);
			}
		} catch (SQLException e) {
			throw new DaoException("Erreur lors de la création du client", e);
		}
	}

	public long delete(Client client) throws DaoException {
		try {
			Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
			PreparedStatement ps = connexion.prepareStatement(DELETE_CLIENT_QUERY);
			ps.setInt(1, client.getID());
			ps.execute();
			ps.close();
			connexion.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return client.getID();
	}

	public Client findById(long id) throws DaoException {
		return new Client();
	}

	public List<Client> findAll() throws DaoException {
		return new ArrayList<Client>();
	}

}
