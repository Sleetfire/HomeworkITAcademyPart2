package by.it.academy.MK_JD2_88_2.hw1.repository.api;

import by.it.academy.MK_JD2_88_2.hw1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    @Modifying
    void deleteByLogin(String login);

    @Modifying()
    @Query("update User user set user = :user, user.updateDateTime = current_timestamp where user.login =:login and user.updateDateTime = :old_update")
    void update(@Param("user") User user, @Param("login") String login, @Param("old_update") LocalDateTime oldUpdate);


}
