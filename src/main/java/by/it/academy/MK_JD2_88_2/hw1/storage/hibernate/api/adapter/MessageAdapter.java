package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.api.IEntityDTOAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.MessageEntity;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.UserEntity;

public class MessageAdapter implements IEntityDTOAdapter<MessageEntity, Message> {

    private final IEntityDTOAdapter<UserEntity, User> userAdapter;

    public MessageAdapter() {
        this.userAdapter = new UserAdapter();
    }

    @Override
    public Message entityToDTO(MessageEntity object) {
        if (object == null) {
            return null;
        }
        return Message.Builder.createBuilder()
                .setId(object.getId())
                .setText(object.getText())
                .setDateTime(object.getDateTime())
                .setSender(this.userAdapter.entityToDTO(object.getRecipient()))
                .setRecipient(this.userAdapter.entityToDTO(object.getRecipient()))
                .build();
    }

    @Override
    public MessageEntity dtoToEntity(Message object) {
        if (object == null) {
            return null;
        }
        return MessageEntity.Builder.createBuilder()
                .setId(object.getId())
                .setText(object.getText())
                .setDateTime(object.getDateTime())
                .setSender(this.userAdapter.dtoToEntity(object.getSender()))
                .setRecipient(this.userAdapter.dtoToEntity(object.getRecipient()))
                .build();
    }
}
