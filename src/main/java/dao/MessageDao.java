package dao;

import entity.MessageEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MessageDao implements MessageDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;


    public void saveMessage(MessageEntity messageEntity) {
        sessionFactory.getCurrentSession().save(messageEntity);
    }

    public List getMessagesByTopicId(Integer topicId) {
        String messageHQL = "FROM MessageEntity WHERE topic_id = :topicId";
        org.hibernate.query.Query query = sessionFactory.getCurrentSession().createQuery(messageHQL);
        query.setParameter("topicId", topicId);
        return query.list();
    }
}
