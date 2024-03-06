package com.epf.rentmanager.service;

import com.epf.rentmanager.Model.Reservation;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;

import java.util.List;

public class ReservationService {


    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }


    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer une reservation

        try {
            return reservationDao.create(reservation);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Reservation> findResaByClientId(long id) throws ServiceException {
        // TODO: récupérer une reservation par son id client
        try{
            return  reservationDao.findResaByClientId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Reservation> findResaByVehicleId(long id) throws ServiceException {
        // TODO: récupérer une reservation par son id vehicule
        try{
            return  reservationDao.findResaByVehicleId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        // TODO: récupérer tous les clients
        try {
            return reservationDao.findAll();
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public long delete(Reservation reservation) throws ServiceException {
        try{
            return reservationDao.delete(reservation);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Reservation getReservationById(int reservationId) throws ServiceException {
       try {
           List<Reservation> reservations = reservationDao.findAll();

           for (Reservation reservation : reservations) {
               if (reservation.getID() == reservationId) {
                   return reservation;
               }
           }
       }catch (DaoException e){
           throw new ServiceException(e.getMessage(),e);
       }
        return null;
    }

    public int count() throws ServiceException{
        try{
            return reservationDao.count();
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }

}

