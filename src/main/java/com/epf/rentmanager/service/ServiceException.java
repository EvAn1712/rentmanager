package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.DaoException;

public class ServiceException extends Exception{

    public ServiceException(String s, DaoException e) {
    }
}
