package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.api;

public interface IEntityDTOAdapter<E, D> {

    D entityToDTO(E object);

    E dtoToEntity(D object);

}
