package by.it.academy.MK_JD2_88_2.hw1.repository;

import by.it.academy.MK_JD2_88_2.hw1.model.AuditUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuditUserRepository extends JpaRepository<AuditUser, Long> {

}
