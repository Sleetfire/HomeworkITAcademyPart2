package by.it.academy.MK_JD2_88_2.hw1.storage;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBUserStorage implements IUserStorage {

    private static final IUserStorage instance = new DBUserStorage();
    private Connection connection = null;

    private final String DB_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/app?ApplicationName=TestSweetApp";
    private final String DB_USER = "postgres";
    private final String DB_PASSWORD = "12378";

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

    private DBUserStorage() {
    }

    @Override
    public void add(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        LocalDate birthday = user.getBirthday();
        try {
            String sql = "INSERT INTO app.users (login, password, name, birthday) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setDate(4, Date.valueOf(birthday));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM app.users");
            while (rs.next()) {
                String userLogin = rs.getString("login");
                String password = rs.getString("password");
                String name = rs.getString("name");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                User user = new User(userLogin, password, name, birthday);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
        return users;
    }

    @Override
    public User get(String login) {
        User user = null;
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM app.users WHERE " +
                    "(login='" + login + "')");
            while (rs.next()) {
                String userLogin = rs.getString("login");
                String password = rs.getString("password");
                String name = rs.getString("name");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                user = new User(userLogin, password, name, birthday);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
        return user;
    }

    @Override
    public int getCount() {
        int count = 0;
        try ( Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT COUNT (*) FROM app.users");
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
           statement.executeUpdate("DELETE FROM app.users WHERE " +
                   "(login='" + login + "')");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
    }

    public static IUserStorage getInstance() {
        return instance;
    }
}

