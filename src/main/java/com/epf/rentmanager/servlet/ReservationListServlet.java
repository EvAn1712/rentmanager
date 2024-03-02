package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.Model.Reservation;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rents")
public class ReservationListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ReservationService reservationService;
    private VehicleService vehicleService;
    private ClientService clientService;

    public void init() throws ServletException {
        this.reservationService = ReservationService.getInstance();
        this.vehicleService = VehicleService.getInstance();
        this.clientService = ClientService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Reservation> reservations = null;
        try {
            reservations = reservationService.findAll();
            for (Reservation reservation : reservations) {
                Vehicle vehicle = vehicleService.findById(reservation.getVehicle_id());
                Client client = clientService.findById(reservation.getClient_id());
                if (vehicle != null && client!=null) {
                    reservation.setVehicleName(vehicle.getConstructeur(), vehicle.getModele());
                    reservation.setClientName(client.getNom(), client.getPrenom());
                }
            }
            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
