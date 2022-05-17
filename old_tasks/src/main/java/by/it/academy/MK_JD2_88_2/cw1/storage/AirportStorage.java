package by.it.academy.MK_JD2_88_2.cw1.storage;

import by.it.academy.MK_JD2_88_2.cw1.dto.Airport;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IAirportStorage;

import java.util.List;

public class AirportStorage implements IAirportStorage {

    private static IAirportStorage instance = new AirportStorage();

    private AirportStorage() {
    }

    @Override
    public List<Airport> get(int count) {
        return null;
    }

    public static IAirportStorage getInstance() {
        return instance;
    }
}
