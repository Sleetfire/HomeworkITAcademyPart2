package by.it.academy.MK_JD2_88_2.hw1.repository.api;

import by.it.academy.MK_JD2_88_2.hw1.model.AuditUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuditUserRepository extends JpaRepository<AuditUser, Long> {

    @Modifying
    @Query("delete from AuditUser audit where audit.user.login=:login")
    void deleteByUserLogin(@Param("login") String login);

}
