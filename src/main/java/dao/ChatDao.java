package dao;

import entity.TopicEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
public class ChatDao implements ChatDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;

    public void createNewTopic(TopicEntity topicEntity) {
        sessionFactory.getCurrentSession().save(topicEntity);
    }

    public boolean isTopicExist(String topicName) {
        String chatHQL = "FROM TopicEntity WHERE topic_name = :topic";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(chatHQL);
        query.setParameter("topic", topicName);
        return query.list().size() > 0;
    }

    public List findChats(String findChatParam) {
        String chatHQL = "FROM TopicEntity WHERE topic_name LIKE ?";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(chatHQL);
        query.setString(0, findChatParam + "%");
        return query.list();
    }

    public List showAllChats() {
        String chatHQL = "FROM TopicEntity";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(chatHQL);
        return query.list();

    }

    public List showMyChats(int idUser) {
        String chatHQL = "FROM TopicEntity WHERE user_id = :idUser";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(chatHQL);
        query.setParameter("idUser", idUser);
        return query.list();
    }

    public TopicEntity getTopicByName(String requestParam) {
        try {
            String userHQL = "FROM TopicEntity WHERE topic_name = :name";
            org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
            query.setParameter("name", requestParam);
            return (TopicEntity) query.getSingleResult();
        } catch (NoResultException nre) {

        }
        return null;
    }

    public TopicEntity getTopicById(int topicId) {
        try {
            String userHQL = "FROM TopicEntity WHERE id_topics = :id";
            org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
            query.setParameter("id", topicId);
            return (TopicEntity) query.getSingleResult();
        } catch (NoResultException nre) {

        }
        return null;
    }
}