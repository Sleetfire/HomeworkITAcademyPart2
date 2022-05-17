package by.it.academy.MK_JD2_88_2.json_practice.service;

import by.it.academy.MK_JD2_88_2.json_practice.dto.Citizen;
import by.it.academy.MK_JD2_88_2.json_practice.service.api.IJsonObjectService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CitizenService implements IJsonObjectService<Citizen> {

    private final List<Citizen> citizensList = new ArrayList<>();
    private final static IJsonObjectService<Citizen> instance = new CitizenService();

    private CitizenService() {

    }

    @Override
    public void addJsonObjectToList(Citizen jsonObject) {
        this.citizensList.add(jsonObject);
    }

    @Override
    public List<Citizen> getJsonObjectsList() {
        return Collections.unmodifiableList(this.citizensList);
    }

    public static IJsonObjectService<Citizen> getInstance() {
        return instance;
    }
}
