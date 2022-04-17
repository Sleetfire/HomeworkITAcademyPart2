package by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.mappers;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MessageMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        String senderLogin = rs.getString("sender_login");
        String recipientLogin = rs.getString("recipient_login");
        String text = rs.getString("text");
        LocalDateTime dateTime = rs.getObject("date_time", LocalDateTime.class);
        User sender = new User();
        User recipient = new User();
        sender.setLogin(senderLogin);
        recipient.setLogin(recipientLogin);
        return Message.Builder.createBuilder()
                .setSender(sender)
                .setRecipient(recipient)
                .setText(text)
                .setDateTime(dateTime)
                .build();
    }
}
