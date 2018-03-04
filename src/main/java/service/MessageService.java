package service;

import dao.MessageDao;
import entity.MessageEntity;
import entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService implements MessageServiceInterface {

    @Autowired
    private MessageDao messageDao;

    public void saveMessage(MessageEntity messageEntity) {
        messageDao.saveMessage(messageEntity);
    }

    public List getMessagesByTopicEntity(TopicEntity topicEntity) {
        return messageDao.getMessagesByTopicEntity(topicEntity);
    }

    @Override
    public List getPreviousMessages(TopicEntity topicEntity, HttpSession session) {
        return messageDao.getPreviousMessages(topicEntity, session);
    }

    @Override
    public List getNewMessages(TopicEntity topicEntity, LocalDateTime currentTime) {
        return messageDao.getNewMessages(topicEntity, currentTime);
    }
}
