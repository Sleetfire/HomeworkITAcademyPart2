package by.it.academy.MK_JD2_88_2.cw1.service;

import by.it.academy.MK_JD2_88_2.cw1.dto.Airport;
import by.it.academy.MK_JD2_88_2.cw1.service.api.IAirportService;
import by.it.academy.MK_JD2_88_2.cw1.storage.AirportStorage;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IAirportStorage;

import java.util.List;

public class AirportService implements IAirportService {

    private static IAirportService instance = new AirportService();
    private final IAirportStorage storage = AirportStorage.getInstance();

    private AirportService() {
    }

    @Override
    public List<Airport> get(String count) {
        return this.storage.get(getNumberFromString(count));
    }

    private int getNumberFromString(String str) {
        int value;
        try {
            value = Integer.parseInt(str);
        }catch (NumberFormatException e) {
            throw new RuntimeException("Введено не целое число", e);
        }
        return value;
    }

    public static IAirportService getInstance() {
        return instance;
    }
}
