package dao;

import entity.MessageEntity;
import entity.TopicEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class MessageDaoImpl implements MessageDao {

    private final int STEP = 2;

    @Autowired
    private SessionFactory sessionFactory;

    public void saveMessage(MessageEntity messageEntity) {
        sessionFactory.getCurrentSession().save(messageEntity);
    }

    public List getMessagesByTopicEntity(TopicEntity topicEntity) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MessageEntity.class);
        criteria.add(Restrictions.eq("topicEntity", topicEntity));
        criteria.setFirstResult(criteria.list().size() - STEP);
        criteria.setMaxResults(STEP);
        return criteria.list();
    }

    //-------для предыдущих сообщений.......................
    @Override
    public List getPreviousMessages(TopicEntity topicEntity, Integer numberOfPages) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MessageEntity.class);
        criteria.add(Restrictions.eq("topicEntity", topicEntity));
        criteria.addOrder(Order.desc("idMessage"));
        if (STEP * numberOfPages <= criteria.list().size()) {
            criteria.setFirstResult(STEP * numberOfPages);
            criteria.setMaxResults(STEP);
            return criteria.list();
        } else {
            return Collections.emptyList();
        }
    }


    //выбор новых сообщений........................................................
    @Override
    public List getNewMessages(TopicEntity topicEntity, LocalDateTime currentTime) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MessageEntity.class);
        criteria.add(Restrictions.eq("topicEntity", topicEntity));
        criteria.add(Restrictions.gt("localDateTime", currentTime));
        return criteria.list();
    }
    //--------------------------------------------------------------------------------
}
