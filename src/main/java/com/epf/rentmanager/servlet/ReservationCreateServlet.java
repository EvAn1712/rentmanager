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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.epf.rentmanager.utils.IOUtils.print;
@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ReservationService reservationService;
    private ClientService clientService;
    private VehicleService vehicleService;

    public void init() throws ServletException {
        this.reservationService = ReservationService.getInstance();
        this.clientService = ClientService.getInstance();
        this.vehicleService = VehicleService.getInstance();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Reservation reservation = null;
        List<Client> clients = null;
        try {
            clients = clientService.findAll();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("clients", clients);

        List<Vehicle> vehicles = null;
        try {
            vehicles = vehicleService.findAll();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("cars", vehicles);

        request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);

    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            int vehicle_id = Integer.parseInt(request.getParameter("car"));
            int client_id = Integer.parseInt(request.getParameter("client"));
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate debut = LocalDate.parse(request.getParameter("begin"), dateFormatter);
            LocalDate fin = LocalDate.parse(request.getParameter("end"), dateFormatter);

            Reservation newReservation = new Reservation(client_id, vehicle_id , debut, fin);

            reservationService.create(newReservation);

            response.sendRedirect(request.getContextPath() + "/rents");
        } catch (NumberFormatException | ServiceException e) {

            e.printStackTrace();
        }
    }
}
