package by.it.academy.MK_JD2_88_2.hw1.storage.sql;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.mappers.AuditUserMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository("dBAuditUserStorage")
public class DBAuditUserStorage implements IAuditUserStorage {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DBAuditUserStorage(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void create(AuditUser audit) {

        if (audit == null) {
            throw new IllegalStateException("Аудит не может быть null");
        }

        LocalDateTime dtCreate = audit.getDtCreate();
        String text = audit.getText();
        User user = audit.getUser();
        User author = audit.getAuthor();
        String userLogin = user.getLogin();
        String authorLogin = author != null ? author.getLogin() : null;
        String sql = "insert into app.audit_users (dt_create, text, author, \"user\") " +
                "values (:dt_create, :text, :author, :user)";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("dt_create", dtCreate);
        paramMap.addValue("text", text);
        paramMap.addValue("author", authorLogin);
        paramMap.addValue("user", userLogin);
        try {
            this.namedParameterJdbcTemplate.update(sql, paramMap);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения sql", e);
        }

    }

    @Override
    public List<AuditUser> read(Pageable pageable) {
        Integer limit = null;
        Integer offset = null;

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                limit = pageable.getSize();
            }
            if (limit != null && pageable.getPage() > 0) {
                offset = (pageable.getPage() - 1) * limit;
            }
        }

        String sql = "SELECT audit.id AS id,\n" +
                "\t   audit.dt_create ,\n" +
                "\t   audit.text,\n" +
                "\t   audit.\"user\",\n" +
                "\t   \n" +
                "\t   obj.dt_rg AS obj_dt_rg,\n" +
                "\t   obj.name AS obj_name,\n" +
                "\t   obj.birthday AS obj_birthday,\n" +
                "\t   \n" +
                "\t   audit.author,\n" +
                "\t   \n" +
                "\t   author.dt_rg AS author_dt_rg,\n" +
                "\t   author.name AS author_name,\n" +
                "\t   author.birthday AS author_birthday\n" +
                "FROM app.audit_users AS audit\n" +
                "LEFT JOIN app.users AS author ON audit.author = author.login\n" +
                "LEFT JOIN app.users AS obj ON audit.\"user\" = obj.login";

        if (limit != null) {
            sql += "\n LIMIT " + limit;
        }
        if (offset != null) {
            sql += "\n OFFSET " + offset;
        }
        sql += ";";

        try {
            return this.namedParameterJdbcTemplate.query(sql, new AuditUserMapper());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения sql", e);
        }
    }

    @Override
    public void deleteByUser(User user) {
        String userLogin = user != null ? user.getLogin() : null;
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("user", userLogin);
        String sql = "delete from app.audit_users where \"user\" = :user";
        try {
            this.namedParameterJdbcTemplate.update(sql, paramMap);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения sql", e);
        }
    }

}
