package com.epf.rentmanager.servlet;

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

@WebServlet("/cars")
public class VehicleListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService;

    public void init() throws ServletException {
        this.vehicleService = VehicleService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Vehicle> vehicles = null;
        try {
            vehicles = vehicleService.findAll();
            request.setAttribute("vehicles", vehicles);

            request.getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
     }

}
