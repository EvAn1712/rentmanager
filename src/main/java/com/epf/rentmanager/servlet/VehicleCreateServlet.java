package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/create")
public class VehicleCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService;

    public void init() throws ServletException {
        this.vehicleService = VehicleService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Vehicle vehicles = null;
            request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);

    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String manufacturer = request.getParameter("manufacturer");
            String model = request.getParameter("modele");
            int seats = Integer.parseInt(request.getParameter("seats"));

            Vehicle newVehicle = new Vehicle(manufacturer, model, seats);

            vehicleService.create(newVehicle);
            response.sendRedirect(request.getContextPath() + "/cars");
        } catch (NumberFormatException | ServiceException e) {

            e.printStackTrace();
        }
    }
}
