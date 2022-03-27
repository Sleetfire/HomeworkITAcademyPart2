package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.api.IEntityDTOAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.UserEntity;

public class UserAdapter implements IEntityDTOAdapter<UserEntity, User> {
    @Override
    public User entityToDTO(UserEntity object) {
        if (object == null) {
            return null;
        }
        return User.Builder.createBuilder()
                .setId(object.getId())
                .setLogin(object.getLogin())
                .setPassword(object.getPassword())
                .setName(object.getName())
                .setBirthday(object.getBirthday())
                .setRgDate(object.getRgDate())
                .build();
    }

    @Override
    public UserEntity dtoToEntity(User object) {
        if (object == null) {
            return null;
        }
        return UserEntity.Builder.createBuilder()
                .setId(object.getId())
                .setLogin(object.getLogin())
                .setPassword(object.getPassword())
                .setName(object.getName())
                .setBirthday(object.getBirthday())
                .setRgDate(object.getRgDate())
                .build();
    }
}
