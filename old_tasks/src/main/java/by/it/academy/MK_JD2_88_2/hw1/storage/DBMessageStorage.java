package by.it.academy.MK_JD2_88_2.hw1.storage;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBMessageStorage implements IMessageStorage {

    private static final IMessageStorage instance = new DBMessageStorage();
    private Connection connection = null;

    private final String DB_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/app?ApplicationName=TestSweetApp";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "12378";

    private DBMessageStorage() {
    }

    {
        try {
            Class.forName(this.DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Проблемы с загрузкой");
        }
        try {
            this.connection = DriverManager.getConnection(
                    this.DB_URL, this.DB_USER, this.DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
    }

    @Override
    public void create(Message message) {
        String senderLogin = message.getSenderLogin();
        String recipientLogin = message.getRecipientLogin();
        String text = message.getText();
        LocalDateTime dateTime = message.getDateTime();
        String sql = "INSERT INTO app.messages (sender_login, recipient_login, text, data_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, senderLogin);
            preparedStatement.setString(2, recipientLogin);
            preparedStatement.setString(3, text);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(dateTime));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
    }

    @Override
    public List<Message> getAll() {
        return get("SELECT * FROM app.messages");
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
        return get("SELECT * FROM app.messages WHERE " +
                "(sender_login='" + login + "')");
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
        return get("SELECT * FROM app.messages WHERE " +
                "(recipient_login='" + login + "')");
    }

    private List<Message> get(String sql) {
        List<Message> messages = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String senderLogin = rs.getString("sender_login");
                String recipientLogin = rs.getString("recipient_login");
                String text = rs.getString("text");
                LocalDateTime dateTime = rs.getTimestamp("data_time").toLocalDateTime();
                Message message = new Message(senderLogin, recipientLogin, text, dateTime);
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
        return messages;
    }

    @Override
    public int getCount() {
        int count = 0;
        try ( Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT COUNT (*) FROM app.messages");
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
        return count;
    }

    @Override
    public void delete(String login) {
        try ( Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM app.messages WHERE " +
                    "(sender_login='" + login + "')");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
    }

    public static IMessageStorage getInstance() {
        return instance;
    }
}
