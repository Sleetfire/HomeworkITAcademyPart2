package by.it.academy.MK_JD2_88_2.hw1.storage.hibernate;

import by.it.academy.MK_JD2_88_2.hw1.dto.User;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.IHibernateUserStorage;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.UserAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.adapter.api.IEntityDTOAdapter;
import by.it.academy.MK_JD2_88_2.hw1.storage.hibernate.api.entity.UserEntity;
import org.hibernate.Session;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernateUserStorage implements IHibernateUserStorage {

    private static final IHibernateUserStorage instance = new HibernateUserStorage();
    private final HibernateDBInitializer dbInitializer;
    private final IEntityDTOAdapter<UserEntity, User> userAdapter;

    private HibernateUserStorage() {
        this.dbInitializer = HibernateDBInitializer.getInstance();
        this.userAdapter = new UserAdapter();
    }

    @Override
    public void add(User user) {
        EntityManager entityManager = this.dbInitializer.getManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this.userAdapter.dtoToEntity(user));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Long add(User user, EntityManager entityManager) {
        if (entityManager == null) {
            add(user);
            return get(user.getLogin()).getId();
        } else {
            Session session = entityManager.unwrap(Session.class);
            return (Long) session.save(this.userAdapter.dtoToEntity(user));
        }
    }

    @Override
    public List<User> getAll() {
        EntityManager entityManager = this.dbInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);

        entityManager.getTransaction().begin();
        List<UserEntity> userEntities = entityManager.createQuery(query).getResultList();
        List<User> users = new ArrayList<>(userEntities.size());
        userEntities.forEach(userEntity -> users.add(this.userAdapter.entityToDTO(userEntity)));
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }

    @Override
    public User get(String login) {
        User user;
        EntityManager entityManager = this.dbInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root).where(
                cb.equal(root.get("login"), login)
        );
        entityManager.getTransaction().begin();
        try {
            user = this.userAdapter.entityToDTO(entityManager.createQuery(query).getSingleResult());
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public int getCount() {
        EntityManager entityManager = this.dbInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(cb.count(root));
        entityManager.getTransaction().begin();
        Long count = entityManager.createQuery(query).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Math.toIntExact(count);
    }

    @Override
    public void delete(String login) {
        EntityManager entityManager = this.dbInitializer.getManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<UserEntity> criteriaDelete = cb.createCriteriaDelete(UserEntity.class);
        Root<UserEntity> root = criteriaDelete.from(UserEntity.class);
        criteriaDelete.where(
                cb.equal(root.get("login"), login)
        );
        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(String login, EntityManager entityManager) {
        if (entityManager == null) {
            delete(login);
        } else {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<UserEntity> criteriaDelete = cb.createCriteriaDelete(UserEntity.class);
            Root<UserEntity> root = criteriaDelete.from(UserEntity.class);
            criteriaDelete.where(
                    cb.equal(root.get("login"), login)
            );
            entityManager.createQuery(criteriaDelete).executeUpdate();
        }
    }

    public static IHibernateUserStorage getInstance() {
        return instance;
    }
}
