package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Model.Reservation;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rents/delete")
public class ReservationDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ReservationService reservationService;
    private Reservation reservation;

    public void init() {
        this.reservationService = ReservationService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));

            reservation= new Reservation();
            reservation.setID((int) id);

            reservationService.delete(reservation);

            response.sendRedirect(request.getContextPath() + "/rents");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
