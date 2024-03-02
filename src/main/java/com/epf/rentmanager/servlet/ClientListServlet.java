package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class ClientListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClientService clientService;

    public void init() throws ServletException {
        this.clientService = ClientService.getInstance();
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            List<Client> client = null;
            try {
                client = clientService.findAll();
                request.setAttribute("client", client);
                request.getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);

            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
}

