package hw1;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.service.MessageService;
import by.it.academy.MK_JD2_88_2.hw1.service.api.IMessageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceTest {

    private final IMessageService messageService = MessageService.getInstance();

    @Test
    @DisplayName(value = "Получение всех сообщений")
    public void getAllMessages() {
        createMessage();
        Assert.assertEquals(testMessagesList(), this.messageService.getAllMessages());
    }

    @Test
    @DisplayName(value = "Получения сообщений по логину отправителя")
    public void getMessagesBySenderLogin() {
        createMessage();
        Assert.assertEquals(testMessagesList(), this.messageService.getMessagesBySenderLogin("sender"));
    }

    @Test
    @DisplayName(value = "Получение сообщений по логину отправителя")
    public void getMessagesByRecipientLogin() {
        createMessage();
        Assert.assertEquals(testMessagesList(), this.messageService.getMessagesByRecipientLogin("recipient"));
    }

    @Test
    @DisplayName(value = "Получение количества сообщений")
    public void getMessagesCount() {
        createMessage();
        Assert.assertEquals(1, this.messageService.getMessagesCount());
    }

    @Test
    @DisplayName(value = "Удаление сообщений по логину пользователя")
    public void deleteMessagesByUserLogin() {
        createMessage();
        this.messageService.deleteMessagesByUserLogin("recipient");
        Assert.assertEquals(0, this.messageService.getMessagesCount());
    }

    private void createMessage() {
        this.messageService.createMessage(new Message("sender", "recipient", "lorem20",
                LocalDateTime.parse("+999999999-12-31T23:59:59.999999999")));
    }

    private List<Message> testMessagesList() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("sender", "recipient", "lorem20",
                LocalDateTime.parse("+999999999-12-31T23:59:59.999999999")));
        return messages;
    }
}
