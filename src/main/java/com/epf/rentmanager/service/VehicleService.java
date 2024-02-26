package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: créer un véhicule
		if (vehicle.getConstructeur().isEmpty() || vehicle.getNb_place() <= 1) {
			throw new ServiceException("Le constructeur ne peut pas être vide et le nombre de places doit être supérieur à 1.", null);
		}
		try {
			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		// TODO: récupérer un véhicule par son id
		try{
			return  vehicleDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(),e);
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return vehicleDao.findAll();
		}catch (DaoException e){
			throw new ServiceException(e.getMessage(),e);
		}
	}

	public long delete(Vehicle vehicle) throws ServiceException {
		try{
			return vehicleDao.delete(vehicle);
		}catch (DaoException e){
			throw new ServiceException(e.getMessage(),e);
		}
	}
	
}
