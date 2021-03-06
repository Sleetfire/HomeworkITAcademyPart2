package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;
import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.IHibernateMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.MessageAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.UserAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.MessageEntity;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository("hibernateMessageStorage")
public class HibernateMessageStorage implements IHibernateMessageStorage {

    private final HibernateDBInitializer hibernateDBInitializer;
    private final MessageAdapter messageAdapter;

    public HibernateMessageStorage(HibernateDBInitializer hibernateDBInitializer) {
        this.hibernateDBInitializer = hibernateDBInitializer;
        this.messageAdapter = new MessageAdapter();
    }

    @Override
    public void add(Message message) {
        EntityManager entityManager = this.hibernateDBInitializer.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this.messageAdapter.dtoToEntity(message));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void add(Message message, EntityManager entityManager) {
        if (entityManager == null) {
            add(message);
        } else {
            entityManager.persist(this.messageAdapter.dtoToEntity(message));
        }
    }

    @Override
    public List<Message> getAll() {
        return getByLogin(null, null);
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
        return getByLogin(login, "sender");
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
        return getByLogin(login, "recipient");
    }

    @Override
    public int getCount() {
        EntityManager entityManager = this.hibernateDBInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);
        query.select(cb.count(root));
        entityManager.getTransaction().begin();
        Long count = entityManager.createQuery(query).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Math.toIntExact(count);
    }

    @Override
    public void delete(String login) {
        EntityManager entityManager = this.hibernateDBInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<MessageEntity> criteriaDelete = cb.createCriteriaDelete(MessageEntity.class);
        Root<MessageEntity> root = criteriaDelete.from(MessageEntity.class);
        criteriaDelete.where(
                cb.equal(root.get("sender").get("login"), login)
        );
        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(User user, EntityManager entityManager) {
        if (entityManager == null) {
            delete(user.getLogin());
        } else {
            UserEntity userEntity = new UserAdapter().dtoToEntity(user);
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<MessageEntity> criteriaDelete = cb.createCriteriaDelete(MessageEntity.class);
            Root<MessageEntity> root = criteriaDelete.from(MessageEntity.class);
            criteriaDelete = criteriaDelete.where(
                    cb.equal(root.get("sender"), userEntity)
            );
            entityManager.createQuery(criteriaDelete).executeUpdate();
        }
    }

    List<Message> getByLogin(String login, String fieldName) {
        EntityManager entityManager = this.hibernateDBInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);

        if (login == null || fieldName == null) {
            query.select(root);
        } else {
            query.select(root).where(
                    cb.equal(root.get(fieldName).get("login"), login)
            );
        }

        entityManager.getTransaction().begin();
        List<MessageEntity> messageEntities = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();

        List<Message> messages = new ArrayList<>(messageEntities.size());
        messageEntities.forEach(messageEntity -> messages.add(this.messageAdapter.entityToDTO(messageEntity)));
        entityManager.close();
        return messages;
    }
}
