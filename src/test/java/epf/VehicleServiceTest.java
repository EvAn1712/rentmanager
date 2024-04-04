package epf;

import com.epf.rentmanager.dao.DaoException;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.Model.Vehicle;
import com.epf.rentmanager.service.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class VehicleServiceTest {

    private VehicleDao vehicleDaoMock;
    private VehicleService vehicleService;

    @BeforeEach
    public void setUp() {
        vehicleDaoMock = Mockito.mock(VehicleDao.class);
        vehicleService = new VehicleService(vehicleDaoMock);
    }

    @Test
    public void should_create_vehicle() throws DaoException, ServiceException {
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 5);
        when(vehicleDaoMock.create(vehicle)).thenReturn(1L);

        long createdVehicleId = vehicleService.create(vehicle);

        assertEquals(1L, createdVehicleId);
    }
    @Test
    public void should_not_create_vehicle_due_to_invalid_number_of_seats() throws DaoException {

        VehicleDao vehicleDaoMock = Mockito.mock(VehicleDao.class);
        VehicleService vehicleService = new VehicleService(vehicleDaoMock);

        Vehicle vehicle = new Vehicle();
        vehicle.setNb_place(10);
        ServiceException exception = assertThrows(ServiceException.class, () -> vehicleService.create(vehicle));
        assert("le nombre de places doit être compris entre 2 et 9.".equals(exception.getMessage()));
    }

    @Test
    public void should_find_vehicle_by_id() throws DaoException, ServiceException {
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 5);
        when(vehicleDaoMock.findById(1L)).thenReturn(vehicle);

        Vehicle foundVehicle = vehicleService.findById(1L);

        assertEquals(vehicle, foundVehicle);
    }

    @Test
    public void should_find_all_vehicles() throws DaoException, ServiceException {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Toyota", "Corolla", 5));
        vehicles.add(new Vehicle("Honda", "Civic", 4));
        when(vehicleDaoMock.findAll()).thenReturn(vehicles);

        List<Vehicle> foundVehicles = vehicleService.findAll();

        assertEquals(vehicles.size(), foundVehicles.size());
        assertEquals(vehicles.get(0), foundVehicles.get(0));
        assertEquals(vehicles.get(1), foundVehicles.get(1));
    }

    @Test
    public void should_delete_vehicle() throws DaoException, ServiceException {
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 5);
        when(vehicleDaoMock.delete(vehicle)).thenReturn(1L);

        long deletedVehicleId = vehicleService.delete(vehicle);

        assertEquals(1L, deletedVehicleId);
    }

    @Test
    public void should_count_vehicles() throws DaoException, ServiceException {
        when(vehicleDaoMock.count()).thenReturn(5);

        int vehicleCount = vehicleService.count();

        assertEquals(5, vehicleCount);
    }

    @Test
    public void should_modify_vehicle() throws DaoException, ServiceException {
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 5);

        vehicleService.modifierVehicle(vehicle);

        verify(vehicleDaoMock, times(1)).modifier(vehicle);
    }

    @Test
    public void should_not_modify_vehicle_due_to_invalid_number_of_seats() throws DaoException {
        VehicleDao vehicleDaoMock = Mockito.mock(VehicleDao.class);
        VehicleService vehicleService = new VehicleService(vehicleDaoMock);
        Vehicle vehicle = new Vehicle();
        vehicle.setNb_place(1);
        when(vehicleDaoMock.findById(vehicle.getID())).thenReturn(vehicle);
        ServiceException exception = assertThrows(ServiceException.class, () -> vehicleService.modifierVehicle(vehicle));
        assert("le nombre de places doit être compris entre 2 et 9.".equals(exception.getMessage()));
    }

}

