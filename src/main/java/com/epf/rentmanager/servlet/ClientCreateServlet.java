
package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.epf.rentmanager.utils.IOUtils.print;

@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientService clientService;

    public void init() throws ServletException {
        this.clientService = ClientService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Client clients = null;
        request.getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);

    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String last_name = request.getParameter("last_name");
            String first_name = request.getParameter("first_name");
            String email = request.getParameter("email");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate naissance = null;
            try {
                 naissance = LocalDate.parse(request.getParameter("naissance"), dateFormatter);
            } catch (DateTimeParseException e) {
            e.printStackTrace(); }

            Client newClient = new Client(last_name, first_name, email, naissance);

            clientService.create(newClient);

            response.sendRedirect(request.getContextPath() + "/users");
        } catch (NumberFormatException | ServiceException e) {

            e.printStackTrace();
        }
    }
}