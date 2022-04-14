package by.it.academy.MK_JD2_88_2.hw1.storage.api;

import by.it.academy.MK_JD2_88_2.hw1.storage.api.exceptions.EssenceNotFound;

public interface ICUDRepository <T, ID>{

    /**
     * Creating an item
     * @param item
     */
    void create(T item);

    /**
     * Deleting an item by id
     * @param id
     * @throws EssenceNotFound when item is not found
     */
    void delete(ID id) throws EssenceNotFound;

    /**
     * Updating an item
     * @param item
     * @param id
     * @throws EssenceNotFound when item is not found
     */
    void update(T item, ID id) throws EssenceNotFound;

}
