package by.it.academy.MK_JD2_88_2.hw1.repository;

import by.it.academy.MK_JD2_88_2.hw1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    void deleteByLogin(String login);

    @Query("update User user set user = :user, user.updateDateTime = current_timestamp where user.login =:login and user.updateDateTime = :old_update")
    void update(@Param(value = "user") User user, @Param(value = "login") String login, @Param(value = "old_update") LocalDateTime oldUpdate);


}
