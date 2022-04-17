package by.it.academy.MK_JD2_88_2.hw1.storage.sql;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.api.IUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.sql.api.mappers.MessageMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Repository("dBMessageStorage")
public class DBMessageStorage implements IMessageStorage {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DBMessageStorage(DataSource dataSource, @Qualifier("dBUserStorage") IUserStorage dbUserStorage) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void add(Message message) {
        String senderLogin = message.getSender().getLogin();
        String recipientLogin = message.getRecipient().getLogin();
        String text = message.getText();
        LocalDateTime dateTime = message.getDateTime();
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("sender_login", senderLogin);
        paramMap.addValue("recipient_login", recipientLogin);
        paramMap.addValue("text", text);
        paramMap.addValue("date_time", dateTime);
        String sql = "insert into app.messages (sender_login, recipient_login, text, date_time) values (:sender_login, :recipient_login, :text, :date_time)";
        try {
            this.namedParameterJdbcTemplate.update(sql, paramMap);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения sql", e);
        }
    }

    @Override
    public List<Message> getAll() {
        String sql = "select * from app.messages";
        try {
            return this.namedParameterJdbcTemplate.query(sql, new MessageMapper());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка выполнения sql", e);
        }
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
        String sql = "select * from app.messages where sender_login = :login";
        return get(sql, login);
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
        String sql = "select * from app.messages where recipient_login = :login";
        return get(sql, login);
    }

    private List<Message> get(String sql, String login) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("login", login);
        try {
            return this.namedParameterJdbcTemplate.query(sql, paramMap, new MessageMapper());
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public int getCount() {
        String sql = "select count (*) from app.messages";
       try {
           return this.namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
       } catch (Exception e) {
           throw new RuntimeException("Ошибка выполнения sql", e);
       }
    }

    @Override
    public void delete(String login) {
        String sql = "delete from app.messages where sender_login = :login";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("login", login);
        this.namedParameterJdbcTemplate.update(sql, paramMap);
    }

}
