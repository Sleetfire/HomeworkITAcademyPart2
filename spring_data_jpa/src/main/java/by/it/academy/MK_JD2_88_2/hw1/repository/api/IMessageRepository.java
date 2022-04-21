package by.it.academy.MK_JD2_88_2.hw1.repository.api;

import by.it.academy.MK_JD2_88_2.hw1.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {

    @Query("from Message message where message.sender.login = :login")
    List<Message> findBySenderLogin(@Param("login") String login);

    @Query("from Message message where message.recipient.login = :login")
    List<Message> findByRecipientLogin(@Param("login") String login);

    @Modifying
    @Query("delete from Message m where m.sender.login = :login")
    void deleteBySenderLogin(@Param("login") String login);

}
