package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.DaoException;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}
	
	
	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		try {
			if (client.getNom().isEmpty() || client.getPrenom().isEmpty()) {
				throw new ServiceException("Le nom ou le prénom du client ne peut pas être vide.", null);
			}
			client.setNom(client.getNom().toUpperCase());
			return clientDao.create(client);
		} catch (DaoException e) {
			throw new ServiceException("Erreur lors de la création du client.", e);
		}
    }

	public Client findById(long id) throws ServiceException {
		// TODO: récupérer un client par son id
		try{
			return  clientDao.findById(id);
		} catch (DaoException e) {
            throw new ServiceException("Erreur lors de la recherche par ID",e);
        }
    }

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		}catch (DaoException e){
			throw new ServiceException("Erreur lors de la recherche ",e);
		}
	}

	public long delete(Client client) throws ServiceException {
		try{
			return clientDao.delete(client);
		}catch (DaoException e){
			throw new ServiceException("Erreur lors de la supression ",e);
		}
	}
	
}
