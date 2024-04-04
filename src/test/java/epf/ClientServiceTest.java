package epf;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ServiceException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.Model.Client;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.DaoException;
import org.mockito.Mockito;

public class ClientServiceTest {

    @Test
    public void should_return_list_of_clients() throws DaoException {

        ClientDao clientDaoMock = Mockito.mock(ClientDao.class);
        ClientService clientService = new ClientService(clientDaoMock);

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Doe", "John", "john@example.com", LocalDate.of(1990, 1, 1)));
        clients.add(new Client("Smith", "Jane", "jane@example.com", LocalDate.of(1985, 5, 15)));

        when(clientDaoMock.findAll()).thenReturn(clients);
        List<Client> returnedClients = null;
        try {
            returnedClients = clientService.findAll();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        assertIterableEquals(clients, returnedClients);
    }

    @Test
    public void should_create_client() throws ServiceException {
        ClientDao clientDaoMock = Mockito.mock(ClientDao.class);
        ClientService clientService = new ClientService(clientDaoMock);
        Client client = new Client("Doe", "John", "john@example.com", LocalDate.of(1990, 1, 1));

        try {
            when(clientDaoMock.emailExists(client.getEmail())).thenReturn(false);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        try {
            when(clientDaoMock.create(client)).thenReturn(1L);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        long clientId = clientService.create(client);

        assertEquals(1L, clientId);
    }

    @Test
    public void should_not_create_client_due_to_short_name() {
        ClientDao clientDaoMock = Mockito.mock(ClientDao.class);
        ClientService clientService = new ClientService(clientDaoMock);
        Client client = new Client("Do", "John", "john@example.com", LocalDate.of(1990, 1, 1));
        ServiceException exception = assertThrows(ServiceException.class, () -> clientService.create(client));
        assertEquals("Le nom du client doit faire au moins 3 caractères.", exception.getMessage());
    }

    @Test
    public void should_not_create_client_due_to_mineur() {

        ClientDao clientDaoMock = Mockito.mock(ClientDao.class);
        ClientService clientService = new ClientService(clientDaoMock);
        Client client = new Client("Doe", "John", "john@example.com", LocalDate.now().minusYears(17));
        ServiceException exception = assertThrows(ServiceException.class, () -> clientService.create(client));
        assertEquals("Le client doit avoir au moins 18 ans.", exception.getMessage());
    }

    @Test
    public void should_create_first_client_and_not_create_second_due_to_existing_email() throws DaoException {
        ClientDao clientDaoMock = Mockito.mock(ClientDao.class);
        ClientService clientService = new ClientService(clientDaoMock);
        Client client1 = new Client("Doe", "John", "john@example.com", LocalDate.of(1990, 1, 1));
        Client client2 = new Client("Smith", "Jane", "john@example.com", LocalDate.of(1985, 5, 15));

        when(clientDaoMock.emailExists(client1.getEmail())).thenReturn(false);
        when(clientDaoMock.emailExists(client2.getEmail())).thenReturn(true);
        when(clientDaoMock.create(client1)).thenReturn(1L);
        ServiceException exception = assertThrows(ServiceException.class, () -> clientService.create(client2));
        assertEquals("Cette adresse mail existe déjà", exception.getMessage());
    }

}
