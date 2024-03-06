package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/delete")
public class ClientDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientService clientService;
    private Client client;
    public void init() {
        this.clientService = ClientService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));

            client= new Client();
            client.setID((int) id);

            clientService.delete(client);

            response.sendRedirect(request.getContextPath() + "/users");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
