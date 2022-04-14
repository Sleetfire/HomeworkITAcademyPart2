package by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.mappers;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AuditUserMapper implements RowMapper<AuditUser> {
    @Override
    public AuditUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        User author = User.Builder.createBuilder()
                .setLogin(rs.getString("author"))
                .setName(rs.getString("author"))
                .setRgDate(rs.getObject("author_dt_rg", LocalDate.class))
                .setBirthday(rs.getObject("author_birthday", LocalDate.class))
                .build();
        User user = User.Builder.createBuilder()
                .setLogin(rs.getString("user"))
                .setName(rs.getString("obj_name"))
                .setRgDate(rs.getObject("obj_dt_rg", LocalDate.class))
                .setBirthday(rs.getObject("obj_birthday", LocalDate.class))
                .build();
        return AuditUser.Builder.createBuilder()
                .setUser(user)
                .setAuthor(author)
                .setText(rs.getString("text"))
                .setDtCreate(rs.getObject("dt_create", LocalDateTime.class))
                .build();
    }
}
