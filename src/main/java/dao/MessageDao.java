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

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class MessageDao implements MessageDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveMessage(MessageEntity messageEntity) {
        sessionFactory.getCurrentSession().save(messageEntity);
    }

    public List getMessagesByTopicEntity(TopicEntity topicEntity) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MessageEntity.class);
        criteria.add(Restrictions.eq("topicEntity", topicEntity));
        criteria.setFirstResult(criteria.list().size() - 2);
        criteria.setMaxResults(2);
        return criteria.list();
    }

    //-------для предыдущих сообщений.......................
    @Override
    public List getPreviousMessages(TopicEntity topicEntity, HttpSession session) {
        int numberPage = (int) session.getAttribute("numberOfPages");
        numberPage = numberPage + 1;
        session.setAttribute("numberOfPages", numberPage);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MessageEntity.class);
        criteria.add(Restrictions.eq("topicEntity", topicEntity));
        criteria.addOrder(Order.desc("idMessage"));
        if (2 * numberPage <= criteria.list().size()) {
            criteria.setFirstResult(2 * numberPage);
            criteria.setMaxResults(2);
            return criteria.list();
        } else {
            return criteria.list();
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
