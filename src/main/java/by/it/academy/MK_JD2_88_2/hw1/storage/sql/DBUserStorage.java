package by.it.academy.MK_JD2_88_2.hw1.storage.sql;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBUserStorage implements IUserStorage {

    private static final IUserStorage instance = new DBUserStorage();
    private final IAuditUserStorage auditStorage;
    private final DataSource ds;

    private DBUserStorage() {
        this.ds = DBInitializer.getInstance().getDataSource();
        this.auditStorage = DBAuditUserStorage.getInstance();
    }

    @Override
    public void add(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        LocalDate regDate = user.getRgDate();
        LocalDate birthday = user.getBirthday();
        String sql = "INSERT INTO app.users (login, password, name, dt_rg, birthday) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = this.ds.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setDate(4, Date.valueOf(regDate));
            preparedStatement.setDate(5, Date.valueOf(birthday));
            preparedStatement.execute();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM app.users");
            while (rs.next()) {
                String userLogin = rs.getString("login");
                String password = rs.getString("password");
                String name = rs.getString("name");
                LocalDate rgDate = rs.getDate("dt_reg").toLocalDate();
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                User user = new User(userLogin, password, name, rgDate, birthday);
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
        String sql = "SELECT login, password, name, birthday, dt_rg FROM app.users WHERE login = ?";
        try (Connection connection = this.ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                String userLogin = rs.getString("login");
                String password = rs.getString("password");
                String name = rs.getString("name");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                LocalDate rgDate = rs.getDate("dt_rg").toLocalDate();
                user = new User(userLogin, password, name, rgDate, birthday);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения SQL " + e.getMessage());
        }
        return user;
    }

    @Override
    public int getCount() {
        int count = 0;
        try (Connection connection = this.ds.getConnection();
             Statement statement = connection.createStatement()) {
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
        try (Connection connection = ds.getConnection();
             Statement statement = connection.createStatement()) {
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

