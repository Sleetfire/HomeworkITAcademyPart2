package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.api.IEntityDTOAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.AuditUserEntity;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.UserEntity;

public class AuditUserAdapter implements IEntityDTOAdapter<AuditUserEntity, AuditUser> {

    private final IEntityDTOAdapter<UserEntity, User> adapter;

    public AuditUserAdapter() {
        this.adapter = new UserAdapter();
    }

    @Override
    public AuditUser entityToDTO(AuditUserEntity object) {
        if (object == null) {
            return null;
        }
        return AuditUser.Builder.createBuilder()
                .setId(object.getId())
                .setDtCreate(object.getDtCreate())
                .setText(object.getText())
                .setAuthor(this.adapter.entityToDTO(object.getAuthor()))
                .setUser(this.adapter.entityToDTO(object.getUser())).build();
    }

    @Override
    public AuditUserEntity dtoToEntity(AuditUser object) {
        if (object == null) {
            return null;
        }
        return AuditUserEntity.Builder.createBuilder()
                .setId(object.getId())
                .setDtCreate(object.getDtCreate())
                .setText(object.getText())
                .setAuthor(this.adapter.dtoToEntity(object.getAuthor()))
                .setUser(this.adapter.dtoToEntity(object.getUser()))
                .build();
    }
}
