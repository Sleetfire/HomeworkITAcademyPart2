package by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.mappers;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.Builder.createBuilder()
                .setLogin(rs.getString("login"))
                .setPassword(rs.getString("password"))
                .setName(rs.getString("name"))
                .setBirthday(rs.getObject("birthday", LocalDate.class))
                .setRgDate(rs.getObject("dt_rg", LocalDate.class))
                .setUpdateDateTime(rs.getObject("update_dt", LocalDateTime.class))
                .build();
    }
}
