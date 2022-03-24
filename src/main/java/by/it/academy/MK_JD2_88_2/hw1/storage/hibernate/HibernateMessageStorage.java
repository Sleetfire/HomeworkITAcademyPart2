package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate;

import by.it.academy.MK_JD2_88_2.hw1.dto.Message;

import by.it.academy.MK_JD2_88_2.hw1.storage.api.IMessageStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.MessageAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.MessageEntity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernateMessageStorage implements IMessageStorage {

    private static IMessageStorage instance = new HibernateMessageStorage();
    private final HibernateDBInitializer dbInitializer;
    private final MessageAdapter messageAdapter;

    private HibernateMessageStorage() {
        this.dbInitializer = HibernateDBInitializer.getInstance();
        this.messageAdapter = new MessageAdapter();
    }

    @Override
    public void add(Message message) {
        EntityManager entityManager = this.dbInitializer.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this.messageAdapter.dtoToEntity(message));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Message> getAll() {
        return getByLogin(null, null);
    }

    @Override
    public List<Message> getBySenderLogin(String login) {
        return getByLogin(login, "senderLogin");
    }

    @Override
    public List<Message> getByRecipientLogin(String login) {
      return getByLogin(login, "recipientLogin");
    }

    @Override
    public int getCount() {
        EntityManager entityManager = this.dbInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);
        query.select(cb.count(root));
        Long count = entityManager.createQuery(query).getSingleResult();
        entityManager.close();
        return Math.toIntExact(count);
    }

    @Override
    public void delete(String login) {
        EntityManager entityManager = this.dbInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<MessageEntity> criteriaDelete = cb.createCriteriaDelete(MessageEntity.class);
        Root<MessageEntity> root = criteriaDelete.from(MessageEntity.class);
        criteriaDelete.where(
                cb.equal(root.get("senderLogin"), login)
        );
        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    List<Message> getByLogin(String login, String fieldName) {
        EntityManager entityManager = this.dbInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);

        if (login == null || fieldName == null) {
            query.select(root);
        } else {
            query.select(root).where(
                    cb.equal(root.get(fieldName), login)
            );
        }

        List<MessageEntity> messageEntities = entityManager.createQuery(query).getResultList();
        List<Message> messages = new ArrayList<>(messageEntities.size());
        messageEntities.forEach(messageEntity -> messages.add(this.messageAdapter.entityToDTO(messageEntity)));
        entityManager.close();
        return messages;
    }

    public static IMessageStorage getInstance() {
        return instance;
    }
}
