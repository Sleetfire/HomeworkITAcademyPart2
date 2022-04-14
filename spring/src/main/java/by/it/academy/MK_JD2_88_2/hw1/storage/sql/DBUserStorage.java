package by.it.academy.MK_JD2_88_2.hw1.storage.sql;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.exceptions.EssenceNotFound;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.mappers.UserMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Repository("dBUserStorage")
public class DBUserStorage implements IUserStorage {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DBUserStorage(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void create(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        LocalDate regDate = user.getRgDate();
        LocalDate birthday = user.getBirthday();
        LocalDateTime updateDateTime = user.getUpdateDateTime();
        String sql = "insert into app.users (login, password, name, dt_rg, birthday, update_dt) values (:login," +
                " :password, :name," +
                " :regDate, :birthday, :update_dt)";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("login", login);
        paramMap.addValue("password", password);
        paramMap.addValue("name", name);
        paramMap.addValue("regDate", regDate);
        paramMap.addValue("birthday", birthday);
        paramMap.addValue("update_dt", updateDateTime);
        try {
            this.namedParameterJdbcTemplate.update(sql, paramMap);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения sql", e);
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "select * from app.users";
        try {
            return this.namedParameterJdbcTemplate.query(sql, new UserMapper());
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public User get(String login) {
        String sql = "select * from app.users where login = :login";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("login", login);
        User user;
        try {
            user = this.namedParameterJdbcTemplate.queryForObject(sql, paramMap, new UserMapper());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return user;
    }

    @Override
    public int getCount() {
        String sql = "select count (*) from app.users";
        try {
            return this.namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения sql", e);
        }
    }

    @Override
    public void delete(String id) {
        User user = this.get(id);
        LocalDateTime updateDateTime = user.getUpdateDateTime();
        String sql = "delete from app.users where login = :login and update_dt = :update_dt";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("login", id);
        paramMap.addValue("update_dt", updateDateTime);
        try {
            int update = this.namedParameterJdbcTemplate.update(sql, paramMap);
            if (update == 0) {
                throw new EssenceNotFound("Не удалось удалить пользователя с id = " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения sql", e);
        }
    }

    @Override
    public void update(User user, String id) {
        User dbUser = this.get(user.getLogin());

        String sql = "update app.users " +
                "set login = :login, " +
                "password = :password, " +
                "name = :name, " +
                "birthday = :birthday, " +
                "update_dt = :update_dt " +
                "where login = :old_login and update_dt = :old_update_dt;";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("login", user.getLogin() == null ? dbUser.getLogin() : user.getLogin());
        paramMap.addValue("password", user.getPassword() == null ? dbUser.getPassword() : user.getPassword());
        paramMap.addValue("name", user.getName() == null ? dbUser.getName() : user.getName());
        paramMap.addValue("birthday", user.getBirthday() == null ? dbUser.getBirthday() : user.getBirthday());
        paramMap.addValue("update_dt", LocalDateTime.now());
        paramMap.addValue("old_login", id);
        paramMap.addValue("old_update_dt", dbUser.getUpdateDateTime());
        try {
            int update = this.namedParameterJdbcTemplate.update(sql, paramMap);
            if (update == 0) {
                throw new EssenceNotFound();
            }
        } catch (Exception e) {
            throw new RuntimeException("Не удалось обновить пользователя с id = " + id);
        }
    }
}

