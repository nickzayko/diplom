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
public class UserDao {

    @Autowired
    public SessionFactory sessionFactory;

    public void saveUser(UserEntity userEntity) {
        sessionFactory.getCurrentSession().save(userEntity);

    }

    public boolean isLoginExist(String login) {
        String userHQL = "FROM UserEntity WHERE user_login = :login";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("login", login);
        return query.list().size() > 0;
    }

    public boolean isEmailExist(String email) {
        String userHQL = "FROM UserEntity WHERE user_email = :email";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("email", email);
        return query.list().size() > 0;
    }

    public UserEntity isPasswordCorrect(String login, String password) {
        try {
            UserEntity userEntity = new UserEntity();
            String userHQL = "FROM UserEntity WHERE user_login = :login AND user_password = :password";
            org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
            query.setParameter("login", login);
            query.setParameter("password", password);
            userEntity = (UserEntity) query.getSingleResult();
            return userEntity;
        } catch (NoResultException nre) {

        }
        return null;
    }

    public UserEntity getUserEntityFromDataBase(int userId) {
        try {
            UserEntity userEntity = new UserEntity();
            String userHQL = "FROM UserEntity WHERE id_user = :user_id";
            org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
            query.setParameter("user_id", userId);
            userEntity = (UserEntity) query.getSingleResult();
            return userEntity;
        } catch (NoResultException nre) {

        }
        return null;
    }
}
