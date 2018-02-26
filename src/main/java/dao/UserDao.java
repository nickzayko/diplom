package dao;

import entity.UserEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
public class UserDao implements UserDaoInterface {

    @Autowired
    public SessionFactory sessionFactory;

    public void saveUser(UserEntity userEntity) {
        sessionFactory.getCurrentSession().save(userEntity);

    }

    public boolean checkIsLoginExist(String login) {
        String userHQL = "FROM UserEntity WHERE user_login = :login";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("login", login);
        return query.list().size() > 0;
    }

    public boolean checkIsEmailExist(String email) {
        String userHQL = "FROM UserEntity WHERE user_email = :email";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("email", email);
        return query.list().size() > 0;
    }

    public UserEntity findPassword(String login, String password) {
        try {
            String userHQL = "FROM UserEntity WHERE user_login = :login AND user_password = :password";
            org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
            query.setParameter("login", login);
            query.setParameter("password", password);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException nre) {

        }
        return null;
    }

    public UserEntity getUser(int userId) {
        try {
            String userHQL = "FROM UserEntity WHERE id_user = :user_id";
            org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
            query.setParameter("user_id", userId);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException nre) {

        }
        return null;
    }
}
