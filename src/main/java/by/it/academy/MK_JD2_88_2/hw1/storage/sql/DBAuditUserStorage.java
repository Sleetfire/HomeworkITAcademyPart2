package by.it.academy.MK_JD2_88_2.hw1.storage.sql;

import by.it.academy.MK_JD2_88_2.hw1.dto.AuditUser;
import by.it.academy.MK_JD2_88_2.hw1.dto.Pageable;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IAuditUserStorage;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBAuditUserStorage implements IAuditUserStorage {

    private static IAuditUserStorage instance = new DBAuditUserStorage();
    private final DataSource ds;

    private DBAuditUserStorage() {
        this.ds = DBInitializer.getInstance().getDataSource();
    }

    @Override
    public void create(AuditUser audit) {

        if (audit == null) {
            throw new IllegalStateException("Аудит не может быть null");
        }

        LocalDateTime dtCreate = audit.getDtCreate();
        String text = audit.getText();
        User user = audit.getUser();
        String userLogin = user.getLogin();

        String sql = "INSERT INTO app.audit_users (dt_create, text, author, \"user\") VALUES (?, ?, ?, ?)";
        try (Connection connection = this.ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"})) {
            ps.setTimestamp(1, Timestamp.valueOf(dtCreate));
            ps.setString(2, text);
            ps.setString(3, audit.getAuthor() != null ? audit.getAuthor().getLogin() : null);
            ps.setString(4, userLogin);

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                } else {
                    throw new SQLException("Creating user failed, no ID obtained");
                }
            } catch (SQLException e) {
                System.out.println("Ошибка выполнения SQL " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
        public List<AuditUser> read(Pageable pageable) {
            List<AuditUser> data = new ArrayList<>();
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

            try (Connection connection = ds.getConnection();
                 Statement statement = connection.createStatement()
            ) {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    LocalDateTime dtCreate = rs.getObject("dt_create", LocalDateTime.class);
                    String text = rs.getString("text");
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
                    AuditUser audit = new AuditUser(id, dtCreate, text, author, user);
                    data.add(audit);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка выполнения SQL", e);
            }
            return data;
        }

    @Override
    public void deleteByUser(User user) {

    }

    public static IAuditUserStorage getInstance () {
            return instance;
        }
    }
