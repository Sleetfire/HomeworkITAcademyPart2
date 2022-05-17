package by.it.academy.MK_JD2_88_2.cw1.storage.api;

import by.it.academy.MK_JD2_88_2.cw1.dto.Airport;

import java.util.List;

public interface IAirportStorage {

    List<Airport> get(int count);

}
