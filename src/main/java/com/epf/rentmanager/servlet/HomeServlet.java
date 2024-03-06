package com.epf.rentmanager.servlet;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
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
	private ReservationService reservationService;
	private ClientService clientService;

	public void init() throws ServletException {
		this.vehicleService = VehicleService.getInstance();
		this.reservationService = ReservationService.getInstance();
		this.clientService = ClientService.getInstance();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        int totalVehicles = 0;
		int totalClients = 0;
		int totalReservation = 0;
        try {
            totalVehicles = vehicleService.count();
			totalReservation = reservationService.count();
			totalClients = clientService.count();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("totalVehicles", totalVehicles);
		request.setAttribute("totalClients", totalClients);
		request.setAttribute("totalReservation", totalReservation);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
