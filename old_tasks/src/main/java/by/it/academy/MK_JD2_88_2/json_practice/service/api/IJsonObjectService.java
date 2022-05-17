package by.it.academy.MK_JD2_88_2.json_practice.service.api;


import java.util.List;

public interface IJsonObjectService<T> {

    void addJsonObjectToList(T jsonObject);

    List<T> getJsonObjectsList();
    
}
