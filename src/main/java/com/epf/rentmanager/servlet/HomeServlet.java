package com.epf.rentmanager.servlet;

import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VehicleService vehicleService;

	public void init() throws ServletException {
		this.vehicleService = VehicleService.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        int totalVehicles = 0;
        try {
            totalVehicles = vehicleService.count();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("totalVehicles", totalVehicles);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
